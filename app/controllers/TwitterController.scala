package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import com.redis._

@Singleton
class TwitterController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def enqueue() = Action { implicit request: Request[AnyContent] =>
    val r = new RedisClient("redis", 6379)
    r.rpush("twitter", request.body.asJson.get)
    Ok(Json.toJson(Map("status" -> 200)))
  }
}