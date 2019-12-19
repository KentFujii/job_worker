package tasks

import javax.inject.Inject
import play.api.inject.SimpleModule
import play.api.inject._

import akka.actor.ActorSystem
import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import play.Logger
import com.redis._

class TasksModule extends SimpleModule(bind[CodeBlockTask].toSelf.eagerly())

class CodeBlockTask @Inject() (actorSystem: ActorSystem)(implicit executionContext: ExecutionContext) {
  actorSystem.scheduler.schedule(initialDelay = 0.seconds, interval = 1.seconds) {
    val r = new RedisClient("redis", 6379)
    val opt = r.blpop(1, "twitter")
    val (k, v) = opt.getOrElse((None, None))
    println(k)
    println(v)
  }
}
