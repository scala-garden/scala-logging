package com.typesafe.scalalogging

import java.util.NoSuchElementException
import org.slf4j.{Logger => Underlying}
import org.slf4j.Marker
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.mockito.MockitoSugar
import org.mockito.ArgumentMatchers._
import org.mockito.Mockito._

object DummyMarker extends Marker {
  def add(childMarker: Marker): Unit = {}
  def contains(childName: String): Boolean = false
  def contains(child: Marker): Boolean = false
  def getName: String = "DummyMarker"
  def hasChildren: Boolean = false
  def hasReferences: Boolean = false
  def iterator(): java.util.Iterator[Marker] = new java.util.Iterator[Marker] {
    def hasNext: Boolean = false
    def next(): Marker = throw new NoSuchElementException()
    override def remove(): Unit = throw new NoSuchElementException()
  }
  def remove(child: Marker): Boolean = false
}

class LoggerWithMarkerSpec extends AnyWordSpec with Matchers with Varargs with MockitoSugar {

  // Error

  "Calling error with a marker and a message" should {

    "call the underlying logger's error method if the error level is enabled" in {
      val f = fixture(_.isErrorEnabled, isEnabled = true)
      import f._
      logger.error(marker, msg)
      verify(underlying).error(marker, msg)
    }

    "not call the underlying logger's error method if the error level is not enabled" in {
      val f = fixture(_.isErrorEnabled, isEnabled = false)
      import f._
      logger.error(marker, msg)
      verify(underlying, never).error(refEq(DummyMarker), any[String])
    }

    "call the underlying logger's error method if the error level is enabled and string is interpolated" in {
      val f = fixture(_.isErrorEnabled, isEnabled = true)
      import f._
      logger.error(marker, s"msg $arg1 $arg2 $arg3")
      verify(underlying).error(marker, "msg {} {} {}", arg1, arg2, arg3)
    }
  }

  "Calling error with a marker and a message and cause" should {

    "call the underlying logger's error method if the error level is enabled" in {
      val f = fixture(_.isErrorEnabled, isEnabled = true)
      import f._
      logger.error(marker, msg, cause)
      verify(underlying).error(marker, msg, cause)
    }

    "not call the underlying logger's error method if the error level is not enabled" in {
      val f = fixture(_.isErrorEnabled, isEnabled = false)
      import f._
      logger.error(marker, msg, cause)
      verify(underlying, never).error(refEq(DummyMarker), any[String], any[Object])
    }
  }

  "Calling error with a marker and a message and parameters" should {

    "call the underlying logger's error method if the error level is enabled" in {
      val f = fixture(_.isErrorEnabled, isEnabled = true)
      import f._
      logger.error(marker, msg, arg1)
      verify(underlying).error(marker, msg, arg1)
      logger.error(marker, msg, arg1, arg2)
      verify(underlying).error(marker, msg, forceVarargs(arg1, arg2): _*)
      logger.error(marker, msg, arg1, arg2, arg3)
      verify(underlying).error(marker, msg, arg1, arg2, arg3)
    }

    "not call the underlying logger's error method if the error level is not enabled" in {
      val f = fixture(_.isErrorEnabled, isEnabled = false)
      import f._
      logger.error(marker, msg, arg1)
      verify(underlying, never).error(marker, msg, arg1)
      logger.error(marker, msg, arg1, arg2)
      verify(underlying, never).error(marker, msg, forceVarargs(arg1, arg2): _*)
      logger.error(marker, msg, arg1, arg2, arg3)
      verify(underlying, never).error(marker, msg, arg1, arg2, arg3)
    }
  }

  // Warn

  "Calling warn with a marker and a message" should {

    "call the underlying logger's warn method if the warn level is enabled" in {
      val f = fixture(_.isWarnEnabled, isEnabled = true)
      import f._
      logger.warn(marker, msg)
      verify(underlying).warn(marker, msg)
    }

    "not call the underlying logger's warn method if the warn level is not enabled" in {
      val f = fixture(_.isWarnEnabled, isEnabled = false)
      import f._
      logger.warn(marker, msg)
      verify(underlying, never).warn(refEq(DummyMarker), any[String])
    }

    "call the underlying logger's warn method if the warn level is enabled and string is interpolated" in {
      val f = fixture(_.isWarnEnabled, isEnabled = true)
      import f._
      logger.warn(marker, s"msg $arg1 $arg2 $arg3")
      verify(underlying).warn(marker, "msg {} {} {}", arg1, arg2, arg3)
    }
  }

  "Calling warn with a marker and a message and cause" should {

    "call the underlying logger's warn method if the warn level is enabled" in {
      val f = fixture(_.isWarnEnabled, isEnabled = true)
      import f._
      logger.warn(marker, msg, cause)
      verify(underlying).warn(marker, msg, cause)
    }

    "not call the underlying logger's warn method if the warn level is not enabled" in {
      val f = fixture(_.isWarnEnabled, isEnabled = false)
      import f._
      logger.warn(marker, msg, cause)
      verify(underlying, never).warn(refEq(DummyMarker), any[String], any[Object])
    }
  }

