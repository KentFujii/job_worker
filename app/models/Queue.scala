package models

import play.api.Configuration
import javax.inject._
import com.redis._

case class Queue(text: String)

@Singleton
class QueueRepository @Inject()(config: Configuration) {
  private val host = config.get[String]("redis.host")
  private val port = config.get[Int]("redis.port")

  def enqueue(text: String): Unit = {
    val r = new RedisClient(host, port)
    r.rpush("twitter", text)
  }

  def dequeue(text: String): Unit = {
    val r = new RedisClient("redis", 6379)
    val opt = r.blpop(1, "twitter")
    val (k, v) = opt.getOrElse((None, None))
  }
}
