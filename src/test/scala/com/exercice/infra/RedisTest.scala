package com.exercice.infra

import com.exercice.domain.UserDisplay
import com.github.sebruck.EmbeddedRedis
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class RedisTest extends AnyFlatSpec with EmbeddedRedis with Matchers {

  behavior of "Redis"

  it should "increment mention to user" in withRedis() {
    port => {
      val db = new Redis(port)
      db.addOneMentionById(5)
      db.addOneMentionById(5)
      val user = db.getUserById("5")
      user.get should be(UserDisplay(5, mentions = 2))
    }
  }
  it should "increment tweet to user" in withRedis() {
    port => {
      val db = new Redis(port)
      db.addOneTweetById(5)
      db.addOneTweetById(5)
      val user = db.getUserById("5")
      user.get should be(UserDisplay(5, tweets = 2))
    }
  }
  it should "set screenname " in withRedis() {
    port => {
      val db = new Redis(port)
      db.setScreenNameById(5, "screenname")

      val user = db.getUserById("5")
      user.get should be(UserDisplay(5, screenname = "screenname"))
    }
  }
  it should "set favorites " in withRedis() {
    port => {
      val db = new Redis(port)
      db.setFavoriteCountById(5, 55)

      val user = db.getUserById("5")
      user.get should be(UserDisplay(5, favorites = 55))
    }
  }
  it should "addvalues to hashtags " in withRedis() {
    port => {
      val db = new Redis(port)
      db.incrementHashtagById(5, 55)
      db.incrementHashtagById(5, 100)

      val user = db.getUserById("5")
      user.get should be(UserDisplay(5, hashTags = 155))
    }
  }
}
