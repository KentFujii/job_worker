package models

import org.scalatest._
import org.scalatestplus.play._
import play.api.test._
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.db.DBApi
import play.api.db.slick.DatabaseConfigProvider
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._

trait DatabaseFlusher extends BeforeAndAfterEach { this: Suite =>
  val app = new GuiceApplicationBuilder().build()
  val dbConfigProvider = app.injector.instanceOf[DatabaseConfigProvider]
  val db = app.injector.instanceOf[DBApi]
  override def beforeEach(): Unit = {
    println(11111)
    super.beforeEach()
  }

  override def afterEach(): Unit = {
    // https://www.playframework.com/documentation/2.8.x/ScalaTestingWithDatabases
    // https://www.programcreek.com/scala/play.api.db.DBApi
    println(22222)
    // println(db.database("default"))
    db.database("default").withConnection { conn =>
      // conn.createStatement().executeQuery("truncate table messages;")
      conn.createStatement().executeQuery("select * from messages;")
    }
    super.afterEach()
  }
}

class MessageSpec extends PlaySpec with DatabaseFlusher {
  "Message#create" should {
    "create message" in {
      val model = new MessageRepository(dbConfigProvider)
      val created = Await.result(model.create("test message!"), Duration.Inf)
      created must equal (Message(1, "test message!"))
    }
  }

  // "Message#list" should {
  //   "list messages" in {
  //     val model = new MessageRepository(dbConfigProvider)
  //     // Await.result(model.create("test message!"), Duration.Inf)
  //     // val listed = Await.result(model.list(), Duration.Inf)
  //     // listed must contain (Message(1, "test message!"))
  //   }
  // }
}
