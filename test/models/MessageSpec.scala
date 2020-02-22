package models

import org.scalatest._
import org.scalatestplus.play._
import play.api.test._
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.db.slick.DatabaseConfigProvider
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.util.Random
import com.redis._

class MessageSpec extends PlaySpec {
  "Message#create" should {
    "create message" in {
      val h2Config: Map[String, String] = Map(
        "slick.dbs.default.profile"     -> "slick.jdbc.H2Profile$",
        "slick.dbs.default.db.url"      -> s"jdbc:h2:mem:${Random.nextInt};MODE=MYSQL",
        "slick.dbs.default.db.user"     -> "root",
        "slick.dbs.default.db.password" -> "password"
      )
      val app = new GuiceApplicationBuilder().configure(h2Config).build()
      val dbConfigProvider = app.injector.instanceOf[DatabaseConfigProvider]
      val model = new MessageRepository(dbConfigProvider)
      val created = Await.result(model.create("test message!"), Duration.Inf)
      created must equal (Message(1, "test message!"))
    }
  }

  "Message#list" should {
    "list messages" in {
      val h2Config: Map[String, String] = Map(
        "slick.dbs.default.profile"     -> "slick.jdbc.H2Profile$",
        "slick.dbs.default.db.url"      -> s"jdbc:h2:mem:${Random.nextInt};MODE=MYSQL",
        "slick.dbs.default.db.user"     -> "root",
        "slick.dbs.default.db.password" -> "password"
      )
      val app = new GuiceApplicationBuilder().configure(h2Config).build()
      val dbConfigProvider = app.injector.instanceOf[DatabaseConfigProvider]
      val model = new MessageRepository(dbConfigProvider)
      Await.result(model.create("test message!"), Duration.Inf)
      val listed = Await.result(model.list(), Duration.Inf)
      listed must contain (Message(1, "test message!"))
    }
  }
}
