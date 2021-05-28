package com.exercice

import com.exercice.application.TweetService
import com.exercice.domain.Tweet
import com.exercice.infra.KafkaStream
import com.typesafe.config.Config
import org.apache.kafka.streams.scala.ImplicitConversions.consumedFromSerde
import org.apache.kafka.streams.scala.StreamsBuilder
import org.apache.kafka.streams.scala.kstream.KStream
import org.apache.kafka.streams.scala.serialization.Serdes.stringSerde


object TweetApp extends KafkaStream {
  val topicConf: Config = conf.getConfig("topics")
  val topicTweet: String = topicConf.getString("in")
  val service = new TweetService(db)

  override def topology(builder: StreamsBuilder): Unit = {
    cleanTweetStream(builder)
      .foreach((_, tweet) => {
        service.saveTweet(tweet)
      })
  }

  private def cleanTweetStream(builder: StreamsBuilder): KStream[String, Option[Tweet]] = {
    builder.stream[String, String](topicTweet)
      .filterNot((_, v) => Option(v).isEmpty)
      .mapValues(v => service.toTweet(v))
      .filterNot((_, v) => v.isEmpty)
  }
}


