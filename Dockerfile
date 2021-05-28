FROM hseeberger/scala-sbt:graalvm-ce-21.1.0-java11_1.5.2_2.13.6  as builder

COPY build.sbt .
COPY project/ ./project/
RUN sbt update
COPY src/ ./src/
RUN sbt clean coverageOn test coverageReport coverageOff
RUN sbt "set test in assembly := {}" assembly

FROM openjdk:11-jre-slim as release
COPY --from=builder /root/target/scala-2.13/exo.jar .
RUN apt-get update
RUN apt-get install -y  curl

RUN groupadd -r myapp && useradd -r -g myapp myapp
USER myapp

EXPOSE 8080

CMD ["java","-jar","exo.jar"]

