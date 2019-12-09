package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._
import play.api.libs.json.Json

class HealthControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {
  "HealthController GET" should {
    "Return the healthcheck json response from a new instance of controller" in {
      val controller = new HealthController(stubControllerComponents())
      val health = controller.health().apply(FakeRequest(GET, "/health"))
      val responseJson = Json.parse(contentAsString(health))

      status(health) mustBe OK
      contentType(health) mustBe Some("application/json")
      contentAsString(health) must include ("status")
      (responseJson \ "status").as[Int] mustBe (200)
    }

    "Return the healthcheck json response from the application" in {
      val controller = inject[HealthController]
      val health = controller.health().apply(FakeRequest(GET, "/health"))
      val responseJson = Json.parse(contentAsString(health))

      status(health) mustBe OK
      contentType(health) mustBe Some("application/json")
      contentAsString(health) must include ("status")
      (responseJson \ "status").as[Int] mustBe (200)
    }

    "Return the healthcheck json response from the router" in {
      val request = FakeRequest(GET, "/health")
      val health = route(app, request).get
      val responseJson = Json.parse(contentAsString(health))

      status(health) mustBe OK
      contentType(health) mustBe Some("application/json")
      contentAsString(health) must include ("status")
      (responseJson \ "status").as[Int] mustBe (200)
    }
  }
}
