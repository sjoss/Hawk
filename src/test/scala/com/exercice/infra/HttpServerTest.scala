package com.exercice.infra

import cats.effect.{Blocker, ContextShift, IO, Timer}
import org.http4s.HttpRoutes
import org.http4s.client._
import org.http4s.dsl.io._
import org.scalatest.BeforeAndAfterAll
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import java.util.concurrent._
import scala.concurrent.ExecutionContext.global

class HttpServerTest extends AnyFlatSpec with Matchers with BeforeAndAfterAll {
  val userService: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root / "path" => Ok("ok")
  }
  val server = new HttpServer(userService: HttpRoutes[IO])
  val blockingPool: ExecutorService = Executors.newFixedThreadPool(5)


  implicit val cs: ContextShift[IO] = IO.contextShift(global)
  implicit val timer: Timer[IO] = IO.timer(global)
  val blocker: Blocker = Blocker.liftExecutorService(blockingPool)
  val httpClient: Client[IO] = JavaNetClientBuilder[IO](blocker).create

  override def afterAll(): Unit = {
    server.stop()
  }
  "server" should "run in 8080" in {

    val helloJames = httpClient.expect[String]("http://localhost:8080/path")

    helloJames.unsafeRunSync() should be("ok")

  }

}
