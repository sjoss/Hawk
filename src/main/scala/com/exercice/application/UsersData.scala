package com.exercice.application

import com.exercice.domain.UserDisplay

trait UsersData {
  def setScreenNameById(id: Int, screenName: String): Option[Boolean]

  def getUserById(id:String): Option[UserDisplay]

  def getTweetsCountById(id:String): Option[String]

  def getAllUsersId: Option[List[String]]

  def addOneTweetById(id:Int):Option[Long]

  def setFavoriteCountById(id:Int,favorite:Int):Option[Boolean]

  def incrementHashtagById(id:Int,add:Int):Option[Long]

  def addOneMentionById(id:Int):Option[Long]
}
