package tasks

// import javax.inject.Inject

import javax.inject._
import play.api.inject.SimpleModule
import play.api.inject._

import akka.actor.ActorSystem
import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import play.Logger
import com.redis._

class MessageTask extends SimpleModule(bind[MessageTaskScheduler].toSelf.eagerly())

@Singleton
class MessageTaskScheduler @Inject() (actorSystem: ActorSystem)(implicit executionContext: ExecutionContext) {
  actorSystem.scheduler.scheduleAtFixedRate(initialDelay = 0.seconds, interval = 1.seconds) { () =>
    val r = new RedisClient("redis", 6379)
    val opt = r.blpop(1, "job_worker")
    val (k, v) = opt.getOrElse((None, None))
    println(1111)
    println(k)
    println(v)
  }
}
