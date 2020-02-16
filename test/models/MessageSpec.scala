package models

import org.scalatest._
import org.scalatestplus.play._
import org.scalatestplus.mockito.MockitoSugar
import play.api.test._
import org.mockito.Mockito._
import org.mockito.ArgumentMatchers._

import play.api.inject.guice.GuiceApplicationBuilder
import play.api.Configuration
import play.api.Mode
import play.api.db.slick.DatabaseConfigProvider
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile
import slick.jdbc.JdbcBackend.DatabaseDef
import scala.concurrent.ExecutionContext.Implicits.global
import slick.jdbc.JdbcProfile
import slick.dbio.DBIO
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.util.Random
// import slick.jdbc.H2Profile.api._

class MessageSpec extends PlaySpec with MockitoSugar {
  "Message#list" should {
    "Return messages" in {
      val h2Config: Map[String, String] = Map(
        "slick.dbs.default.profile"     -> "slick.jdbc.H2Profile$",
        "slick.dbs.default.db.url"      -> s"jdbc:h2:mem:${Random.nextInt};MODE=MYSQL",
        "slick.dbs.default.db.user"     -> "root",
        "slick.dbs.default.db.password" -> "password"
      )

      val app = new GuiceApplicationBuilder().configure(h2Config).build()
      val dbConfigProvider = app.injector.instanceOf[DatabaseConfigProvider]
      val model = new MessageRepository(dbConfigProvider)
      Await.result(model.create("test message!!"), 2.seconds)
      val messages = Await.result(model.list(), 2.seconds)
      println(messages)
    }
  }

  "Message#create" should {
    "Return id" in {
      val h2Config: Map[String, Any] = Map(
        "slick.dbs.default.profile"     -> "slick.jdbc.H2Profile$",
        "slick.dbs.default.db.url"      -> "jdbc:h2:mem:create;MODE=MYSQL",
        "slick.dbs.default.db.user"     -> "root",
        "slick.dbs.default.db.password" -> "password"
      )

      val app = new GuiceApplicationBuilder().configure(h2Config).build()
      val dbConfigProvider = app.injector.instanceOf[DatabaseConfigProvider]
      val model = new MessageRepository(dbConfigProvider)
      Await.result(model.create("test message!!"), 2.seconds)
      val messages = Await.result(model.list(), 2.seconds)
      println(messages)
      // val db = dbConfig.db
      // println(db)
    }
  }


  // "Message#list" should {
  //   "Receive mocks" in {
  //     val mockDatabaseConfigProvider = mock[DatabaseConfigProvider](RETURNS_MOCKS)
  //     val mockDatabaseConfig = mock[DatabaseConfig[JdbcProfile]](RETURNS_MOCKS)
  //     val mockDatabaseDef = mock[DatabaseDef]
  //     doReturn(mockDatabaseConfig).when(mockDatabaseConfigProvider).get[JdbcProfile]
  //     doReturn(mockDatabaseDef).when(mockDatabaseConfig).db
  //     verify(mockDatabaseDef).run(any[DBIO[Seq[Message]]])
  //     val model = new MessageRepository(mockDatabaseConfigProvider)
  //     model.list()
  //   }
  // }

  // "Message#create" should {
  //   "Receive mocks" in {
  //     val mockDatabaseConfigProvider = mock[DatabaseConfigProvider](RETURNS_MOCKS)
  //     val mockDatabaseConfig = mock[DatabaseConfig[JdbcProfile]](RETURNS_MOCKS)
  //     val mockDatabaseDef = mock[DatabaseDef]
  //     doReturn(mockDatabaseConfig).when(mockDatabaseConfigProvider).get[JdbcProfile]
  //     doReturn(mockDatabaseDef).when(mockDatabaseConfig).db
  //     verify(mockDatabaseDef).run(any[DBIO[Seq[Message]]])
  //     val model = new MessageRepository(mockDatabaseConfigProvider)
  //     model.create("test message!")
  //   }
  // }
}
