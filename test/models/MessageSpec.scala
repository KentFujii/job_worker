package models

import org.scalatestplus.play._
import play.api.mvc._
import play.api.test._
import play.api.test.Helpers._
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.JdbcProfile
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.language.postfixOps

class MessageSpec extends PlaySpec {
  "Message#list" should {
    "Access real database" in {
      val messages = TableQuery[MessageTable]
      val insertMessages = messages.map(m => (m.text)) ++= Seq(
        ("Hello, HAL. Do you read me, HAL?"),
        ("Affirmative, Dave. I read you."),
        ("Open the pod bay doors, HAL."),
        ("I'm sorry, Dave. I'm afraid I can't do that.")
      )
      assert(messages.result.statements.mkString == "select `id`, `text` from `messages`")
      assert(insertMessages.statements.mkString == "insert into `messages` (`text`)  values (?)")
    }
  }
}
