package com.exercice.application

import io.circe.generic.auto._
import io.circe.parser.decode
import com.exercice.domain.Tweet

class TweetService(db:UsersData) {

  val service = new UsersService(db)

  def saveTweet(tweet: Option[Tweet]): Option[Unit] = {
    tweet.map(
      t =>{
        val id = t.user.id
        for {
          _ <-  service.addOneTweetToUser(id)
          _ <- service.setFavoriteCount(id, t.favorite_count)
          _ <- service.addHashTag(id, t.entities.hashtags)
          _ <- service.addMentions(t.entities.user_mentions)
          _ <- service.setScreenName(id: Int, t.user.screen_name)
        } yield {}
      }

    )

  }

  def toTweet(v: String): Option[Tweet] = decode[Tweet](v).toOption

}
