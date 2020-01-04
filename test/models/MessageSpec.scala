package models

import org.scalatestplus.play._
import play.api.mvc._
import play.api.test._
import play.api.test.Helpers._

// import play.api.inject.guice.GuiceApplicationBuilder
// import play.api.db.slick.DatabaseConfigProvider
// import slick.jdbc.MySQLProfile.api._
// import slick.jdbc.JdbcProfile
// import scala.concurrent.Await
// import scala.concurrent.duration._
// import scala.concurrent.ExecutionContext.Implicits.global
// import scala.language.postfixOps

class MessageSpec extends PlaySpec {
  "Message#list" should {
    "Access real database" in {
      // val app = new GuiceApplicationBuilder().build
      // val dbConfigProvider = app.injector.instanceOf[DatabaseConfigProvider]
      // val dbConfig = dbConfigProvider.get[JdbcProfile]
      // val db = dbConfig.db
      // val messages = TableQuery[MessageTable]
      // // def exec[T](program: DBIO[T]): T = Await.result(db.run(program), 2 seconds)
      // val exec = db.run {
      //   val moreMessages = messages.map(m => (m.text)) += ("Hello, HAL. Do you read me, HAL?")
      //   moreMessages
      // }
      // Await.result(exec, 2 seconds)

      // val messages = TableQuery[MessageRepository]
      // val insertMessages = messages.map(m => (m.text)) ++= Seq(
      //   ("Hello, HAL. Do you read me, HAL?"),
      //   ("Affirmative, Dave. I read you."),
      //   ("Open the pod bay doors, HAL."),
      //   ("I'm sorry, Dave. I'm afraid I can't do that.")
      // )
      // assert(messages.result.statements.mkString == "select `id`, `text` from `messages`")
      // assert(insertMessages.statements.mkString == "insert into `messages` (`text`)  values (?)")
    }
  }

  // "MessageRepository#test" should {
  //   "Test" in {
  //     // https://stackoverflow.com/questions/34864093/is-it-possible-to-mock-db-connection-in-play-functional-testing-and-how
  //     // https://www.playframework.com/documentation/ja/2.4.x/ScalaTestingWithScalaTest
  //     // https://www.playframework.com/documentation/2.8.x/Evolutions
  //     // val app = new GuiceApplicationBuilder().build
  //     // val dbConfigProvider = app.injector.instanceOf[DatabaseConfigProvider]
  //     // val dbConfig = dbConfigProvider.get[JdbcProfile]
  //     // val db = dbConfig.db
  //     // val messages = TableQuery[MessageTable]
  //     // println(messages.schema.createStatements.mkString)
  //     // val sampleText = messages.filter(_.text === "sample text!!!")
  //     // println(sampleText.result.statements.mkString)
  //     // def exec[T](program: DBIO[T]): T = Await.result(db.run(program), 2 seconds)
  //     // exec(sampleText.result).foreach { println }
  //     // val freshTestData = Seq(
  //     //   ("Hello, HAL. Do you read me, HAL?"),
  //     //   ("Affirmative, Dave. I read you."),
  //     //   ("Open the pod bay doors, HAL."),
  //     //   ("I'm sorry, Dave. I'm afraid I can't do that.")
  //     // )
  //     // val moreMessages = messages.map(m => (m.text)) ++= freshTestData
  //     // exec(moreMessages)
  //     // exec(messages.result).foreach { println }
  //   }
  // }
}
