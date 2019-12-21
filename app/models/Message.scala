package models

import play.api.libs.json._

case class Message(id: Long, text: String)

object Message {
  implicit val messageFormat = Json.format[Message]
}
