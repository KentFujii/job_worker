package models

import slick.jdbc.MySQLProfile.api._


// https://github.com/playframework/play-samples/tree/2.8.x/play-scala-slick-example
// http://scala-slick.org/doc/3.3.1/
// https://github.com/underscoreio/essential-slick-code
case class Message(id: Long, text: String)

class MessageTable(tag: Tag) extends Table[Message](tag, "messages") {
  def id      = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def text  = column[String]("text")
  def * = (id, text).mapTo[Message]
}
