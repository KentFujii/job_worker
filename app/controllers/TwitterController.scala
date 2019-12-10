package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._

@Singleton
class TwitterController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def enqueue() = Action { implicit request: Request[AnyContent] =>
    println(request.body)
    Ok(Json.toJson(Map("status" -> 200)))
  }

  // def newTask = Action(parse.formUrlEncoded) { implicit request =>
  //   Task.add(request.body.get("taskName").get.head)
  //   Redirect(routes.TaskTrackerController.index)
  // }
}
