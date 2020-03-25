package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import models._
import scala.concurrent.Await
import scala.concurrent.duration._

@Singleton
class MessageController @Inject()(
  messageRepo: MessageRepository,
  queueRepo: QueueRepository,
  cc: ControllerComponents
) extends AbstractController(cc) {
  implicit val messageWrites = Json.writes[Message]

  // curl http://localhost:9000/messages
  def index(): Action[AnyContent] = Action {
    val messages = Await.result(messageRepo.all(), Duration.Inf)
    val messagesJson: JsValue = Json.toJson(messages)
    Ok(messagesJson)
  }

  // curl http://localhost:9000/messages/1
  def show(id: Long): Action[AnyContent] = Action {
    val message = Await.result(messageRepo.find(id), Duration.Inf).get
    val messageJson: JsValue = Json.toJson(message)
    Ok(messageJson)
  }

  // curl -X POST -H 'Content-Type:application/json' -d '{"text": "This is a message!!!"}' http://localhost:9000/messages
  def create(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    val text = (request.body.asJson.get \ "text").as[String]
    messageRepo.create(text)
    Ok(Json.toJson(Map("status" -> 200)))
  }

  // curl -X PUT -H 'Content-Type:application/json' -d '{"text": "This is a message???"}' http://localhost:9000/messages/1
  def update(id: Long): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    val text = (request.body.asJson.get \ "text").as[String]
    messageRepo.update(id, text)
    Ok(Json.toJson(Map("status" -> 200)))
  }

  // curl -X DELETE http://localhost:9000/messages/1
  def destroy(id: Long): Action[AnyContent] = Action {
    messageRepo.destroy(id)
    Ok(Json.toJson(Map("status" -> 200)))
  }
}
