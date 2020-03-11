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
import scala.util.Random
import sys.process._

trait MySqlHelper extends BeforeAndAfterEach { this: Suite =>
  val app = new GuiceApplicationBuilder().build()
  val dbConfigProvider = app.injector.instanceOf[DatabaseConfigProvider]
  val db = app.injector.instanceOf[DBApi]
  // https://stackoverflow.com/questions/15338315/parallel-test-runner-for-play-framework
  // https://github.com/KentFujii/job_worker/blob/8acfb01775ac7a266a0242c11bab76551c6b39c4/test/models/MessageSpec.scala
  val dbName = s"job_worker_test_${Math.abs(Random.nextInt)}"
  println(11111)
  println(dbName)
  println(22222)
  val result = s"mysql -h mysql -uroot -ppassword -e 'create database ${dbName};'".!
  println(33333)
  println(result)
  println(44444)
  // override def afterEach(): Unit = {
  //   db.database("default").withConnection { conn =>
  //     conn.createStatement().executeUpdate("truncate table messages;")
  //   }
  //   super.afterEach()
  // }
}

class MessageSpec extends PlaySpec with MySqlHelper {
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
