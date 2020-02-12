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

class MessageSpec extends PlaySpec with MockitoSugar {
  "Message#list" should {
    "Return messages" in {
      // DatabaseConfigProvider
      // println(Configuration.reference)
      // implicit override val app = new GuiceApplicationBuilder()
      //   .configure(
      //     Configuration.from(
      //       Map(
      //           "slick.dbs.default.profile" -> "slick.jdbc.H2Profile$",
      //           "slick.dbs.default.db.profile" -> "org.h2.Driver",
      //           "slick.dbs.default.db.url" -> "jdbc:h2:mem:play",
      //           "slick.dbs.default.db.user" -> "root",
      //           "slick.dbs.default.db.password" -> "password"
      //       )
      //     )
      //   )
      //   .in(Mode.Test)
      //   .build
      val app = new GuiceApplicationBuilder().build
      val dbConfigProvider = app.injector.instanceOf[DatabaseConfigProvider]
      val dbConfig = dbConfigProvider.get[JdbcProfile]
      val db = dbConfig.db
      println(db)
      // https://www.playframework.com/documentation/2.8.x/ScalaTestingWithDatabases
      // https://www.playframework.com/documentation/2.8.x/ScalaTestingWithSpecs2
      // https://www.playframework.com/documentation/2.8.x/Developing-with-the-H2-Database
      // https://www.playframework.com/documentation/2.8.x/PlaySlick
      // https://github.com/playframework/playframework/blob/master/documentation/manual/working/scalaGuide/main/tests/code/tests/guice/ScalaGuiceApplicationBuilderSpec.scala
      // https://blog.karumi.com/testing-with-h2-in-play-framework/
      // slick.dbs.test.profile = "slick.jdbc.H2Profile$"
      // slick.dbs.default.db.profile = "org.h2.Driver"
      // slick.dbs.default.db.url = "jdbc:h2:mem:play;MODE=MYSQL"
      // slick.dbs.default.db.user = "root"
      // slick.dbs.default.db.password = "password"
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

  "Message#create" should {
    "Receive mocks" in {
      val mockDatabaseConfigProvider = mock[DatabaseConfigProvider](RETURNS_MOCKS)
      val mockDatabaseConfig = mock[DatabaseConfig[JdbcProfile]](RETURNS_MOCKS)
      val mockDatabaseDef = mock[DatabaseDef]
      doReturn(mockDatabaseConfig).when(mockDatabaseConfigProvider).get[JdbcProfile]
      doReturn(mockDatabaseDef).when(mockDatabaseConfig).db
      verify(mockDatabaseDef).run(any[DBIO[Seq[Message]]])
      val model = new MessageRepository(mockDatabaseConfigProvider)
      model.create("test message!")
    }
  }
}
