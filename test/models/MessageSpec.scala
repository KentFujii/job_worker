package models

import org.scalatest._
import org.scalatestplus.play._
import play.api.test._
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.db.slick.DatabaseConfigProvider
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._
import com.redis._

trait MysqlBuilder extends BeforeAndAfterEach { this: Suite =>
  // https://www.playframework.com/documentation/2.8.x/ScalaTestingWithGuice
  // val mysqlConfig: Map[String, String] = Map(
  //   "slick.dbs.default.profile"     -> "slick.jdbc.MySQLProfile$",
  //   "slick.dbs.default.db.profile" -> "com.mysql.jdbc.Driver",
  //   "slick.dbs.default.db.url"      -> s"jdbc:mysql://mysql:3306/job_worker?useSSL=false",
  //   "slick.dbs.default.db.user"     -> "root",
  //   "slick.dbs.default.db.password" -> "password"
  // )
  val app = new GuiceApplicationBuilder().configure(mysqlConfig).build()
  val dbConfigProvider = app.injector.instanceOf[DatabaseConfigProvider]

  override def beforeEach(): Unit = {
    println(11111111)
    super.beforeEach()
  }

  override def afterEach(): Unit = {
    println(22222222)
    super.afterEach()
  }
}

class MessageSpec extends PlaySpec with MysqlBuilder {
  "Message#create" should {
    "create message" in {
      val model = new MessageRepository(dbConfigProvider)
      val created = Await.result(model.create("test message!"), Duration.Inf)
      created must equal (Message(1, "test message!"))
    }
  }

  "Message#list" should {
    "list messages" in {
      val model = new MessageRepository(dbConfigProvider)
      Await.result(model.create("test message!"), Duration.Inf)
      val listed = Await.result(model.list(), Duration.Inf)
      listed must contain (Message(1, "test message!"))
    }
  }
}
