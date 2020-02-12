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
