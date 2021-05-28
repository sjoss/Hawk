
* run :
   *  docker build -t "exo" .
   *  docker-compose up -d --force-recreate --remove-orphans redis zookeeper kafka
   *  curl https://s3-eu-west-1.amazonaws.com/static.tabmo.io/jobs/dataeng/tweets-v2.jsonv -o data/tweets-v2.json
   *  docker exec -it stream-exercice2_kafka_1 /data/load.sh
   *  docker-compose up -d  redis zookeeper kafka
   *  docker-compose up -d
   *  url :  http://localhost:8080/users
   *  curl https://github.com/intuit/karate/releases/download/v1.0.1/karate-1.0.1.jar
   *  contract test : java -jar karate-1.0.1.jar test/minimal.feature
