package com.typesafe.scalalogging

import java.io._
import org.slf4j.{Logger => Underlying}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.mockito.MockitoSugar
import org.mockito.ArgumentMatchers._
import org.mockito.Mockito._

trait Varargs {
  // TODO: we used to wrap in List(...): _*, which I assume was to force the varags method to be chosen.
  // I encapsulated that here in something that works across 2.12/2.13.
  def forceVarargs[T](xs: T*): scala.Seq[T] = scala.Seq(xs: _*)
}

class LoggerSpec extends AnyWordSpec with Matchers with Varargs with MockitoSugar {

  // Error

  "Calling error with a message" should {

    "call the underlying logger's error method if the error level is enabled" in {
      val f = fixture(_.isErrorEnabled, isEnabled = true)
      import f._
      logger.error(msg)
      verify(underlying).error(msg)
    }

    "not call the underlying logger's error method if the error level is not enabled" in {
      val f = fixture(_.isErrorEnabled, isEnabled = false)
      import f._
      logger.error(msg)
      verify(underlying, never).error(any[String])
    }
  }

  "Calling error with an interpolated message" should {

    "call the underlying logger's error method with arguments if the error level is enabled" in {
      val f = fixture(_.isErrorEnabled, isEnabled = true)
      import f._
      logger.error(s"msg $arg1 $arg2 $arg3")
      verify(underlying).error("msg {} {} {}", arg1, arg2, arg3)
    }

    "call the underlying logger's error method with two arguments if the error level is enabled" in {
      val f = fixture(_.isErrorEnabled, isEnabled = true)
      import f._
      logger.error(s"msg $arg1 $arg2")
      verify(underlying).error("msg {} {}", forceVarargs(arg1, arg2): _*)
    }

  }

  "Calling error with a message and cause" should {

    "call the underlying logger's error method if the error level is enabled" in {
      val f = fixture(_.isErrorEnabled, isEnabled = true)
      import f._
      logger.error(msg, cause)
      verify(underlying).error(msg, cause)
    }

    "not call the underlying logger's error method if the error level is not enabled" in {
      val f = fixture(_.isErrorEnabled, isEnabled = false)
      import f._
      logger.error(msg, cause)
      verify(underlying, never).error(any[String], any[Object])
    }
  }

  "Calling error with a message and parameters" should {

    "call the underlying logger's error method if the error level is enabled" in {
      val f = fixture(_.isErrorEnabled, isEnabled = true)
      import f._
      logger.error(msg, arg1)
      verify(underlying).error(msg, arg1)
      logger.error(msg, arg1, arg2)
      verify(underlying).error(msg, forceVarargs(arg1, arg2): _*)
      logger.error(msg, arg1, arg2, arg3)
      verify(underlying).error(msg, arg1, arg2, arg3)
    }

    "not call the underlying logger's error method if the error level is not enabled" in {
      val f = fixture(_.isErrorEnabled, isEnabled = false)
      import f._
      logger.error(msg, arg1)
      verify(underlying, never).error(msg, arg1)
      logger.error(msg, arg1, arg2)
      verify(underlying, never).error(msg, forceVarargs(arg1, arg2): _*)
      logger.error(msg, arg1, arg2, arg3)
      verify(underlying, never).error(msg, arg1, arg2, arg3)
    }
  }

  // Warn

  "Calling warn with a message" should {

    "call the underlying logger's warn method if the warn level is enabled" in {
      val f = fixture(_.isWarnEnabled, isEnabled = true)
      import f._
      logger.warn(msg)
      verify(underlying).warn(msg)
    }

    "not call the underlying logger's warn method if the warn level is not enabled" in {
      val f = fixture(_.isWarnEnabled, isEnabled = false)
      import f._
      logger.warn(msg)
      verify(underlying, never).warn(any[String])
    }
  }

  "Calling warn with an interpolated message" should {

    "call the underlying logger's warn method if the warn level is enabled" in {
      val f = fixture(_.isWarnEnabled, isEnabled = true)
      import f._
      logger.warn(s"msg $arg1 $arg2 $arg3")
      verify(underlying).warn("msg {} {} {}", arg1, arg2, arg3)
    }

    "call the underlying logger's warn method with two arguments if the warn level is enabled" in {
      val f = fixture(_.isWarnEnabled, isEnabled = true)
      import f._
      logger.warn(s"msg $arg1 $arg2")
      verify(underlying).warn("msg {} {}", forceVarargs(arg1, arg2): _*)
    }
  }