  "Calling warn with a marker and a message and parameters" should {

    "call the underlying logger's warn method if the warn level is enabled" in {
      val f = fixture(_.isWarnEnabled, isEnabled = true)
      import f._
      logger.warn(marker, msg, arg1)
      verify(underlying).warn(marker, msg, arg1)
      logger.warn(marker, msg, arg1, arg2)
      verify(underlying).warn(marker, msg, forceVarargs(arg1, arg2): _*)
      logger.warn(marker, msg, arg1, arg2, arg3)
      verify(underlying).warn(marker, msg, arg1, arg2, arg3)
    }

    "not call the underlying logger's warn method if the warn level is not enabled" in {
      val f = fixture(_.isWarnEnabled, isEnabled = false)
      import f._
      logger.warn(marker, msg, arg1)
      verify(underlying, never).warn(marker, msg, arg1)
      logger.warn(marker, msg, arg1, arg2)
      verify(underlying, never).warn(marker, msg, forceVarargs(arg1, arg2): _*)
      logger.warn(marker, msg, arg1, arg2, arg3)
      verify(underlying, never).warn(marker, msg, arg1, arg2, arg3)
    }
  }

  // Info

  "Calling info with a marker and a message" should {

    "call the underlying logger's info method if the info level is enabled" in {
      val f = fixture(_.isInfoEnabled, isEnabled = true)
      import f._
      logger.info(marker, msg)
      verify(underlying).info(marker, msg)
    }

    "not call the underlying logger's info method if the info level is not enabled" in {
      val f = fixture(_.isInfoEnabled, isEnabled = false)
      import f._
      logger.info(marker, msg)
      verify(underlying, never).info(refEq(DummyMarker), any[String])
    }

    "call the underlying logger's info method if the info level is enabled and string is interpolated" in {
      val f = fixture(_.isInfoEnabled, isEnabled = true)
      import f._
      logger.info(marker, s"msg $arg1 $arg2 $arg3")
      verify(underlying).info(marker, "msg {} {} {}", arg1, arg2, arg3)
    }
  }

  "Calling info with a marker and a message and cause" should {

    "call the underlying logger's info method if the info level is enabled" in {
      val f = fixture(_.isInfoEnabled, isEnabled = true)
      import f._
      logger.info(marker, msg, cause)
      verify(underlying).info(marker, msg, cause)
    }

    "not call the underlying logger's info method if the info level is not enabled" in {
      val f = fixture(_.isInfoEnabled, isEnabled = false)
      import f._
      logger.info(marker, msg, cause)
      verify(underlying, never).info(refEq(DummyMarker), any[String], any[Object])
    }
  }

  "Calling info with a marker and a message and parameters" should {

    "call the underlying logger's info method if the info level is enabled" in {
      val f = fixture(_.isInfoEnabled, isEnabled = true)
      import f._
      logger.info(marker, msg, arg1)
      verify(underlying).info(marker, msg, arg1)
      logger.info(marker, msg, arg1, arg2)
      verify(underlying).info(marker, msg, forceVarargs(arg1, arg2): _*)
      logger.info(marker, msg, arg1, arg2, arg3)
      verify(underlying).info(marker, msg, arg1, arg2, arg3)
    }

    "not call the underlying logger's info method if the info level is not enabled" in {
      val f = fixture(_.isInfoEnabled, isEnabled = false)
      import f._
      logger.info(marker, msg, arg1)
      verify(underlying, never).info(marker, msg, arg1)
      logger.info(marker, msg, arg1, arg2)
      verify(underlying, never).info(marker, msg, forceVarargs(arg1, arg2): _*)
      logger.info(marker, msg, arg1, arg2, arg3)
      verify(underlying, never).info(marker, msg, arg1, arg2, arg3)
    }
  }

  // Debug

  "Calling debug with a marker and a message" should {

    "call the underlying logger's debug method if the debug level is enabled" in {
      val f = fixture(_.isDebugEnabled, isEnabled = true)
      import f._
      logger.debug(marker, msg)
      verify(underlying).debug(marker, msg)
    }

    "not call the underlying logger's debug method if the debug level is not enabled" in {
      val f = fixture(_.isDebugEnabled, isEnabled = false)
      import f._
      logger.debug(marker, msg)
      verify(underlying, never).debug(refEq(DummyMarker), any[String])
    }

    "call the underlying logger's debug method if the debug level is enabled and string is interpolated" in {
      val f = fixture(_.isDebugEnabled, isEnabled = true)
      import f._
      logger.debug(marker, s"msg $arg1 $arg2 $arg3")
      verify(underlying).debug(marker, "msg {} {} {}", arg1, arg2, arg3)
    }
  }

