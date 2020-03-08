FROM openjdk:8
ENV SBT_VERSION 1.3.3
RUN curl -L -o sbt-$SBT_VERSION.deb https://dl.bintray.com/sbt/debian/sbt-$SBT_VERSION.deb && \
  dpkg -i sbt-$SBT_VERSION.deb && \
  rm sbt-$SBT_VERSION.deb
RUN apt-get update && \
  apt-get install -y sbt && \
  apt-get install -y default-mysql-client && \
  apt-get install -y redis-server && \
  apt-get clean

WORKDIR /job_worker
ADD build.sbt /job_worker/build.sbt
ADD project /job_worker/project/
RUN sbt compile
ADD . /job_worker
EXPOSE 9000
