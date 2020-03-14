package models

import org.scalatestplus.play._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._

class MessageRepositorySpec extends PlaySpec with ModelSpecHelper {
  "MessageRepository#create" should {
    "create message" in withMysql { dbConfigProvider =>
      val model = new MessageRepository(dbConfigProvider)
      val created = Await.result(model.create("test message!"), Duration.Inf)
      created must equal(Message(1, "test message!"))
    }
  }

  "MessageRepository#list" should {
    "list messages" in withMysql { dbConfigProvider =>
      val model = new MessageRepository(dbConfigProvider)
      Await.result(model.create("test message!"), Duration.Inf)
      val listed = Await.result(model.list(), Duration.Inf)
      listed must contain(Message(1, "test message!"))
    }
  }
}
