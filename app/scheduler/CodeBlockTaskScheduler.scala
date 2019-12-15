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
    // https://alvinalexander.com/scala/how-to-define-methods-return-multiple-items-tuples-scala
    // http://bach.istc.kobe-u.ac.jp/lect/ProLang/org/scala-list.html#sec-9
    println(opt)
  }
}
