package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._

@Singleton
class HealthController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def health() = Action {
    Ok(Json.toJson(Map("status" -> 200)))
  }
}
