#!/bin/bash
cd `dirname $0`/..

# docker pull mozilla/sbt
# docker run -it -v=$(pwd):/app -w /app  mozilla/sbt sbt
docker build -t job_worker -f ./Dockerfile  ./
docker run job_worker bash -c "sbt run" -p 9000:9000
