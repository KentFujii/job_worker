package models

import play.api.Configuration
import javax.inject._
import com.redis._

case class Queue(text: String)

@Singleton
class QueueRepository @Inject()(config: Configuration) {
  private val host = config.get[String]("redis.host")
  private val database = config.get[Int]("redis.database")
  private val port = config.get[Int]("redis.port")
  private val key = config.get[String]("redis.key")
  private val client = new RedisClient(host, port, database)

  def enqueue(text: String): Option[Long] = {
    client.rpush(key, text)
  }

  def dequeue(): Option[String] = {
    client.rpop(key)
  }
}
