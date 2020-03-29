package models

import javax.inject._
import scala.concurrent.{ Future, ExecutionContext }
import java.sql.Timestamp
import play.api.Configuration
import slick.jdbc.MySQLProfile.api._

case class Message(id: Long = 0L, text: String, createdAt: Timestamp, updatedAt: Timestamp)

@Singleton
class MessageRepository @Inject()(config: Configuration)(implicit ec: ExecutionContext) {
  private val url = config.get[String]("slick.dbs.default.db.url")
  private val driver = config.get[String]("slick.dbs.default.db.profile")
  private val user = config.get[String]("slick.dbs.default.db.user")
  private val password = config.get[String]("slick.dbs.default.db.password")
  private val db = Database.forURL(
    url,
    driver = driver,
    user = user,
    password = password,
    executor = AsyncExecutor(
      "job_worker",
      minThreads = 20,
      maxThreads = 20,
      queueSize = 20,
      maxConnections = 20
    )
  )

  private class MessageTable(tag: Tag) extends Table[Message](tag, "messages") {
    def id   = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def text = column[String]("text")
    def createdAt = column[Timestamp]("created_at")
    def updatedAt = column[Timestamp]("updated_at")
    def *    = (id, text, createdAt, updatedAt).mapTo[Message]
  }
  private val messages = TableQuery[MessageTable]
  // スキーマ
  // https://gist.github.com/ugdark/ec614b23a8b08c8a5a5d6a57cc4619d5
  // コード
  // https://github.com/underscoreio/essential-slick-code
  // デザイン
  // https://railsguides.jp/active_record_basics.html#crud-%E3%83%87%E3%83%BC%E3%82%BF%E3%81%AE%E8%AA%AD%E3%81%BF%E6%9B%B8%E3%81%8D

  def all(): Future[Seq[Message]] = {
    val query = messages.result
    db.run(query)
  }

  def find(id: Long): Future[Option[Message]] = {
    val query = messages.filter(_.id === id).result.headOption
    db.run(query)
  }

  def create(text: String): Future[Int] = {
    val query = messages.map(_.text) += (text)
    db.run(query)
  }

  def update(id: Long, text: String): Future[Int] = {
    val query = messages.filter(_.id === id).map(_.text).update(text)
    db.run(query)
  }

  def destroy(id: Long): Future[Int] = {
    val query = messages.filter(_.id === id).delete
    db.run(query)
  }
}
