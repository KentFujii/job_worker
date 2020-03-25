package models

import play.api.inject.guice.GuiceApplicationBuilder
import play.api.Configuration
import scala.util.Random
import sys.process._


trait ModelSpecHelper {
  def withMysql(testCode: Configuration => Unit): Unit = {
    val dbName = s"job_worker_test_${Math.abs(Random.nextInt)}"
    s"mysql -h mysql -uroot -ppassword -e 'create database ${dbName};'".!
    val slickConfig: Map[String, Any] = Map(
      "slick.dbs.default.profile" -> "slick.jdbc.MySQLProfile$",
      "slick.dbs.default.db.profile" -> "com.mysql.jdbc.Driver",
      "slick.dbs.default.db.url" -> s"jdbc:mysql://mysql:3306/${dbName}?useSSL=false",
      "slick.dbs.default.db.user" -> "root",
      "slick.dbs.default.db.password" -> "password"
    )
    val app = new GuiceApplicationBuilder().configure(slickConfig).build()
    val config = app.injector.instanceOf[Configuration]
    try {
      testCode(config)
    }
    finally s"mysql -h mysql -uroot -ppassword -e 'drop database ${dbName};'".!!
  }

  def withRedis(testCode: Configuration => Unit): Unit = {
    val keyName = s"job_worker_${Math.abs(Random.nextInt)}"
    val redisclientConfig: Map[String, Any] = Map(
      "redisclient.host" -> "redis",
      "redisclient.database" -> 0,
      "redisclient.port" -> 6379,
      "redisclient.key" -> keyName
    )
    val app = new GuiceApplicationBuilder().configure(redisclientConfig).build()
    val config = app.injector.instanceOf[Configuration]
    try {
      testCode(config)
    }
    finally s"redis-cli -h redis -n 0 -p 6379 del ${keyName}".!!
  }
}
