package models

import org.scalatest._
import org.scalatestplus.play._
import org.scalatestplus.mockito.MockitoSugar
import play.api.mvc._
import play.api.test._
import play.api.test.Helpers._
import org.mockito.Mockito._
import org.mockito.ArgumentMatchers._

import play.api.inject.guice.GuiceApplicationBuilder
import play.api.db.slick.DatabaseConfigProvider
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile
import slick.jdbc.JdbcBackend.DatabaseDef
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._
import slick.jdbc.JdbcProfile
import slick.dbio.DBIO
import scala.concurrent.Future

class MessageSpec extends PlaySpec with MockitoSugar {
// class MessageSpec extends PlaySpec {
  "Message#list" should {
    "Return messages" in {
      val mockDatabaseConfigProvider = mock[DatabaseConfigProvider](RETURNS_MOCKS)
      val mockDatabaseConfig = mock[DatabaseConfig[JdbcProfile]](RETURNS_MOCKS)
      val mockDatabaseDef = mock[DatabaseDef](RETURNS_SMART_NULLS)
      when(mockDatabaseConfigProvider.get[JdbcProfile]).thenReturn(mockDatabaseConfig)
      when(mockDatabaseConfig.db).thenReturn(mockDatabaseDef)
      when(mockDatabaseDef.run(any[DBIO[Seq[Message]]])).thenReturn(Future(Seq(Message(1, "test message!"), Message(2, "test message!!"))))
      val model = new MessageRepository(mockDatabaseConfigProvider)
      // https://github.com/slick/slick/blob/7944c7407093f3f5e767634f77575f629615f3b2/slick/src/main/scala/slick/basic/BasicBackend.scala#L74
      // https://github.com/slick/slick/blob/7944c7407093f3f5e767634f77575f629615f3b2/slick/src/main/scala/slick/basic/BasicBackend.scala#L128
      // https://github.com/slick/slick/blob/f5e7c6c70ba58c08a95d43413664a03586a6c2c8/slick/src/main/scala/slick/jdbc/JdbcBackend.scala#L56
      println(model.list())
      // slick.jdbc.JdbcActionComponent$QueryActionExtensionMethodsImpl$$anon$2@16c2c624
      // SmartNull returned by this unstubbed method call on a mock:
      // databaseDef.runInContext(
      //     slick.jdbc.JdbcActionComponent$QueryActionExtensionMethodsImpl$$anon$2@16c2c624,
      //     SmartNull returned by this unstubbed method call on a mock:
      // databaseDef.createDatabaseActionContext(
      //     false
      // );,
      //     false,
      //     true
      // );
      // val aaa = Await.result(model.list(), Duration.Inf)
      // println(aaa)
    }
  }
}
