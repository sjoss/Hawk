package com.exercice.domain

case class User(id: Int,screen_name: Option[String])

case class HashTags(text: String)

case class Entities(user_mentions: List[User], hashtags: List[HashTags])

case class Tweet(user: User, entities: Entities, favorite_count: Int)
