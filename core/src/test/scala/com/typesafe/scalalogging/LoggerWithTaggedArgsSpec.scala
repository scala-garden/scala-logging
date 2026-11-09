package com.typesafe.scalalogging

import com.typesafe.scalalogging.tag.@@
import org.slf4j.{Logger => Underlying}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.mockito.MockitoSugar
import org.mockito.ArgumentMatchers._
import org.mockito.Mockito._

object tag extends TaggedType {
  trait Tagged[U]

  def taggedString[T](s: String): String @@ T = s.asInstanceOf[String @@ T]
  def taggedInteger[T](i: Integer): Integer @@ T = i.asInstanceOf[Integer @@ T]
  def taggedBoolean[T](b: Boolean): Boolean @@ T = b.asInstanceOf[Boolean @@ T]
}

class LoggerWithTaggedArgsSpec extends AnyWordSpec with Matchers with Varargs with MockitoSugar {

  trait Tag

  "Calling error with tagged args" should {

    "not throw ClassCastException when varargs are passed" in {
      val f = fixture(_.isErrorEnabled)
      import f._
      noException shouldBe thrownBy {
        logger.error("This should not throw: {}, {} - {}", arg1, arg2, arg3)
      }
      verify(underlying).error(any[String], any, any, any)
    }

    "not throw ClassCastException when interpolated message is passed" in {
      val f = fixture(_.isErrorEnabled)
      import f._
      noException shouldBe thrownBy {
        logger.error(s"This should not throw: $arg1, $arg2, $arg3")
      }
      verify(underlying).error(any[String], any, any, any)
    }
  }

  "Calling trace with tagged args" should {

    "not throw ClassCastException when varargs are passed" in {
      val f = fixture(_.isTraceEnabled)
      import f._
      noException shouldBe thrownBy {
        logger.trace("This should not throw: {}, {} - {}", arg1, arg2, arg3)
      }
      verify(underlying).trace(any[String], any, any, any)
    }

    "not throw ClassCastException when interpolated message is passed" in {
      val f = fixture(_.isTraceEnabled)
      import f._
      noException shouldBe thrownBy {
        logger.trace(s"This should not throw: $arg1, $arg2, $arg3")
      }
      verify(underlying).trace(any[String], any, any, any)
    }
  }

  "Calling debug with tagged args" should {

    "not throw ClassCastException when varargs are passed" in {
      val f = fixture(_.isDebugEnabled)
      import f._
      noException shouldBe thrownBy {
        logger.debug("This should not throw: {}, {} - {}", arg1, arg2, arg3)
      }
      verify(underlying).debug(any[String], any, any, any)
    }

    "not throw ClassCastException when interpolated message is passed" in {
      val f = fixture(_.isDebugEnabled)
      import f._
      noException shouldBe thrownBy {
        logger.debug(s"This should not throw: $arg1, $arg2, $arg3")
      }
      verify(underlying).debug(any[String], any, any, any)
    }
  }

  "Calling info with tagged args" should {

    "not throw ClassCastException when varargs are passed" in {
      val f = fixture(_.isInfoEnabled)
      import f._
      noException shouldBe thrownBy {
        logger.info("This should not throw: {}, {} - {}", arg1, arg2, arg3)
      }
      verify(underlying).info(any[String], any, any, any)
    }

    "not throw ClassCastException when interpolated message is passed" in {
      val f = fixture(_.isInfoEnabled)
      import f._
      noException shouldBe thrownBy {
        logger.info(s"This should not throw: $arg1, $arg2, $arg3")
      }
      verify(underlying).info(any[String], any, any, any)
    }
  }

  private def fixture(p: Underlying => Boolean, isEnabled: Boolean = true) = new LoggerF(p, isEnabled)
  private class LoggerF(p: Underlying => Boolean, isEnabled: Boolean = true) {
    val arg1: String @@ Tag = tag.taggedString[Tag]("arg1")
    val arg2: Integer @@ Tag = tag.taggedInteger[Tag](Integer.valueOf(1))
    val arg3: Boolean @@ Boolean = tag.taggedBoolean[Boolean](true)
    val underlying: Underlying = mock[org.slf4j.Logger]
    when(p(underlying)).thenReturn(isEnabled)
    val logger: Logger = Logger(underlying)
  }
}