  "Calling debug with a marker and a message and cause" should {

    "call the underlying logger's debug method if the debug level is enabled" in {
      val f = fixture(_.isDebugEnabled, isEnabled = true)
      import f._
      logger.debug(marker, msg, cause)
      verify(underlying).debug(marker, msg, cause)
    }

    "not call the underlying logger's debug method if the debug level is not enabled" in {
      val f = fixture(_.isDebugEnabled, isEnabled = false)
      import f._
      logger.debug(marker, msg, cause)
      verify(underlying, never).debug(refEq(DummyMarker), any[String], any[Object])
    }
  }

  "Calling debug with a marker and a message and parameters" should {

    "call the underlying logger's debug method if the debug level is enabled" in {
      val f = fixture(_.isDebugEnabled, isEnabled = true)
      import f._
      logger.debug(marker, msg, arg1)
      verify(underlying).debug(marker, msg, arg1)
      logger.debug(marker, msg, arg1, arg2)
      verify(underlying).debug(marker, msg, forceVarargs(arg1, arg2): _*)
      logger.debug(marker, msg, arg1, arg2, arg3)
      verify(underlying).debug(marker, msg, arg1, arg2, arg3)
    }

    "not call the underlying logger's debug method if the debug level is not enabled" in {
      val f = fixture(_.isDebugEnabled, isEnabled = false)
      import f._
      logger.debug(marker, msg, arg1)
      verify(underlying, never).debug(marker, msg, arg1)
      logger.debug(marker, msg, arg1, arg2)
      verify(underlying, never).debug(marker, msg, forceVarargs(arg1, arg2): _*)
      logger.debug(marker, msg, arg1, arg2, arg3)
      verify(underlying, never).debug(marker, msg, arg1, arg2, arg3)
    }
  }

  // Trace

  "Calling trace with a marker and a message" should {

    "call the underlying logger's trace method if the trace level is enabled" in {
      val f = fixture(_.isTraceEnabled, isEnabled = true)
      import f._
      logger.trace(marker, msg)
      verify(underlying).trace(marker, msg)
    }

    "not call the underlying logger's trace method if the trace level is not enabled" in {
      val f = fixture(_.isTraceEnabled, isEnabled = false)
      import f._
      logger.trace(marker, msg)
      verify(underlying, never).trace(refEq(DummyMarker), any[String])
    }

    "call the underlying logger's trace method if the trace level is enabled and string is interpolated" in {
      val f = fixture(_.isTraceEnabled, isEnabled = true)
      import f._
      logger.trace(marker, s"msg $arg1 $arg2 $arg3")
      verify(underlying).trace(marker, "msg {} {} {}", arg1, arg2, arg3)
    }
  }

  "Calling trace with a marker and a message and cause" should {

    "call the underlying logger's trace method if the trace level is enabled" in {
      val f = fixture(_.isTraceEnabled, isEnabled = true)
      import f._
      logger.trace(marker, msg, cause)
      verify(underlying).trace(marker, msg, cause)
    }

    "not call the underlying logger's trace method if the trace level is not enabled" in {
      val f = fixture(_.isTraceEnabled, isEnabled = false)
      import f._
      logger.trace(marker, msg, cause)
      verify(underlying, never).trace(refEq(DummyMarker), any[String], any[Object])
    }
  }

  "Calling trace with a marker and a message and parameters" should {

    "call the underlying logger's trace method if the trace level is enabled" in {
      val f = fixture(_.isTraceEnabled, isEnabled = true)
      import f._
      logger.trace(marker, msg, arg1)
      verify(underlying).trace(marker, msg, arg1)
      logger.trace(marker, msg, arg1, arg2)
      verify(underlying).trace(marker, msg, forceVarargs(arg1, arg2): _*)
      logger.trace(marker, msg, arg1, arg2, arg3)
      verify(underlying).trace(marker, msg, arg1, arg2, arg3)
    }

    "not call the underlying logger's trace method if the trace level is not enabled" in {
      val f = fixture(_.isTraceEnabled, isEnabled = false)
      import f._
      logger.trace(marker, msg, arg1)
      verify(underlying, never).trace(marker, msg, arg1)
      logger.trace(marker, msg, arg1, arg2)
      verify(underlying, never).trace(marker, msg, forceVarargs(arg1, arg2): _*)
      logger.trace(marker, msg, arg1, arg2, arg3)
      verify(underlying, never).trace(marker, msg, arg1, arg2, arg3)
    }
  }

  private def fixture(p: Underlying => Marker => Boolean, isEnabled: Boolean) = new LoggerF(p, isEnabled)
  private class LoggerF(p: Underlying => Marker => Boolean, isEnabled: Boolean) {
    val marker: DummyMarker.type = DummyMarker
    val msg = "msg"
    val cause = new RuntimeException("cause")
    val arg1 = "arg1"
    val arg2: Integer = Integer.valueOf(1)
    val arg3 = "arg3"
    val underlying: Underlying = mock[org.slf4j.Logger]

    when(p(underlying)(marker)).thenReturn(isEnabled)

    val logger: Logger = Logger(underlying)
  }
}
