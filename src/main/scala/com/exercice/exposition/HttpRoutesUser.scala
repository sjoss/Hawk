package com.exercice.exposition

import cats.effect._
import com.exercice.application.UsersData
import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s.EntityEncoder._
import org.http4s._
import org.http4s.circe.CirceEntityCodec.circeEntityEncoder
import org.http4s.dsl.io._

import scala.language.postfixOps

class HttpRoutesUser(db: UsersData) {
  val userService: HttpRoutes[IO] = HttpRoutes.of[IO] {

    case GET -> Root / UsersPath =>
      db.getAllUsersId.fold(NotFound())(a => Ok(a.asJson))

    case GET -> Root / UsersPath / id =>
      db.getUserById(id).fold(NotFound())(a => Ok(a.asJson))

    case GET -> Root / UsersPath / id / TweetsPath =>
      db.getTweetsCountById(id).fold(NotFound())(a => Ok(a))

  }
  private val UsersPath = "users"
  private val TweetsPath = "tweets"


}