  "Calling warn with a message and cause" should {

    "call the underlying logger's warn method if the warn level is enabled" in {
      val f = fixture(_.isWarnEnabled, isEnabled = true)
      import f._
      logger.warn(msg, cause)
      verify(underlying).warn(msg, cause)
    }

    "not call the underlying logger's warn method if the warn level is not enabled" in {
      val f = fixture(_.isWarnEnabled, isEnabled = false)
      import f._
      logger.warn(msg, cause)
      verify(underlying, never).warn(any[String], any[Object])
    }
  }

  "Calling warn with a message and parameters" should {

    "call the underlying logger's warn method if the warn level is enabled" in {
      val f = fixture(_.isWarnEnabled, isEnabled = true)
      import f._
      logger.warn(msg, arg1)
      verify(underlying).warn(msg, arg1)
      logger.warn(msg, arg1, arg2)
      verify(underlying).warn(msg, forceVarargs(arg1, arg2): _*)
      logger.warn(msg, arg1, arg2, arg3)
      verify(underlying).warn(msg, arg1, arg2, arg3)
    }

    "not call the underlying logger's warn method if the warn level is not enabled" in {
      val f = fixture(_.isWarnEnabled, isEnabled = false)
      import f._
      logger.warn(msg, arg1)
      verify(underlying, never).warn(msg, arg1)
      logger.warn(msg, arg1, arg2)
      verify(underlying, never).warn(msg, forceVarargs(arg1, arg2): _*)
      logger.warn(msg, arg1, arg2, arg3)
      verify(underlying, never).warn(msg, arg1, arg2, arg3)
    }
  }

  // Info

  "Calling info with a message" should {

    "call the underlying logger's info method if the info level is enabled" in {
      val f = fixture(_.isInfoEnabled, isEnabled = true)
      import f._
      logger.info(msg)
      verify(underlying).info(msg)
    }

    "not call the underlying logger's info method if the info level is not enabled" in {
      val f = fixture(_.isInfoEnabled, isEnabled = false)
      import f._
      logger.info(msg)
      verify(underlying, never).info(any[String])
    }
  }

  "Calling info with an interpolated message" should {

    "call the underlying logger's info method if the info level is enabled" in {
      val f = fixture(_.isInfoEnabled, isEnabled = true)
      import f._
      logger.info(s"msg $arg1 $arg2 $arg3")
      verify(underlying).info("msg {} {} {}", arg1, arg2, arg3)
    }

    "call the underlying logger's info method with two arguments if the info level is enabled" in {
      val f = fixture(_.isInfoEnabled, isEnabled = true)
      import f._
      logger.info(s"msg $arg1 $arg2")
      verify(underlying).info("msg {} {}", forceVarargs(arg1, arg2): _*)
    }
  }

  "Calling info with a message and cause" should {

    "call the underlying logger's info method if the info level is enabled" in {
      val f = fixture(_.isInfoEnabled, isEnabled = true)
      import f._
      logger.info(msg, cause)
      verify(underlying).info(msg, cause)
    }

    "not call the underlying logger's info method if the info level is not enabled" in {
      val f = fixture(_.isInfoEnabled, isEnabled = false)
      import f._
      logger.info(msg, cause)
      verify(underlying, never).info(any[String], any[Object])
    }
  }

  "Calling info with a message and parameters" should {

    "call the underlying logger's info method if the info level is enabled" in {
      val f = fixture(_.isInfoEnabled, isEnabled = true)
      import f._
      logger.info(msg, arg1)
      verify(underlying).info(msg, arg1)
      logger.info(msg, arg1, arg2)
      verify(underlying).info(msg, forceVarargs(arg1, arg2): _*)
      logger.info(msg, arg1, arg2, arg3)
      verify(underlying).info(msg, arg1, arg2, arg3)
    }

    "not call the underlying logger's info method if the info level is not enabled" in {
      val f = fixture(_.isInfoEnabled, isEnabled = false)
      import f._
      logger.info(msg, arg1)
      verify(underlying, never).info(msg, arg1)
      logger.info(msg, arg1, arg2)
      verify(underlying, never).info(msg, forceVarargs(arg1, arg2): _*)
      logger.info(msg, arg1, arg2, arg3)
      verify(underlying, never).info(msg, arg1, arg2, arg3)
    }
  }

