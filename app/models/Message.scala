package models

import javax.inject._
import scala.concurrent.{ Future, ExecutionContext }
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

  def list(): Future[Seq[Message]] = {
    val query = messages.result
    db.run {
      query
    }
  }

  def create(text: String): Future[Message] = {
    val query = (messages.map(m => (m.text))
      returning messages.map(_.id)
      into ((message, id) => Message(id, message))
    ) += (text)
    db.run {
      query
    }
  }
}
