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
      created must equal(1)
    }
  }

  "MessageRepository#all" should {
    "list messages" in withMysql { config =>
      val model = new MessageRepository(config)
      Await.result(model.create("test message!"), Duration.Inf)
      val all = Await.result(model.all(), Duration.Inf)
      all must contain(Message(1L, "test message!"))
    }
  }
}
