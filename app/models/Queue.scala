package models

import play.api.Configuration
import javax.inject._
import com.redis._

case class Queue(text: String)

@Singleton
class QueueRepository @Inject()(config: Configuration) {
  private val host = config.get[String]("redisclient.host")
  private val database = config.get[Int]("redisclient.database")
  private val port = config.get[Int]("redisclient.port")
  private val key = config.get[String]("redisclient.key")
  private val client = new RedisClient(host, port, database)

  def enqueue(text: String): Option[Long] = {
    client.rpush(key, text)
  }

  def dequeue(): Option[String] = {
    client.rpop(key)
  }
}
