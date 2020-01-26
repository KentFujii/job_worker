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
      // http://www.scalatest.org/user_guide/testing_with_mock_objects
      // https://github.com/playframework/play-samples/tree/2.8.x/play-scala-slick-example
      // https://blog.karumi.com/testing-with-h2-in-play-framework/
      // http://unittesting1.blogspot.com/2015/07/mocking-database-with-slick-in.html
      // https://www.playframework.com/documentation/2.8.x/ScalaTestingWithScalaTest
      // https://www.playframework.com/documentation/2.8.x/ScalaTestingWithGuice
      // https://github.com/TinkoffCreditSystems/ScalaDatabase/blob/master/src/test/scala/ru/tcsbank/utils/database/DatabaseTest.scala
      // https://stackoverflow.com/questions/34864093/is-it-possible-to-mock-db-connection-in-play-functional-testing-and-how
      // https://github.com/slick/slick/blob/42d787b4950fe876569b5fd68e98c4e0379ac83c/samples/slick-multidb/src/main/scala/basic/MultiDBExample.scala
      val mockDatabaseConfigProvider = mock[DatabaseConfigProvider](RETURNS_MOCKS)
      val mockDatabaseConfig = mock[DatabaseConfig[JdbcProfile]](RETURNS_MOCKS)
      val mockDatabaseDef = mock[DatabaseDef]
      val futureMessages = Future(Seq(Message(1, "test message!"), Message(2, "test message!!")))
      doReturn(mockDatabaseConfig).when(mockDatabaseConfigProvider).get[JdbcProfile]
      doReturn(mockDatabaseDef).when(mockDatabaseConfig).db
      doReturn(futureMessages).when(mockDatabaseDef).run(any[DBIO[Seq[Message]]])
      val model = new MessageRepository(mockDatabaseConfigProvider)
      // https://github.com/slick/slick/blob/7944c7407093f3f5e767634f77575f629615f3b2/slick/src/main/scala/slick/basic/BasicBackend.scala#L74
      // https://github.com/slick/slick/blob/7944c7407093f3f5e767634f77575f629615f3b2/slick/src/main/scala/slick/basic/BasicBackend.scala#L128
      https://github.com/slick/slick/blob/f5e7c6c70ba58c08a95d43413664a03586a6c2c8/slick/src/main/scala/slick/jdbc/JdbcBackend.scala#L56
      Await.result(model.list(), 2.seconds)
      // [info] - should Return messages *** FAILED ***
      // [info]   org.mockito.exceptions.misusing.WrongTypeOfReturnValue: Transformation cannot be returned by createDatabaseActionContext()
      // [info] createDatabaseActionContext() should return JdbcActionContext
      // [info] ***
      // [info] If you're unsure why you're getting above error read on.
      // [info] Due to the nature of the syntax above problem might occur because:
      // [info] 1. This exception *might* occur in wrongly written multi-threaded tests.
      // [info]    Please refer to Mockito FAQ on limitations of concurrency testing.
      // [info] 2. A spy is stubbed using when(spy.foo()).then() syntax. It is safer to stub spies -
      // [info]    - with doReturn|Throw() family of methods. More in javadocs for Mockito.spy() method.
      // [info]   at slick.basic.BasicBackend$DatabaseDef.runInternal(BasicBackend.scala:77)
      // [info]   at slick.basic.BasicBackend$DatabaseDef.runInternal$(BasicBackend.scala:76)
      // [info]   at slick.jdbc.JdbcBackend$DatabaseDef.runInternal(JdbcBackend.scala:37)
      // [info]   at slick.basic.BasicBackend$DatabaseDef.run(BasicBackend.scala:74)
      // [info]   at slick.basic.BasicBackend$DatabaseDef.run$(BasicBackend.scala:74)
      // [info]   at slick.jdbc.JdbcBackend$DatabaseDef.run(JdbcBackend.scala:37)
      // [info]   at models.MessageRepository.list(Message.scala:28)
      // [info]   at models.MessageSpec.$anonfun$new$2(MessageSpec.scala:46)
      // [info]   at org.scalatest.OutcomeOf.outcomeOf(OutcomeOf.scala:85)
      // [info]   at org.scalatest.OutcomeOf.outcomeOf$(OutcomeOf.scala:83)
      // [info]   ...
    }
  }
}
