#!/bin/bash
cd `dirname $0`/..

sbt new playframework/play-scala-seed.g8
mv job_worker/* ./
rm job_worker
