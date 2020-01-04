package models

import javax.inject._
import scala.concurrent.{ Future, Await, ExecutionContext }
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.JdbcProfile

case class Message(id: Long = 0L, text: String)

@Singleton
class MessageRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  private val db = dbConfigProvider.get[JdbcProfile].db

  private class MessageTable(tag: Tag) extends Table[Message](tag, "messages") {
    def id      = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def text  = column[String]("text")
    def * = (id, text).mapTo[Message]
  }

  private val messages = TableQuery[MessageTable]

  def list(): Future[Seq[Message]] = db.run {
    messages.result
  }

  def create(text: String): Future[Message] = db.run {
    // https://github.com/bizreach/slick-reference/blob/master/3.x.md
    // https://scala-slick.org/doc/3.0.0/queries.html#inserting
    (messages.map(m => (m.text))
      returning messages.map(_.id)
      into ((message, id) => Message(id, message))
    ) += (text)
  }
}
