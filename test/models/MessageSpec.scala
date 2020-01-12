package models

import org.scalatestplus.play._
import play.api.mvc._
import play.api.test._
import play.api.test.Helpers._

import play.api.inject.guice.GuiceApplicationBuilder
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._

class MessageSpec extends PlaySpec {
  "Message#list" should {
    "Access " in {
      // https://www.playframework.com/documentation/ja/2.4.x/ScalaTestingWithScalaTest
      // http://www.scalatest.org/user_guide/testing_with_mock_objects
      // https://github.com/playframework/play-samples/tree/2.8.x/play-scala-slick-example
      // https://blog.karumi.com/testing-with-h2-in-play-framework/
      // http://unittesting1.blogspot.com/2015/07/mocking-database-with-slick-in.html
      // TODO DatabaseConfigProviderをmockする
      val app = new GuiceApplicationBuilder().build
      val dbConfigProvider = app.injector.instanceOf[DatabaseConfigProvider]
      val model = new MessageRepository(dbConfigProvider)
      Await.result(model.create("test message!!"), 2.seconds)
      val messages = Await.result(model.list(), 2.seconds)
      println(messages)
    }
  }
}
