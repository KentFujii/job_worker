FROM openjdk:8
ENV SBT_VERSION 1.3.3
RUN curl -L -o sbt-$SBT_VERSION.deb https://dl.bintray.com/sbt/debian/sbt-$SBT_VERSION.deb && \
  dpkg -i sbt-$SBT_VERSION.deb && \
  rm sbt-$SBT_VERSION.deb
RUN apt-get update && \
  apt-get install -y sbt && \
  apt-get clean
ADD . /job_worker
WORKDIR /job_worker
CMD ["sbt", "run"]
EXPOSE 9000
