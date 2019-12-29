package models

import org.scalatestplus.play._
import play.api.mvc._
import play.api.test._
import play.api.test.Helpers._

import play.api.inject.guice.GuiceApplicationBuilder
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.JdbcProfile

// class MessageSpec extends PlaySpec with MockitoSugar {
class MessageSpec extends PlaySpec {
  "Message#list" should {
    val app = new GuiceApplicationBuilder().build
    val dbConfigProvider = app.injector.instanceOf[DatabaseConfigProvider]
    val dbConfig = dbConfigProvider.get[JdbcProfile]
    println(111)
    println(dbConfig)
    // def freshTestData = Seq(
    //   Message("Hello, HAL. Do you read me, HAL?"),
    //   Message("Affirmative, Dave. I read you."),
    //   Message("Open the pod bay doors, HAL."),
    //   Message("I'm sorry, Dave. I'm afraid I can't do that.")
    // )
    // println(11111)
    // val messages = TableQuery[MessageTable]
    // messages ++= freshTestData
    // val halSays = messages.filter(_.text === "HAL")
    // println(halSays)
  }
}
