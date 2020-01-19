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
import slick.dbio.{ DBIOAction, NoStream }
import scala.concurrent.Future

class MessageSpec extends PlaySpec with MockitoSugar {
// class MessageSpec extends PlaySpec {
  "Message#list" should {
    "Return messages" in {
      // http://www.scalatest.org/user_guide/testing_with_mock_objects
      // https://github.com/playframework/play-samples/tree/2.8.x/play-scala-slick-example
      // https://blog.karumi.com/testing-with-h2-in-play-framework/
      // http://unittesting1.blogspot.com/2015/07/mocking-database-with-slick-in.html
      // https://www.playframework.com/documentation/2.8.x/ScalaTestingWithScalaTest
      // https://www.playframework.com/documentation/2.8.x/ScalaTestingWithGuice
      // TODO DatabaseConfigProviderをmockする
      // TODO mockにstubを仕込む
      // val app = new GuiceApplicationBuilder().build
      // val dbConfigProvider = app.injector.instanceOf[DatabaseConfigProvider]
      // val db = spy(dbConfigProvider.get[JdbcProfile].db)
      // https://github.com/TinkoffCreditSystems/ScalaDatabase/blob/master/src/test/scala/ru/tcsbank/utils/database/DatabaseTest.scala
      // https://stackoverflow.com/questions/34864093/is-it-possible-to-mock-db-connection-in-play-functional-testing-and-how
      val mockDatabaseConfigProvider = mock[DatabaseConfigProvider](RETURNS_MOCKS)
      val mockDatabaseConfig = mock[DatabaseConfig[JdbcProfile]](RETURNS_MOCKS)
      val mockDatabaseRef = mock[DatabaseDef]
      doReturn(mockDatabaseConfig).when(mockDatabaseConfigProvider).get[JdbcProfile]
      doReturn(mockDatabaseRef).when(mockDatabaseConfig).db
      val model = new MessageRepository(mockDatabaseConfigProvider)
      model.list()
      // println(mockDb)
      // doReturn(mockDb).when(mockDatabaseConfigProvider).db(any[DBIOAction[Seq[Message], NoStream, Nothing]])
      // val aaaaa = mockDatabaseConfigProvider.get[JdbcProfile]
      // println(aaaaa)
      // aaaaa.db returns "aaaaa"
      // val db = spy(mockDatabaseConfigProvider.get[JdbcProfile].db)
      // val result = Future.successful(Seq(Message(1, "test message!!")))
      // println(11111)
      // doReturn(result).when(db).run(any[DBIOAction[Seq[Message], NoStream, Nothing]])
      // println(22222)
      // val model = new MessageRepository(mockDatabaseConfigProvider)

      // println("!!!!!!! future !!!!!!!!")
      // println(model.list())
      // println(result)
      // println("!!!!!!! result !!!!!!!!")
      // println(Await.result(model.list(), 2.seconds))
      // println(Await.result(result, 2.seconds))
      // Await.result(model.create("test message!!"), 2.seconds)
      // val messages = Await.result(model.list(), 2.seconds)
      // println(messages)
      // val databaseConfigProvider = mock[DatabaseConfigProvider]
      // val db = spy(databaseConfigProvider.get[JdbcProfile].db)
      // println(databaseConfigProvider.get[JdbcProfile])
      // when(db.run(any[DBIOAction[Seq[Message], NoStream, Nothing]])).thenReturn(result)
      // doReturn(result).when(databaseConfigProvider).db
    }
  }
}
