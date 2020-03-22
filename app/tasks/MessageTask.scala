package tasks

// import javax.inject.Inject

import javax.inject._
import play.api.inject.SimpleModule
import play.api.inject._

import akka.actor.ActorSystem
import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import models._

class MessageTask extends SimpleModule(bind[MessageTaskScheduler].toSelf.eagerly())

@Singleton
class MessageTaskScheduler @Inject() (
  messageRepo: MessageRepository,
  queueRepo: QueueRepository,
  actorSystem: ActorSystem
)(implicit executionContext: ExecutionContext) {
  actorSystem.scheduler.scheduleAtFixedRate(initialDelay = 0.seconds, interval = 1.seconds) { () =>
    queueRepo.dequeue match {
      case Some(text) => messageRepo.create(text)
      case _ =>
    }
  }
}
