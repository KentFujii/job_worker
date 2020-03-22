package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import models._

@Singleton
class QueueController @Inject()(
  queueRepo: QueueRepository,
  cc: ControllerComponents
) extends AbstractController(cc) {
  // curl -X POST -H 'Content-Type:application/json' -d '{"text": "This is a message!!!"}' http://localhost:9000/queues
  def enqueue(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    val text = (request.body.asJson.get \ "text").as[String]
    queueRepo.enqueue(text)
    Ok(Json.toJson(Map("status" -> 200)))
  }
}
