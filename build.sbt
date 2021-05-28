
ThisBuild / scalaVersion     := "2.13.5"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "stream-exercice-2"
val http4sVersion = "0.21.22"

lazy val root = (project in file("."))
  .settings(
    name := "test",
    libraryDependencies ++=
      Seq(
        "net.debasishg" %% "redisclient" % "3.30",
        "org.apache.kafka" %% "kafka-streams-scala" % "2.7.0" withSources(),
        "ch.qos.logback" % "logback-classic" % "1.2.3",
        "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
        "com.typesafe" % "config" % "1.4.1"
      ),
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-dsl" % http4sVersion,
      "org.http4s" %% "http4s-blaze-server" % http4sVersion,
      "org.http4s" %% "http4s-blaze-client" % http4sVersion,
      "org.http4s" %% "http4s-circe" % http4sVersion
    ),

    // src json parser
    libraryDependencies ++= Seq(
      "circe-core",
      "circe-generic",
      "circe-parser",
      "circe-generic-extras"
    ).map("io.circe" %% _ % "0.13.0"),
    // test
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.6",
      "org.scalacheck" %% "scalacheck" % "1.15.3",
      "org.apache.kafka" % "kafka-streams-test-utils" % "2.7.0",
      "org.http4s" %% "http4s-blaze-client" % http4sVersion,
      "com.github.sebruck" %% "scalatest-embedded-redis" % "0.4.0"
    ).map(_ % Test),
    assembly / assemblyJarName := "exo.jar",
    assembly / assemblyMergeStrategy := {
      case "module-info.class" => MergeStrategy.discard
      case "logback.xml" => MergeStrategy.first
      case x =>
        val oldStrategy = (assembly / assemblyMergeStrategy ).value
        oldStrategy(x)

    }

  )


