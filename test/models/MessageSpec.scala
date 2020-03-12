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

// trait MysqlHelper extends BeforeAndAfter { this: Suite =>
//   override def after(): Unit = {
//     s"mysql -h mysql -uroot -ppassword -e 'drop database ${dbName};'".!
//   }
// }

// trait MysqlEachHelper extends BeforeAndAfterEach { this: Suite =>
//   val dbNameList = new ListBuffer[String]
//   val databaseNameBuilder = new StringBuilder
//   override def afterEach(): Unit = {
//     val dbName = s"job_worker_test_${Math.abs(Random.nextInt)}"
//     println(dbName)
//     s"mysql -h mysql -uroot -ppassword -e 'create database ${dbName};'".!
//     val mysqlConfig: Map[String, String] = Map(
//       "slick.dbs.default.profile"     -> "slick.jdbc.MySQLProfile$",
//       "slick.dbs.default.db.profile"  -> "com.mysql.jdbc.Driver",
//       "slick.dbs.default.db.url"      -> s"jdbc:mysql://mysql:3306/${dbName}?useSSL=false",
//       "slick.dbs.default.db.user"     -> "root",
//       "slick.dbs.default.db.password" -> "password"
//     )
//     val app = new GuiceApplicationBuilder().configure(mysqlConfig).build()
//     val dbConfigProvider = app.injector.instanceOf[DatabaseConfigProvider]
//     val db = app.injector.instanceOf[DBApi]
//   }
//   override def afterEach(): Unit = {
//     println(dbName)
//     db.database("default").withConnection { conn =>
//       conn.createStatement().executeUpdate("truncate table messages;")
//     }
//     super.afterEach()
//   }
// }

class MessageSpec extends PlaySpec {
  // afterEach {
  //   println(111111)
  //   // db.database("default").withConnection { conn =>
  //   //   conn.createStatement().executeUpdate("truncate table messages;")
  //   // }
  // }

  "Message#create" should {
    "create message" in {
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
      val db = app.injector.instanceOf[DBApi]
      val model = new MessageRepository(dbConfigProvider)
      val created = Await.result(model.create("test message!"), Duration.Inf)
      created must equal (Message(1, "test message!"))
      s"mysql -h mysql -uroot -ppassword -e 'drop database ${dbName};'".!
    }
  }

  "Message#list" should {
    "list messages" in {
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
      val db = app.injector.instanceOf[DBApi]
      val model = new MessageRepository(dbConfigProvider)
      Await.result(model.create("test message!"), Duration.Inf)
      val listed = Await.result(model.list(), Duration.Inf)
      listed must contain (Message(1, "test message!"))
      s"mysql -h mysql -uroot -ppassword -e 'drop database ${dbName};'".!
    }
  }
}
