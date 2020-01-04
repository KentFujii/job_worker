package tasks

import org.scalatestplus.play._
import play.api.mvc._
import play.api.test._
import play.api.test.Helpers._

class MessageTaskSpec extends PlaySpec {
  "TwitterTask" should {
    "Import twitter message into database" in {
      // https://stackoverflow.com/questions/34864093/is-it-possible-to-mock-db-connection-in-play-functional-testing-and-how
      // https://www.playframework.com/documentation/ja/2.4.x/ScalaTestingWithScalaTest
      // https://www.playframework.com/documentation/2.8.x/Evolutions
      // val app = new GuiceApplicationBuilder().build
      // val dbConfigProvider = app.injector.instanceOf[DatabaseConfigProvider]
      // val dbConfig = dbConfigProvider.get[JdbcProfile]
      // val db = dbConfig.db
      // val messages = TableQuery[MessageTable]
      // println(messages.schema.createStatements.mkString)
      // val sampleText = messages.filter(_.text === "sample text!!!")
      // println(sampleText.result.statements.mkString)
      // def exec[T](program: DBIO[T]): T = Await.result(db.run(program), 2 seconds)
      // exec(sampleText.result).foreach { println }
      // val freshTestData = Seq(
      //   ("Hello, HAL. Do you read me, HAL?"),
      //   ("Affirmative, Dave. I read you."),
      //   ("Open the pod bay doors, HAL."),
      //   ("I'm sorry, Dave. I'm afraid I can't do that.")
      // )
      // val moreMessages = messages.map(m => (m.text)) ++= freshTestData
      // exec(moreMessages)
      // exec(messages.result).foreach { println }
    }
  }
}