  // Debug

  "Calling debug with a message" should {

    "call the underlying logger's debug method if the debug level is enabled" in {
      val f = fixture(_.isDebugEnabled, isEnabled = true)
      import f._
      logger.debug(msg)
      verify(underlying).debug(msg)
    }

    "not call the underlying logger's debug method if the debug level is not enabled" in {
      val f = fixture(_.isDebugEnabled, isEnabled = false)
      import f._
      logger.debug(msg)
      verify(underlying, never).debug(any[String])
    }
  }
  "Calling debug with an interpolated message" should {

    "call the underlying logger's debug method if the debug level is enabled" in {
      val f = fixture(_.isDebugEnabled, isEnabled = true)
      import f._
      logger.debug(s"msg $arg1 $arg2 $arg3")
      verify(underlying).debug("msg {} {} {}", arg1, arg2, arg3)
    }

    "call the underlying logger's debug method with two arguments if the debug level is enabled" in {
      val f = fixture(_.isDebugEnabled, isEnabled = true)
      import f._
      logger.debug(s"msg $arg1 $arg2")
      verify(underlying).debug("msg {} {}", forceVarargs(arg1, arg2): _*)
    }
  }

  "Calling debug with a message and cause" should {

    "call the underlying logger's debug method if the debug level is enabled" in {
      val f = fixture(_.isDebugEnabled, isEnabled = true)
      import f._
      logger.debug(msg, cause)
      verify(underlying).debug(msg, cause)
    }

    "not call the underlying logger's debug method if the debug level is not enabled" in {
      val f = fixture(_.isDebugEnabled, isEnabled = false)
      import f._
      logger.debug(msg, cause)
      verify(underlying, never).debug(any[String], any[Object])
    }
  }

  "Calling debug with a message and parameters" should {

    "call the underlying logger's debug method if the debug level is enabled" in {
      val f = fixture(_.isDebugEnabled, isEnabled = true)
      import f._
      logger.debug(msg, arg1)
      verify(underlying).debug(msg, arg1)
      logger.debug(msg, arg1, arg2)
      verify(underlying).debug(msg, forceVarargs(arg1, arg2): _*)
      logger.debug(msg, arg1, arg2, arg3)
      verify(underlying).debug(msg, arg1, arg2, arg3)
    }

    "not call the underlying logger's debug method if the debug level is not enabled" in {
      val f = fixture(_.isDebugEnabled, isEnabled = false)
      import f._
      logger.debug(msg, arg1)
      verify(underlying, never).debug(msg, arg1)
      logger.debug(msg, arg1, arg2)
      verify(underlying, never).debug(msg, forceVarargs(arg1, arg2): _*)
      logger.debug(msg, arg1, arg2, arg3)
      verify(underlying, never).debug(msg, arg1, arg2, arg3)
    }
  }

  // Trace

  "Calling trace with a message" should {

    "call the underlying logger's trace method if the trace level is enabled" in {
      val f = fixture(_.isTraceEnabled, isEnabled = true)
      import f._
      logger.trace(msg)
      verify(underlying).trace(msg)
    }

    "not call the underlying logger's trace method if the trace level is not enabled" in {
      val f = fixture(_.isTraceEnabled, isEnabled = false)
      import f._
      logger.trace(msg)
      verify(underlying, never).trace(any[String])
    }
  }

  "Calling trace with an interpolated message" should {

    "call the underlying logger's trace method if the trace level is enabled" in {
      val f = fixture(_.isTraceEnabled, isEnabled = true)
      import f._
      logger.trace(s"msg $arg1 $arg2 $arg3")
      verify(underlying).trace("msg {} {} {}", arg1, arg2, arg3)
    }

    "call the underlying logger's trace method with two arguments if the trace level is enabled" in {
      val f = fixture(_.isTraceEnabled, isEnabled = true)
      import f._
      logger.trace(s"msg $arg1 $arg2")
      verify(underlying).trace("msg {} {}", forceVarargs(arg1, arg2): _*)
    }
  }

