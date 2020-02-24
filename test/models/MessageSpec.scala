package models

import org.scalatest._
import org.scalatestplus.play._
import play.api.test._
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.db.evolutions._
import play.api.Configuration
import play.api.db.slick.DatabaseConfigProvider
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._
import slick.jdbc.JdbcProfile
import com.redis._

trait MysqlDatabaseBuilder extends BeforeAndAfterAll  { this: Suite =>
  // create database
  // drop database
  // Database.forURL("jdbc:mysql://mysql:3306/?useSSL=false")
  override def beforeAll(): Unit = {
    println(11111)
    super.beforeAll()
  }

  override def afterAll(): Unit = {
    println(44444)
    super.afterAll()
  }
}

trait MysqlTableBuilder extends BeforeAndAfterEach { this: Suite =>
  // create table with evolution
  // drop table with evolution
  // private val port = config.get[Int]("redis.port")
  // https://stackoverflow.com/questions/20247742/slick-create-database
  // https://www.playframework.com/documentation/ja/2.4.x/ScalaTestingWithDatabases
  val app = new GuiceApplicationBuilder().build()
  val dbConfigProvider = app.injector.instanceOf[DatabaseConfigProvider]
  val config = app.injector.instanceOf[Configuration]
  println(config.get[String]("slick.dbs.default.db.url"))
  // val db = dbConfigProvider.get[JdbcProfile].db
  override def beforeEach(): Unit = {
    println(22222)
    super.beforeEach()
  }

  override def afterEach(): Unit = {
    println(33333)
    // Evolutions.cleanupEvolutions(db)
    super.afterEach()
  }
}

class MessageSpec extends PlaySpec with MysqlDatabaseBuilder with MysqlTableBuilder {
  "Message#create" should {
    "create message" in {
      // val model = new MessageRepository(dbConfigProvider)
      // val created = Await.result(model.create("test message!"), Duration.Inf)
      // created must equal (Message(1, "test message!"))
    }
  }

  // "Message#list" should {
  //   "list messages" in {
  //     val model = new MessageRepository(dbConfigProvider)
  //     Await.result(model.create("test message!"), Duration.Inf)
  //     val listed = Await.result(model.list(), Duration.Inf)
  //     listed must contain (Message(1, "test message!"))
  //   }
  // }
}
