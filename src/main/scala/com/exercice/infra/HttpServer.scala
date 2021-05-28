package com.exercice.infra

import cats.data.Kleisli
import cats.effect._
import com.typesafe.config.{Config, ConfigFactory}
import org.http4s._
import org.http4s.implicits._
import org.http4s.server.Router
import org.http4s.server.blaze._


import scala.concurrent.ExecutionContext.Implicits.global

class HttpServer(userService: HttpRoutes[IO]) {


  val conf: Config = ConfigFactory.load
  val httpConf: Config = conf.getConfig("http")
  implicit val cs: ContextShift[IO] = IO.contextShift(global)
  implicit val timer: Timer[IO] = IO.timer(global)

  val httpApp: Kleisli[IO, Request[IO], Response[IO]] = Router("/" -> userService).orNotFound
  val serverBuilder: BlazeServerBuilder[IO] = BlazeServerBuilder[IO](global).bindHttp(httpConf.getInt("port"),httpConf.getString("host")).withHttpApp(httpApp)
  val fiber: Fiber[IO, Nothing] = serverBuilder.resource.use(_ => IO.never).start.unsafeRunSync()

  def stop(): Unit ={
    fiber.cancel.unsafeRunSync()
  }
}

