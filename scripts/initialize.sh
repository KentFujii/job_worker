#!/bin/bash
cd `dirname $0`/..
rm -rf app
rm -rf conf
rm -rf logs
rm -rf project
rm -rf public
rm -rf target
rm -rf test
rm -rf build.sbt
sbt new playframework/play-scala-seed.g8
mv job_worker/* ./
rm -rf job_worker
docker-compose up --build
