package models

import org.scalatest._
import org.scalatestplus.play._
import org.scalatestplus.mockito.MockitoSugar
import play.api.test._
import org.mockito.Mockito._
import org.mockito.ArgumentMatchers._

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
      val mockDatabaseConfigProvider = mock[DatabaseConfigProvider](RETURNS_MOCKS)
      val mockDatabaseConfig = mock[DatabaseConfig[JdbcProfile]](RETURNS_MOCKS)
      val mockDatabaseDef = mock[DatabaseDef]
      doReturn(mockDatabaseConfig).when(mockDatabaseConfigProvider).get[JdbcProfile]
      doReturn(mockDatabaseDef).when(mockDatabaseConfig).db
      verify(mockDatabaseDef).run(any[DBIO[Seq[Message]]])
      val model = new MessageRepository(mockDatabaseConfigProvider)
      model.list()
    }
  }

  "Message#create" should {
    "Return messages" in {
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
