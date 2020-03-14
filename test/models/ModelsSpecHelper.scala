package models

import play.api.inject.guice.GuiceApplicationBuilder
import play.api.db.slick.DatabaseConfigProvider
import scala.util.Random
import sys.process._


trait ModelSpecHelper {
  def withMysql(testCode: DatabaseConfigProvider => Unit): Unit = {
    val dbName = s"job_worker_test_${Math.abs(Random.nextInt)}"
    s"mysql -h mysql -uroot -ppassword -e 'create database ${dbName};'".!
    val mysqlConfig: Map[String, String] = Map(
      "slick.dbs.default.profile"     -> "slick.jdbc.MySQLProfile$",
      "slick.dbs.default.db.profile"  -> "com.mysql.jdbc.Driver",
      "slick.dbs.default.db.url"      -> s"jdbc:mysql://mysql:3306/${dbName}?useSSL=false",
      "slick.dbs.default.db.user"     -> "root",
      "slick.dbs.default.db.password" -> "password"
    )
    val app = new GuiceApplicationBuilder().configure(mysqlConfig).build()
    val dbConfigProvider = app.injector.instanceOf[DatabaseConfigProvider]
    try {
      testCode(dbConfigProvider)
    }
    finally s"mysql -h mysql -uroot -ppassword -e 'drop database ${dbName};'".!
  }
}
