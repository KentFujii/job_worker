package models

import javax.inject.{ Inject, Singleton }
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import scala.concurrent.{ Future, ExecutionContext }

@Singleton
class MessageRepository @Inject() (dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  private val dbConfig = dbConfigProvider.get[JdbcProfile]
  import dbConfig._
  import profile.api._
  private class MessagesTable(tag: Tag) extends Table[Message](tag, "messages") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def text = column[String]("text")
    def * = (id, text) <> ((Message.apply _).tupled, Message.unapply)
  }
  private val message = TableQuery[MessagesTable]

  def create(text: String): Future[Message] = db.run {
    (message.map(m => m.text)
      returning message.map(_.id)
      into ((text, id) => Message(id, text))
    ) += (text)
  }

  def list(): Future[Seq[Message]] = db.run {
    message.result
  }
}