  "Calling trace with a message and cause" should {

    "call the underlying logger's trace method if the trace level is enabled" in {
      val f = fixture(_.isTraceEnabled, isEnabled = true)
      import f._
      logger.trace(msg, cause)
      verify(underlying).trace(msg, cause)
    }

    "not call the underlying logger's trace method if the trace level is not enabled" in {
      val f = fixture(_.isTraceEnabled, isEnabled = false)
      import f._
      logger.trace(msg, cause)
      verify(underlying, never).trace(any[String], any[Object])
    }
  }

  "Calling trace with a message and parameters" should {

    "call the underlying logger's trace method if the trace level is enabled" in {
      val f = fixture(_.isTraceEnabled, isEnabled = true)
      import f._
      logger.trace(msg, arg1)
      verify(underlying).trace(msg, arg1)
      logger.trace(msg, arg1, arg2)
      verify(underlying).trace(msg, forceVarargs(arg1, arg2): _*)
      logger.trace(msg, arg1, arg2, arg3)
      verify(underlying).trace(msg, arg1, arg2, arg3)
    }

    "not call the underlying logger's trace method if the trace level is not enabled" in {
      val f = fixture(_.isTraceEnabled, isEnabled = false)
      import f._
      logger.trace(msg, arg1)
      verify(underlying, never).trace(msg, arg1)
      logger.trace(msg, arg1, arg2)
      verify(underlying, never).trace(msg, forceVarargs(arg1, arg2): _*)
      logger.trace(msg, arg1, arg2, arg3)
      verify(underlying, never).trace(msg, arg1, arg2, arg3)
    }
  }

  // Interpolator destructuring corner cases

  "Logging a message using the standard string interpolator" should {

    "call the underlying format method with boxed versions of value arguments" in {
      val f = fixture(_.isErrorEnabled, isEnabled = true)
      import f._
      logger.error(s"msg ${1}")
      verify(underlying).error("msg {}", 1.asInstanceOf[AnyRef])
    }

    "call the underlying format method with boxed versions of arguments of type Any" in {
      val f = fixture(_.isErrorEnabled, isEnabled = true)
      import f._
      logger.error(s"msg ${1.asInstanceOf[Any]}")
      verify(underlying).error("msg {}", 1.asInstanceOf[AnyRef])
    }

    "call the underlying format method escaping literal format anchors" in {
      val f = fixture(_.isErrorEnabled, isEnabled = true)
      import f._
      logger.error(s"foo {} bar $arg1")
      verify(underlying).error("foo \\{} bar {}", arg1)
    }

    "call the underlying method without escaping format anchors when the message has no interpolations" in {
      val f = fixture(_.isErrorEnabled, isEnabled = true)
      import f._
      logger.error(s"foo {} bar")
      verify(underlying).error("foo {} bar")
    }

    "call the underlying format method when the interpolated string contains escape sequences" in {
      val f = fixture(_.isErrorEnabled, isEnabled = true)
      import f._
      logger.error(s"foo\nbar $arg1")
      verify(underlying).error(s"foo\nbar {}", arg1)
    }

    "call the underlying format method when the interpolated string is triple quoted and contains escape sequences" in {
      val f = fixture(_.isErrorEnabled, isEnabled = true)
      import f._
      logger.error(s"""foo\nbar $arg1""")
      verify(underlying).error(s"""foo\nbar {}""", arg1)
    }

    "call the underlying format method when interpolated argument is a Throwable" in {
      val f = fixture(_.isErrorEnabled, isEnabled = true)
      import f._
      logger.error(s"""foo\nbar $arg7""")
      verify(underlying).error(s"""foo\nbar {}""", arg7ref)
    }
  }

  "Logging a message using slf4 interpolator and Any args" should {
    "map args to AnyRef for 2 args" in {
      val f = fixture(_.isErrorEnabled, isEnabled = true)
      import f._
      logger.error("foo {}, bar {}", arg4, arg5)
      verify(underlying).error("foo {}, bar {}", forceVarargs(arg4ref, arg5ref): _*)
    }

    "map args to AnyRef for non 2 args" in {
      val f = fixture(_.isErrorEnabled, isEnabled = true)
      import f._
      logger.error("foo {}", arg4)
      verify(underlying).error("foo {}", arg4ref)
      logger.error("foo {}, bar {}, {}", arg4, arg5, arg6)
      verify(underlying).error("foo {}, bar {}, {}", arg4ref, arg5ref, arg6ref)
    }
  }

