FROM mozilla/sbt
EXPOSE 9000
ADD . /job_worker
WORKDIR /job_worker
