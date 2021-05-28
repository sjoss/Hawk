package com.exercice.application

import com.exercice.domain.{HashTags, User}
import com.typesafe.scalalogging.LazyLogging

class UsersService(db:UsersData) extends LazyLogging{
  def addOneTweetToUser(id: Int): Option[Long] = {
    logger.info(s"##########userid :$id")
    db.addOneTweetById(id)
  }

  def setFavoriteCount(id: Int, favoriteCount: Int): Option[Boolean] = {
    logger.info(s"##########favoriteCount :$id")
    db.setFavoriteCountById(id,favoriteCount)
  }
  def setScreenName(id: Int, screenName: Option[String]): Option[Boolean] = {
    logger.info(s"##########favoriteCount :$id")
    screenName.flatMap(s =>db.setScreenNameById(id,s))
  }

  def addHashTag(id: Int, hashtags: List[HashTags]): Option[Long] = {
    logger.info(s"##########hashtags :$id")
    db.incrementHashtagById(id,hashtags.size)
  }

  def addMentions(userMentions: List[User]): Option[Long] = {

    userMentions.flatMap(f=> {
      logger.info(s"##########mentions :$f")
      db.addOneMentionById(f.id)
    }).lastOption
  }
}
