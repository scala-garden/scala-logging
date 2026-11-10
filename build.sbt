import Library.{logbackClassic, mockitoScala, scalaTest}
import _root_.com.github.sbt.osgi.SbtOsgi
import sbt.{given, *}

// basics

val scala213 = "2.13.16"
val scala3 = "3.9.0"
val scala2 = Seq("2.12.21", scala213)
val isScala3 = Def.setting {
  CrossVersion.partialVersion(scalaVersion.value).exists(_._1 != 2)
}

val scalacOption = Def.setting {
  if (isScala3.value) Seq.empty else Seq("-language:_", "-Ywarn-unused")
}

lazy val root = (project in file("."))
  .aggregate(core, scala2macros)
  .settings(
    publish / skip := true
  )

lazy val publishSettings = Seq(
  versionScheme := Some("early-semver"),
  licenses := Seq("Apache 2.0 License" -> uri("http://www.apache.org/licenses/LICENSE-2.0.html")),
  homepage := Some(uri("https://github.com/lightbend/scala-logging")),
  Test / publishArtifact := false,
  pomIncludeRepository := (_ => false),
  scmInfo := Some(
    ScmInfo(uri("https://github.com/lightbend/scala-logging"), "scm:git:git@github.com:lightbend/scala-logging.git")
  ),
  developers := List(
    Developer(
      id = "hseeberger",
      name = "Heiko Seeberger",
      email = "",
      url = uri("http://heikoseeberger.de")
    ),
    Developer(
      id = "analytically",
      name = "Mathias Bogaert",
      email = "",
      url = uri("http://twitter.com/analytically")
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
    // Scala 3 keeps the Scala 2 macro implementations on its classpath for TASTy consumers.
    allowMismatchScala := true,
    scalacOptions ++= Seq(
      "-unchecked",
      "-deprecation",
      "-encoding",
      "UTF-8"
    ) ++ scalacOption.value,
    incOptions := Def.uncached(incOptions.value.withLogRecompileOnMacro(false)),
    libraryDependencies ++= Dependencies.scalaLogging(scalaVersion.value, isScala3.value),
    initialCommands :=
      """|import com.typesafe.scalalogging._
         |import org.slf4j.{ Logger => Underlying, _ }""".stripMargin
  )
  .settings(
    // OSGi
    osgiSettings,
    publishSettings
  )
  .settings(
    OsgiKeys.bundleSymbolicName := "com.typesafe.scala-logging",
    OsgiKeys.privatePackage := Seq(),
    OsgiKeys.exportPackage := Seq("com.typesafe.scalalogging*"),
    // The bundle's runtime requirement must not depend on the JDK running sbt.
    OsgiKeys.requireCapability := {
      val javaVersion = if (isScala3.value) "17.0" else "11.0"
      s"""osgi.ee;filter:="(&(osgi.ee=JavaSE)(version>=$javaVersion))""""
    }
  )
  .dependsOn(scala2macros)

lazy val scala2macros = project
  .settings(
    name := "scala2-macros",
    organization := "com.typesafe.scala-logging",
    scalaVersion := scala213,
    crossScalaVersions := scala2,
    libraryDependencies ++= Dependencies.scalaLogging(scalaVersion.value, false)
  )
  .settings(publishSettings)

lazy val `integration-test` = project
  .in(file("integration-test"))
  .settings(
    publish / skip := true,
    scalaVersion := scala3,
    crossScalaVersions := Seq(scala3, scala213),
    scalacOptions ++= {
      CrossVersion.partialVersion(scalaVersion.value) match {
        case Some((2, 13)) => Seq("-Ytasty-reader")
        case _             => Seq.empty
      }
    },
    libraryDependencies ++= Seq(
      logbackClassic % "test",
      mockitoScala % "test",
      scalaTest % "test"
    )
  )
  .dependsOn(core)
