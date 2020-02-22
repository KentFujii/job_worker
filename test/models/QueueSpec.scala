package models

import org.scalatest._
import org.scalatestplus.play._
import play.api.test._
import play.api.inject.guice.GuiceApplicationBuilder

class QueueSpec extends PlaySpec {
  "Queue#enqueue" should {
    "create message" in {
      val redisConfig: Map[String, Any] = Map(
        "redis.host" -> "redis",
        "redis.port" -> 6379
      )
      val app = new GuiceApplicationBuilder().configure(redisConfig).build()
      val model = new QueueRepository(app.configuration)
      model.enqueue("test message!")
    }
  }
}
