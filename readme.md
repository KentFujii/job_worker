## getting started

Getting Scala 2.12.10 (for sbt)...

1. Run `sbt new playframework/play-scala-seed.g8`
2. Change into the top level project directory.
3. Enter `sbt run` to download dependencies and start the system.
4. In a browser enter `http://localhost:9000/health` to healthcheck.

### docker

Run `docker-compose up`

### test

RUN `docker-compose exec job_worker sbt test`

### memo

https://blog.karumi.com/testing-with-h2-in-play-framework/

https://www.playframework.com/documentation/2.8.x/ScalaTestingYourApplication

https://github.com/underscoreio/essential-slick-code

https://github.com/bizreach/slick-reference/blob/master/3.x.md

https://github.com/debasishg/scala-redis/blob/f89a2221bd1b692dac38826fa0dbe5ae6185c30a/src/test/scala/com/redis/api/BaseApiSpec.scala

Physically a Repository needs integrated tests
