package tasks

import javax.inject.Inject
import play.api.inject.SimpleModule
import play.api.inject._

import akka.actor.ActorSystem
import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import play.Logger

class TasksModule extends SimpleModule(bind[CodeBlockTask].toSelf.eagerly())

class CodeBlockTask @Inject() (actorSystem: ActorSystem)(implicit executionContext: ExecutionContext) {
  actorSystem.scheduler.schedule(initialDelay = 0.seconds, interval = 10.seconds) {
    Logger.debug("Executing something...");
  }
}
