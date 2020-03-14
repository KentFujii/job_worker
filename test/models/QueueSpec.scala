package models

import org.scalatestplus.play._

class QueueRepositorySpec extends PlaySpec with ModelSpecHelper {
  "QueueRepository#enqueue" should {
    "enqueue value" in withRedis { config =>
      val model = new QueueRepository(config)
      val enqueued = model.enqueue("test message!")
      enqueued must equal(Some(1))
    }
  }

  "QueueRepository#dequeue" should {
    "dequeue value" in withRedis { config =>
      val model = new QueueRepository(config)
      model.enqueue("test message!")
      val dequeued = model.dequeue
      dequeued must equal(Some("test message!"))
    }
  }
}
