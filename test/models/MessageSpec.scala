package models

import org.scalatestplus.play._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._

class MessageRepositorySpec extends PlaySpec with ModelSpecHelper {
  "MessageRepository#create" should {
    "create message" in withMysql { config =>
      val model = new MessageRepository(config)
      val action = model.create("test message!")
      val created = Await.result(action, Duration.Inf)
      created must equal(Some(1))
    }
  }

  "MessageRepository#list" should {
    "list messages" in withMysql { config =>
      val model = new MessageRepository(config)
      Await.result(model.create("test message!"), Duration.Inf)
      val listed = Await.result(model.list(), Duration.Inf)
      listed must contain(Message("test message!", 1))
    }
  }
}
