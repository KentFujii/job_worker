package tasks

import javax.inject.Inject

import akka.actor.ActorSystem

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import play.Logger

class CodeBlockTask @Inject() (actorSystem: ActorSystem)(implicit executionContext: ExecutionContext) {
  actorSystem.scheduler.schedule(initialDelay = 10.seconds, interval = 1.minute) {
    Logger.debug("Executing something...");
  }
}
