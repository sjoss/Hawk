package com.exercice.infra

import com.exercice.application.UsersData
import com.exercice.domain.UserDisplay
import com.redis.RedisClient
import com.typesafe.config.{Config, ConfigFactory}

class Redis(port:Int = 6379 ) extends UsersData{
  val conf: Config = ConfigFactory.load
  val redisConf: Config = conf.getConfig("redis")
  private val client = new RedisClient(redisConf.getString("host"), port)

  private val DefaultString = ""
  private val DefaultInt = "0"

  def getUserById(id:String): Option[UserDisplay] ={
    client.hgetall1(id).map(a =>
      UserDisplay(id.toInt,
        a.getOrElse(DbModel.ScreenName, DefaultString),
        a.getOrElse(DbModel.Tweet, DefaultInt).toInt,
        a.getOrElse(DbModel.Favorites, DefaultInt).toInt,
        a.getOrElse(DbModel.Hashtags, DefaultInt).toInt,
        a.getOrElse(DbModel.Mentions, DefaultInt).toInt
      )
    )
  }

   def getTweetsCountById(id: String): Option[String] = client.hget(id,DbModel.Tweet)

   def getAllUsersId: Option[List[String]] =
    client.keys()
    .map(a=>a.filterNot(_.isEmpty)
      .map(_.get)
    )

   def addOneTweetById(id: Int): Option[Long] = client.hincrby(id,DbModel.Tweet,1)

   def setFavoriteCountById(id: Int, favoriteCount: Int): Option[Boolean] = Option(client.hset(id,DbModel.Favorites,favoriteCount))

   def incrementHashtagById(id: Int, add: Int): Option[Long] = client.hincrby(id,DbModel.Hashtags,add)

   def addOneMentionById(id: Int): Option[Long] = client.hincrby(id,DbModel.Mentions,1)

  override def setScreenNameById(id: Int, screenName: String): Option[Boolean] = Option(client.hset(id,DbModel.ScreenName,screenName))
}
object DbModel{
  val Tweet: String ="tweet"
  val Favorites: String ="favorite"
  val Hashtags: String = "hashtag"
  val Mentions: String = "mentions"
  val ScreenName: String = "screenname"
}