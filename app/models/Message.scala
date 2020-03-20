package models

import javax.inject._
import scala.concurrent.{ Future, ExecutionContext }
import play.api.Configuration
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.JdbcProfile

case class Message(text: String, id: Long = 0L)

@Singleton
class MessageRepository @Inject()(config: Configuration)(implicit ec: ExecutionContext) {
  private val url = config.get[String]("slick.dbs.default.db.url")
  private val driver = config.get[String]("slick.dbs.default.db.profile")
  private val user = config.get[String]("slick.dbs.default.db.user")
  private val password = config.get[String]("slick.dbs.default.db.password")
  private val db = Database.forURL(
    url,
    driver = driver,
    user = user,
    password = password,
    executor = AsyncExecutor(
      "job_worker",
      minThreads = 20,
      maxThreads = 20,
      queueSize = 20,
      maxConnections = 20
    )
  )

  private class MessageTable(tag: Tag) extends Table[Message](tag, "messages") {
    def id   = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def text = column[String]("text")
    def *    = (text, id).mapTo[Message]
  }
  private val messages = TableQuery[MessageTable]

  def all(): Future[Seq[Message]] = {
    val query = messages.result
    db.run {
      query
    }
  }

  def create(text: String): Future[Option[Int]] = {
    val query = messages ++= Seq(Message(text))
    db.run(query)
  }
}
