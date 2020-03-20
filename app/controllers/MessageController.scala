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
  def index(): Action[AnyContent] = Action {
    println(Await.result(repo.all(), Duration.Inf))
    Ok(Json.toJson(Map("status" -> 200)))
  }

  def show(id: Int): Action[AnyContent] = Action {
    Ok(Json.toJson(Map("status" -> 200)))
  }

  // curl -X POST -H 'Content-Type:application/json' -d '{"text": "This is a message!!!"}' http://localhost:9000/messages
  def create(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    val text = (request.body.asJson.get \ "text").as[String]
    repo.create(text)
    Ok(Json.toJson(Map("status" -> 200)))
  }

  def update(id: Int): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(Json.toJson(Map("status" -> 200)))
  }

  def destroy(id: Int): Action[AnyContent] = Action {
    Ok(Json.toJson(Map("status" -> 200)))
  }

  // def register() = Action { implicit request: Request[AnyContent] =>
  //   val r = new RedisClient("redis", 6379)
  //   r.rpush("twitter", request.body.asJson.get)
  //   Ok(Json.toJson(Map("status" -> 200)))
  // }
}
