import Library.{logbackClassic, mockitoScala, scalaTest}
import _root_.com.github.sbt.osgi.SbtOsgi
import sbt.url

// basics

val scala213 = "2.13.16"
val scala3 = "3.8.4"
val scala2 = Seq("2.12.21", scala213)
val isScala3 = Def.setting {
  CrossVersion.partialVersion(scalaVersion.value).exists(_._1 != 2)
}

val scalacOption = Def.setting {
  if (isScala3.value) Seq.empty else Seq("-language:_", "-Ywarn-unused")
}

lazy val root = (project in file(".")).aggregate(core, scala2macros)
  .settings(
      publish / skip := true
  )

lazy val publishSettings = Seq(
  licenses := Seq("Apache 2.0 License" -> url("http://www.apache.org/licenses/LICENSE-2.0.html")),
  homepage := Some(url("https://github.com/lightbend/scala-logging")),
  Test / publishArtifact := false,
  pomIncludeRepository := (_ => false),
  scmInfo := Some(
    ScmInfo(url("https://github.com/lightbend/scala-logging"), "scm:git:git@github.com:lightbend/scala-logging.git")
  ),
  developers := List(
    Developer(
      id = "hseeberger",
      name = "Heiko Seeberger",
      email = "",
      url = url("http://heikoseeberger.de")
    ),
    Developer(
      id = "analytically",
      name = "Mathias Bogaert",
      email = "",
      url = url("http://twitter.com/analytically")
    )
  )
)

lazy val core = (project in file("core"))
  .enablePlugins(SbtOsgi)
  .settings(
    name := "scala-logging",
    organization := "com.typesafe.scala-logging",
    crossScalaVersions := Seq(scala3) ++ scala2,
    scalaVersion := crossScalaVersions.value.head,
    ThisBuild / versionScheme := Some("early-semver"),
    scalacOptions ++= Seq(
      "-unchecked",
      "-deprecation",
      "-encoding", "UTF-8"
    ) ++ scalacOption.value,
    incOptions := incOptions.value.withLogRecompileOnMacro(false),
    libraryDependencies ++= Dependencies.scalaLogging(scalaVersion.value, isScala3.value),
    initialCommands :=
      """|import com.typesafe.scalalogging._
         |import org.slf4j.{ Logger => Underlying, _ }""".stripMargin
  ).settings(
    // OSGi
    osgiSettings,
    publishSettings
  ).settings(
    OsgiKeys.bundleSymbolicName := "com.typesafe.scala-logging",
    OsgiKeys.privatePackage := Seq(),
    OsgiKeys.exportPackage := Seq("com.typesafe.scalalogging*")
)
  .dependsOn(scala2macros)

lazy val scala2macros = project
  .settings(
    name := "scala2-macros",
    organization := "com.typesafe.scala-logging",    
    scalaVersion := scala213,
    crossScalaVersions := scala2,
    libraryDependencies ++= Dependencies.scalaLogging(scalaVersion.value, false),
  ).settings(publishSettings)

lazy val `integration-test` = project.in(file("integration-test"))
  .settings(
    publish / skip := true,
    scalaVersion := scala3,
    crossScalaVersions := Seq(scala3, scala213),
    scalacOptions ++= {
      CrossVersion.partialVersion(scalaVersion.value) match {
        case Some((2, 13)) => Seq("-Ytasty-reader")
        case _ => Seq.empty
      }
    },
    libraryDependencies ++= Seq(
      logbackClassic % "test",
      mockitoScala % "test",
      scalaTest % "test"
    )
  )
  .dependsOn(core)
