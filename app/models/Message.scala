package models

import slick.jdbc.MySQLProfile.api._

case class Message(id: Long = 0L, text: String)

class MessageTable(tag: Tag) extends Table[Message](tag, "messages") {
  def id      = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def text  = column[String]("text")
  def * = (id, text).mapTo[Message]
}
