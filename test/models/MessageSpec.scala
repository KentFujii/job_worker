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
// import slick.jdbc.JdbcActionComponent$QueryActionExtensionMethodsImpl
import scala.concurrent.Future

class MessageSpec extends PlaySpec with MockitoSugar {
  "Message#list" should {
    "Return messages" in {
      val mockDatabaseConfigProvider = mock[DatabaseConfigProvider](RETURNS_MOCKS)
      val mockDatabaseConfig = mock[DatabaseConfig[JdbcProfile]](RETURNS_MOCKS)
      val mockDatabaseDef = mock[DatabaseDef]
      when(mockDatabaseConfigProvider.get[JdbcProfile]).thenReturn(mockDatabaseConfig)
      when(mockDatabaseConfig.db).thenReturn(mockDatabaseDef)
      verify(mockDatabaseDef).run(any[DBIO[Seq[Message]]])
      val model = new MessageRepository(mockDatabaseConfigProvider)
      model.list()
    }
  }
}
