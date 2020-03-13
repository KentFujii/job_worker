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
import sys.process._

class MessageSpec extends PlaySpec {
  def withDatabase(testCode: DatabaseConfigProvider => Unit): Unit = {
    val dbName = s"job_worker_test_${Math.abs(Random.nextInt)}"
    println(dbName)
    s"mysql -h mysql -uroot -ppassword -e 'create database ${dbName};'".!
    val mysqlConfig: Map[String, String] = Map(
      "slick.dbs.default.profile"     -> "slick.jdbc.MySQLProfile$",
      "slick.dbs.default.db.profile"  -> "com.mysql.jdbc.Driver",
      "slick.dbs.default.db.url"      -> s"jdbc:mysql://mysql:3306/${dbName}?useSSL=false",
      "slick.dbs.default.db.user"     -> "root",
      "slick.dbs.default.db.password" -> "password"
    )
    val app = new GuiceApplicationBuilder().configure(mysqlConfig).build()
    val dbConfigProvider = app.injector.instanceOf[DatabaseConfigProvider]
    try {
      testCode(dbConfigProvider)
    }
    finally s"mysql -h mysql -uroot -ppassword -e 'drop database ${dbName};'".!
  }

  "Message#create" should {
    "create message" in withDatabase { dbConfigProvider =>
      val model = new MessageRepository(dbConfigProvider)
      val created = Await.result(model.create("test message!"), Duration.Inf)
      created must equal (Message(1, "test message!"))
    }
  }

  "Message#list" should {
    "list messages" in withDatabase { dbConfigProvider =>
      val model = new MessageRepository(dbConfigProvider)
      Await.result(model.create("test message!"), Duration.Inf)
      val listed = Await.result(model.list(), Duration.Inf)
      listed must contain (Message(1, "test message!"))
    }
  }
}
