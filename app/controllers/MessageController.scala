package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import models._
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.util.{ Try, Success, Failure }
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class MessageController @Inject()(repo: MessageRepository, cc: ControllerComponents) extends AbstractController(cc) {
  // curl http://localhost:9000/messages
  // https://www.playframework.com/documentation/2.8.x/ScalaJsonHttp
  def index(): Action[AnyContent] = Action {
    println(Await.result(repo.all(), Duration.Inf))
    Ok(Json.toJson(Map("status" -> 200)))
  }

  // curl http://localhost:9000/messages/1
  def show(id: Long): Action[AnyContent] = Action {
    // println(Await.result(repo.find(id), Duration.Inf).get)
    implicit val messageWrites = Json.writes[Message]
    val message = Await.result(repo.find(id), Duration.Inf).get
    val messageJson: JsValue = Json.toJson(message)
    println(messageJson)
    // https://qiita.com/miyatin0212/items/fdfe3c6141323ae281c3
    Ok(Json.toJson(Map("status" -> 200)))
  }

  // curl -X POST -H 'Content-Type:application/json' -d '{"text": "This is a message!!!"}' http://localhost:9000/messages
  def create(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    val text = (request.body.asJson.get \ "text").as[String]
    repo.create(text)
    Ok(Json.toJson(Map("status" -> 200)))
  }

  // curl -X PUT -H 'Content-Type:application/json' -d '{"text": "This is a message???"}' http://localhost:9000/messages/1
  def update(id: Long): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    val text = (request.body.asJson.get \ "text").as[String]
    repo.update(id, text)
    Ok(Json.toJson(Map("status" -> 200)))
  }

  // curl -X DELETE http://localhost:9000/messages/1
  def destroy(id: Long): Action[AnyContent] = Action {
    repo.destroy(id)
    Ok(Json.toJson(Map("status" -> 200)))
  }

  // def register() = Action { implicit request: Request[AnyContent] =>
  //   val r = new RedisClient("redis", 6379)
  //   r.lpush("twitter", request.body.asJson.get)
  //   Ok(Json.toJson(Map("status" -> 200)))
  // }
}