  "Serializing Logger" should {

    def serialize(logger: Logger): Array[Byte] = {
      val byteArrayStream = new ByteArrayOutputStream
      val out = new ObjectOutputStream(byteArrayStream)

      out.writeObject(logger)
      out.close()
      byteArrayStream.close()

      byteArrayStream.toByteArray
    }

    def deserialize(array: Array[Byte]): Logger = {
      val byteArrayStream = new ByteArrayInputStream(array)
      val in = new ObjectInputStream(byteArrayStream)

      val logger = in.readObject.asInstanceOf[Logger]
      in.close()
      byteArrayStream.close()

      logger
    }

    "be usable after deserialization" in {
      val logger = deserialize(serialize(Logger(org.slf4j.LoggerFactory.getLogger("test"))))

      logger.trace("Back from deserialization")
    }

    "constructed by explicit class and be usable after deserialization" in {
      val logger = deserialize(serialize(Logger(classOf[LoggerSpec])))

      logger.trace("Back from deserialization")
    }

    "constructed by implicit class tag and be usable after deserialization" in {
      val logger = deserialize(serialize(Logger[LoggerSpec]))

      logger.trace("Back from deserialization")
    }
  }

  "Serializing LoggerTakingImplicit" should {
    case class CorrelationId(value: String)
    implicit val correlationId: CorrelationId = CorrelationId(value = "correlationId")

    implicit case object canLogCorrelationId extends CanLog[CorrelationId] {
      def logMessage(originalMsg: String, a: CorrelationId): String = s"${a.value} $originalMsg"
    }

    def serialize[A](logger: LoggerTakingImplicit[A]): Array[Byte] = {
      val byteArrayStream = new ByteArrayOutputStream
      val out = new ObjectOutputStream(byteArrayStream)

      out.writeObject(logger)
      out.close()
      byteArrayStream.close()

      byteArrayStream.toByteArray
    }

    def deserialize[A](array: Array[Byte]): LoggerTakingImplicit[A] = {
      val byteArrayStream = new ByteArrayInputStream(array)
      val in = new ObjectInputStream(byteArrayStream)

      val logger = in.readObject.asInstanceOf[LoggerTakingImplicit[A]]
      in.close()
      byteArrayStream.close()

      logger
    }

    "be usable after deserialization" in {
      val logger =
        deserialize[CorrelationId](
          serialize[CorrelationId](Logger.takingImplicit[CorrelationId](org.slf4j.LoggerFactory.getLogger("test")))
        )

      logger.trace("Back from deserialization")
    }

    "constructed by explicit class and be usable after deserialization" in {
      val logger =
        deserialize[CorrelationId](serialize[CorrelationId](Logger.takingImplicit[CorrelationId](classOf[LoggerSpec])))

      logger.trace("Back from deserialization")
    }

    "constructed by implicit class tag and be usable after deserialization" in {
      val logger =
        deserialize[CorrelationId](serialize[CorrelationId](Logger.takingImplicit[LoggerSpec, CorrelationId]))

      logger.trace("Back from deserialization")
    }
  }

  private def fixture(p: Underlying => Boolean, isEnabled: Boolean) = new LoggerF(p, isEnabled)
  private class LoggerF(p: Underlying => Boolean, isEnabled: Boolean) {
    val msg = "msg"
    val cause = new RuntimeException("cause")
    val arg1 = "arg1"
    val arg2: Integer = Integer.valueOf(1)
    val arg3 = "arg3"
    val arg4 = 4
    val arg4ref: AnyRef = arg4.asInstanceOf[AnyRef]
    val arg5 = true
    val arg5ref: AnyRef = arg5.asInstanceOf[AnyRef]
    val arg6 = 6L
    val arg6ref: AnyRef = arg6.asInstanceOf[AnyRef]
    val arg7 = new Throwable
    val arg7ref: AnyRef = arg7.asInstanceOf[AnyRef]
    val underlying: Underlying = mock[org.slf4j.Logger]
    when(p(underlying)).thenReturn(isEnabled)
    val logger: Logger = Logger(underlying)
  }
}
