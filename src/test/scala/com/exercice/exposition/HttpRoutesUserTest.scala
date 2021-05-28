package com.exercice.exposition

import cats.effect.IO
import com.exercice.application.UsersData
import com.exercice.domain.UserDisplay
import io.circe.generic.auto._
import org.http4s.circe.CirceEntityCodec.circeEntityDecoder
import org.http4s.implicits._
import org.http4s.{HttpRoutes, Method, Request}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class HttpRoutesUserTest extends AnyFlatSpec with Matchers{

  behavior of "HttpRoutesUserTest"
  val userService: HttpRoutes[IO] = new HttpRoutesUser(new StubUsersData).userService

  it should "return a user" in {
      val r = userService.run(
        Request(method = Method.GET, uri = uri"/users/52" )
      ).value.unsafeRunSync()
      r.get.status.code should be (200)
      r.get.as[UserDisplay].unsafeRunSync() should be(UserDisplay(1))
  }
  it should "return a all id of users" in {
    val r = userService.run(
      Request(method = Method.GET, uri = uri"/users" )
    ).value.unsafeRunSync()
    r.get.status.code should be (200)
    r.get.as[List[String]].unsafeRunSync() should be(List("5","6"))
  }
  it should "return a number of tweet" in {
    val r = userService.run(
      Request(method = Method.GET, uri = uri"/users/5/tweets" )
    ).value.unsafeRunSync()
    r.get.status.code should be (200)
    r.get.as[String].unsafeRunSync() should be("5")
  }
}

class StubUsersData extends UsersData{
  override def setScreenNameById(id: Int, screenName: String): Option[Boolean] = Some(true)

  override def getUserById(id: String): Option[UserDisplay] = Some(UserDisplay(1))

  override def getTweetsCountById(id: String): Option[String] = Some("\"5\"")

  override def getAllUsersId: Option[List[String]] = Some(List("5","6"))

  override def addOneTweetById(id: Int): Option[Long] = Some(1L)

  override def setFavoriteCountById(id: Int, favorite: Int): Option[Boolean] =  Some(true)

  override def incrementHashtagById(id: Int, add: Int): Option[Long] = Some(5L)

  override def addOneMentionById(id: Int): Option[Long] = Some(5L)
}
