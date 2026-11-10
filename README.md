Scala Logging is a **convenient** and **fast** logging library wrapping [SLF4J](http://www.slf4j.org).

![CI][Badge-CI] [![Version][Badge-Releases]][Link-Releases]

[Badge-CI]: https://github.com/lightbend-labs/scala-logging/actions/workflows/ci.yml/badge.svg
[Badge-Releases]: https://img.shields.io/maven-central/v/com.typesafe.scala-logging/scala-logging_3
[Link-Releases]: https://repo1.maven.org/maven2/com/typesafe/scala-logging/scala-logging_3/

It's convenient, because you can simply call log methods, *without* checking whether the respective log level is enabled:

```scala
logger.debug(s"Some $expensive message!")
```

It's fast, because thanks to Scala macros the *check-enabled-idiom* is applied and the following code is generated:

```scala
if (logger.isDebugEnabled) logger.debug(s"Some $expensive message!")
```

## Prerequisites

* Java 17 or higher for Scala 3; Java 11 or higher for Scala 2
  * Scala Logging 3.x supports Java 8
* Scala 2.12, 2.13 or 3.9
* Logging backend compatible with SLF4J

A compatible logging backend is [Logback](http://logback.qos.ch), add it to your sbt build definition:

```scala
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.4.14"
```

If you are looking for a version compatible with Scala 2.10, check out Scala Logging 2.x.

**If you are looking for a version compatible with SLF4J 1.x, check out Scala Logging 3.x.**


## Getting Scala Logging

Scala Logging is published to Sonatype OSS and Maven Central:

- Group id / organization: *com.typesafe.scala-logging*
- Artifact id / name: *scala-logging*

sbt users may add this to their `build.sbt`:

```scala
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "4.0.0-RC1"
```

## Building and testing

Building from source requires JDK 17 or higher and sbt 2.0.5 (selected by
`project/build.properties`).

```sh
sbt --batch +test
sbt --batch "++2.13.16; integration-test/test"
sbt --batch "++3.9.0; integration-test/test"
```

Format sources and build files with
`sbt --batch "+scalafmtAll; integration-test/scalafmtAll; scalafmtSbt"`. CI checks formatting
with `sbt --batch "+scalafmtCheckAll; integration-test/scalafmtCheckAll; scalafmtSbtCheck"`.

## Using Scala Logging

The `Logger` class from the `com.typesafe.scalalogging` package wraps an underlying SLF4J logger.
In order to create a `Logger`, you pass a name to the `apply` factory method defined in the `Logger` companion object:

```scala
val logger = Logger("name")
```

Or, you pass in a SLF4J logger instance:

```scala
val logger = Logger(LoggerFactory.getLogger("name"))
```

Or, you pass in the name of the class into which it is defined:

```scala
val logger = Logger(getClass.getName)
```

Or, you pass in a class:

```scala
val logger = Logger(classOf[MyClass])
```

Or, using the runtime class wrapped by the implicit class tag parameter:

```scala
val logger = Logger[MyClass]
```

The `LazyLogging` and `StrictLogging` traits from the `com.typesafe.scalalogging` package define the `logger` member as
a lazy or strict value respectively, whereas the `AnyLogging` trait defines an abstract `logger`.

It depends on the individual use case which trait to use. However, we have defined some scenarios where you can use these traits:

- Use `LazyLogging` if you are creating lots of objects with this trait repetitively.
- Use `StrictLogging` pretty much by default, especially if the class is a singleton, or you know the log methods will always be invoked.
- Use `AnyLogging` when writing some trait which needs access to any logger without deciding on a specific implementation.

In case of `LazyLogging` and `StrictLogging`, the underlying SLF4J logger is named according to the class into which
these traits are mixed:

```scala
class LazyLoggingExample extends LazyLogging {
  logger.debug("This is Lazy Logging ;-)")

  logger.whenDebugEnabled {
    println("This would only execute when the debug level is enabled.")
    (1 to 10).foreach(x => println("Scala logging is great!"))
  }
}
```

```scala
class StrictLoggingExample extends StrictLogging {
  logger.debug("This is Strict Logging ;-)")

  logger.whenDebugEnabled {
    println("This would only execute when the debug level is enabled.")
    (1 to 10).foreach(x => println("Scala logging is great!"))
  }
}
```

```scala
class AnyLoggingExample extends AnyLogging {
  override protected val logger: Logger = Logger("name")

  logger.info("This is Any Logging ;-)")

  logger.whenInfoEnabled {
    println("This would only execute when the info level is enabled.")
    (1 to 10).foreach(x => println("Scala logging is great!"))
  }
}
```

`LoggerTakingImplicit` provides the same methods as `Logger` class, but with additional implicit parameter `A`.
During creation of the `LoggerTakingImplicit` evidence `CanLog[A]` is required.
It may be useful when contextual parameter (e.g. _Correlation ID_) is being passed around and you would like to include it in the log messages:

```scala
case class CorrelationId(value: String)
implicit case object CanLogCorrelationId extends CanLog[CorrelationId] {
  override def logMessage(originalMsg: String, a: CorrelationId): String = s"${a.value} $originalMsg"
}

implicit val correlationId = CorrelationId("ID")

val logger = Logger.takingImplicit[CorrelationId]("test")
logger.info("Test") // takes implicit correlationId and logs "ID Test"
```

If you want to extract the context object associated with your logger i.e. `correlationId` here, use `getContext`.
```scala
val context = logger.canLogEv.getContext()
```

It's also possible to use `MDC` through `CanLog` without any troubles with execution context.

```scala
case class CorrelationId(value: String)
implicit case object CanLogCorrelationId extends CanLog[CorrelationId] {
  override def logMessage(originalMsg: String, a: CorrelationId): String = {
    MDC.put("correlationId", a.value)
    originalMsg
  }

  override def afterLog(a: CorrelationId): Unit = {
    MDC.remove("correlationId")
  }
}

implicit val correlationId = CorrelationId("ID")

val logger = Logger.takingImplicit[CorrelationId]("test")

def serviceMethod(implicit correlationId: CorrelationId): Future[Result] = {
  dbCall.map { value =>
    logger.trace(s"Received value $value from db") // takes implicit correlationId
    toResult(value)
  }
}
```

## String Interpolation

It is idiomatic to use Scala's string interpolation `logger.error(s"log $value")` instead of SLF4J string interpolation `logger.error("log {}", value)`.
However there are some tools (such as [Sentry](https://sentry.io)) that use the log message format as grouping key. Therefore they do not work well with
Scala's string interpolation.

Scala Logging replaces simple string interpolations with their SLF4J counterparts like this:

```scala
logger.error(s"my log message: $arg1 $arg2 $arg3")
```

```scala
logger.error("my log message: {} {} {}", arg1, arg2, arg3)
```

This has no effect on behavior and performace should be comparable (depends on the underlying logging library).

### Limitations

 - Works only when string interpolation is directly used inside the logging statement. That is when the log message is static (available at compile time).
 - Works only for the `logger.<level>(message)` and `logger.<level>(marker, message)` logging methods. It does not work if you want to log an exception and
 use string interpolation too (this is a limitation of the SLF4J API).

## Line numbers in log message?

Using the [sourcecode](https://github.com/lihaoyi/sourcecode#logging) library, it's possible to add line number
information (especially useful for debugging):

```scala
def foo(arg: String)(implicit line: sourcecode.Line, file: sourcecode.File) = {
  ... do something with arg ...
  ... do something with file.value ...
}

foo("hello") // the implicit sourcecode.File is filled in automatically
```

## Maintenance status

This library is community-maintained. It is not supported under the Lightbend subscription.

## Contribution policy

Contributions via GitHub pull requests are gladly accepted from their original author. Before we can accept pull requests, you will need to agree to the [Lightbend Contributor License Agreement](https://www.lightbend.com/contribute/cla) online, using your GitHub account.
