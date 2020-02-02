package models

import org.scalatest._
import org.scalatestplus.play._
import org.scalatestplus.mockito.MockitoSugar
import play.api.test._
import org.mockito.Mockito._
import org.mockito.ArgumentMatchers._
import org.mockito.ArgumentCaptor

import play.api.db.slick.DatabaseConfigProvider
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile
import slick.jdbc.JdbcBackend.DatabaseDef
import scala.concurrent.ExecutionContext.Implicits.global
import slick.jdbc.JdbcProfile
import slick.dbio.{ DBIO, DBIOAction, NoStream, Effect }
import slick.jdbc.MySQLProfile.QueryActionExtensionMethodsImpl

class MessageSpec extends PlaySpec with MockitoSugar {
  "Message#list" should {
    "Return messages" in {
      val mockDatabaseConfigProvider = mock[DatabaseConfigProvider](RETURNS_MOCKS)
      val mockDatabaseConfig = mock[DatabaseConfig[JdbcProfile]](RETURNS_MOCKS)
      val mockDatabaseDef = mock[DatabaseDef]
      // val captor = ArgumentCaptor.forClass(classOf[DBIO[Seq[Message]]])
      val captor = ArgumentCaptor.forClass(classOf[DBIOAction[Seq[Message], NoStream, Effect.Read]])
      doReturn(mockDatabaseConfig).when(mockDatabaseConfigProvider).get[JdbcProfile]
      doReturn(mockDatabaseDef).when(mockDatabaseConfig).db
      // verify(mockDatabaseDef).run(any[DBIO[Seq[Message]]])
      // https://javadoc.io/static/com.typesafe.slick/slick_2.12/3.3.1/index.html
      // https://javadoc.io/static/com.typesafe.slick/slick_2.12/3.3.1/slick/jdbc/JdbcActionComponent.html
      val model = new MessageRepository(mockDatabaseConfigProvider)
      model.list()
      verify(mockDatabaseDef).run(captor.capture())
      // class slick.jdbc.JdbcActionComponent$QueryActionExtensionMethodsImpl$$anon$2
      // slick.jdbc.JdbcActionComponent$QueryActionExtensionMethodsImpl$$anon$2
      // statements, statements, slick$jdbc$StreamingInvokerAction$$super$getDumpInfo, overrideStatements, overrideStatements
      // println(classOf[DBIOAction[Seq[Message], NoStream, Effect]].getMethods.map(_.getName).mkString(", "))
      println(captor.getValue.statements)
      // println(SqlActionComponent.getClass)
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
