package models

import play.api.inject.guice.GuiceApplicationBuilder
import play.api.db.slick.DatabaseConfigProvider
import play.api.Configuration
import scala.util.Random
import sys.process._


trait ModelSpecHelper {
  def withMysql(testCode: DatabaseConfigProvider => Unit): Unit = {
    val dbName = s"job_worker_test_${Math.abs(Random.nextInt)}"
    s"mysql -h mysql -uroot -ppassword -e 'create database ${dbName};'".!
    val mysqlConfig: Map[String, String] = Map(
      "mysql.profile"  -> "slick.jdbc.MySQLProfile$",
      "mysql.driver"   -> "com.mysql.jdbc.Driver",
      "mysql.url"      -> s"jdbc:mysql://mysql:3306/${dbName}?useSSL=false",
      "mysql.user"     -> "root",
      "mysql.password" -> "password"
    )
    // val app = new GuiceApplicationBuilder().configure(mysqlConfig).build()
    // val dbConfigProvider = app.injector.instanceOf[DatabaseConfigProvider]
    // try {
    //   testCode(dbConfigProvider)
    // }
    // finally s"mysql -h mysql -uroot -ppassword -e 'drop database ${dbName};'".!!
  }

  def withRedis(testCode: Configuration => Unit): Unit = {
    val keyName = s"job_worker_${Math.abs(Random.nextInt)}"
    val redisConfig: Map[String, Any] = Map(
      "redis.host" -> "redis",
      "redis.database" -> 0,
      "redis.port" -> 6379,
      "redis.key" -> keyName
    )
    val app = new GuiceApplicationBuilder().configure(redisConfig).build()
    val config = app.injector.instanceOf[Configuration]
    try {
      testCode(config)
    }
    finally s"redis-cli -h redis -n 0 -p 6379 del ${keyName}".!!
  }
}
