# Subject 

https://github.com/tabmo/coding-challenge/blob/master/backend/scala-2021/index.md

# Technical description

* use kafka and redis for infra
* use http4s for the server
* use kafka-stream from read messages on Kafka
* use Scala 2.13, sbt 1.5.2

# Tree description
  * data : directory for the tweets-v2.json (ddl) + load.sh for load it
  * project : sbt project directory
  * runConfigurations : save intelliJ config for dev test
  * src : code directory and unit test
  * test : feature file for karate test (https://github.com/intuit/karate)

# Run :
   *  docker build -t "exo" .
   *  docker-compose up -d --force-recreate --remove-orphans redis zookeeper kafka
   *  curl https://s3-eu-west-1.amazonaws.com/static.tabmo.io/jobs/dataeng/tweets-v2.jsonv -o data/tweets-v2.json
   *  docker exec -it stream-exercice2_kafka_1 /data/load.sh
   *  docker-compose up -d  redis zookeeper kafka
   *  docker-compose up -d
   *  url :  http://localhost:8080/users
   *  curl https://github.com/intuit/karate/releases/download/v1.0.1/karate-1.0.1.jar
   *  contract test : java -jar karate-1.0.1.jar test/minimal.feature
