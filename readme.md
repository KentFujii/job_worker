## getting started

Getting Scala 2.12.10 (for sbt)...

1. Run `sbt new playframework/play-scala-seed.g8`
2. Change into the top level project directory.
3. Enter `sbt run` to download dependencies and start the system.
4. In a browser enter `http://localhost:9000` to view the welcome page.

### docker

Run `docker-compose up`

### test

RUN `docker-compose exec job_worker sbt test`

## memo

`project/` 配下のディレクトリを消さないと次ビルド時が失敗する可能性がある

### twitter

https://developer.twitter.com/en/application/use-case

### redis

https://www.sraoss.co.jp/tech-blog/redis/redis-streams/

http://mayo.hatenablog.com/entry/2013/10/15/074237

https://reffect.co.jp/laravel/redis-pub-sub-dont-understand

### pub/sub

https://blog.tiqwab.com/2017/07/31/redis-pubsub.html

### mysql

https://www.playframework.com/documentation/2.7.x/PlaySlick

https://github.com/playframework/play-samples/tree/2.8.x/play-scala-slick-example

https://github.com/playframework/play-samples/tree/2.8.x/play-scala-isolated-slick-example

https://pedrorijo.com/blog/play-slick/

https://www.playframework.com/documentation/ja/2.4.x/ScalaTestingWithScalaTest#%E3%83%A2%E3%83%87%E3%83%AB%E3%81%AE%E3%83%A6%E3%83%8B%E3%83%83%E3%83%88%E3%83%86%E3%82%B9%E3%83%88

### other

https://medium.com/swlh/asynchronous-queueing-in-redis-with-akka-8b707b784bca

https://www.tutorialdocs.com/article/redis-stream-tutorial.html


https://dzone.com/articles/pubsub-redis-and-akka-actors

https://stackoverflow.com/questions/23608377/redis-pubsub-in-play


