package com.typesafe.scalalogging

import org.slf4j.{Marker, Logger as Underlying}
import scala.language.experimental.macros

trait LoggerTakingImplicitImpl[A] {
  def underlying: Underlying
  implicit val canLogEv: CanLog[A]

  // Error

  inline def error(inline message: String)(implicit inline a: A): Unit =
    ${ LoggerTakingImplicitMacro.errorMessage('underlying, 'canLogEv, 'message)('a) }

  inline def error(inline message: String, inline cause: Throwable)(implicit inline a: A): Unit =
    ${ LoggerTakingImplicitMacro.errorMessageCause('underlying, 'canLogEv, 'message, 'cause)('a) }

  inline def error(inline message: String, inline args: Any*)(implicit inline a: A): Unit =
    ${ LoggerTakingImplicitMacro.errorMessageArgs('underlying, 'canLogEv, 'message, 'args)('a) }

  inline def error(inline marker: Marker, inline message: String)(implicit inline a: A): Unit =
    ${ LoggerTakingImplicitMacro.errorMessageMarker('underlying, 'canLogEv, 'marker, 'message)('a) }

  inline def error(inline marker: Marker, inline message: String, inline cause: Throwable)(implicit inline a: A): Unit =
    ${ LoggerTakingImplicitMacro.errorMessageCauseMarker('underlying, 'canLogEv, 'marker, 'message, 'cause)('a) }

  inline def error(inline marker: Marker, inline message: String, inline args: Any*)(implicit inline a: A): Unit =
    ${ LoggerTakingImplicitMacro.errorMessageArgsMarker('underlying, 'canLogEv, 'marker, 'message, 'args)('a) }

  inline def whenErrorEnabled(inline body: Unit)(implicit inline a: A): Unit = ${
    LoggerTakingImplicitMacro.errorCode('underlying, 'canLogEv, 'body)('a)
  }

  // Warn

  inline def warn(inline message: String)(implicit inline a: A): Unit =
    ${ LoggerTakingImplicitMacro.warnMessage('underlying, 'canLogEv, 'message)('a) }

  inline def warn(inline message: String, inline cause: Throwable)(implicit inline a: A): Unit =
    ${ LoggerTakingImplicitMacro.warnMessageCause('underlying, 'canLogEv, 'message, 'cause)('a) }

  inline def warn(inline message: String, inline args: Any*)(implicit inline a: A): Unit =
    ${ LoggerTakingImplicitMacro.warnMessageArgs('underlying, 'canLogEv, 'message, 'args)('a) }

  inline def warn(inline marker: Marker, inline message: String)(implicit inline a: A): Unit =
    ${ LoggerTakingImplicitMacro.warnMessageMarker('underlying, 'canLogEv, 'marker, 'message)('a) }

  inline def warn(inline marker: Marker, inline message: String, inline cause: Throwable)(implicit inline a: A): Unit =
    ${ LoggerTakingImplicitMacro.warnMessageCauseMarker('underlying, 'canLogEv, 'marker, 'message, 'cause)('a) }

  inline def warn(inline marker: Marker, inline message: String, inline args: Any*)(implicit inline a: A): Unit =
    ${ LoggerTakingImplicitMacro.warnMessageArgsMarker('underlying, 'canLogEv, 'marker, 'message, 'args)('a) }

  inline def whenWarnEnabled(inline body: Unit)(implicit inline a: A): Unit = ${
    LoggerTakingImplicitMacro.warnCode('underlying, 'canLogEv, 'body)('a)
  }

  // Info

  inline def info(inline message: String)(implicit inline a: A): Unit =
    ${ LoggerTakingImplicitMacro.infoMessage('underlying, 'canLogEv, 'message)('a) }

  inline def info(inline message: String, inline cause: Throwable)(implicit inline a: A): Unit =
    ${ LoggerTakingImplicitMacro.infoMessageCause('underlying, 'canLogEv, 'message, 'cause)('a) }

  inline def info(inline message: String, inline args: Any*)(implicit inline a: A): Unit =
    ${ LoggerTakingImplicitMacro.infoMessageArgs('underlying, 'canLogEv, 'message, 'args)('a) }

  inline def info(inline marker: Marker, inline message: String)(implicit inline a: A): Unit =
    ${ LoggerTakingImplicitMacro.infoMessageMarker('underlying, 'canLogEv, 'marker, 'message)('a) }

  inline def info(inline marker: Marker, inline message: String, inline cause: Throwable)(implicit inline a: A): Unit =
    ${ LoggerTakingImplicitMacro.infoMessageCauseMarker('underlying, 'canLogEv, 'marker, 'message, 'cause)('a) }

  inline def info(inline marker: Marker, inline message: String, inline args: Any*)(implicit inline a: A): Unit =
    ${ LoggerTakingImplicitMacro.infoMessageArgsMarker('underlying, 'canLogEv, 'marker, 'message, 'args)('a) }

  inline def whenInfoEnabled(inline body: Unit)(implicit inline a: A): Unit = ${
    LoggerTakingImplicitMacro.infoCode('underlying, 'canLogEv, 'body)('a)
  }

  // Debug

  inline def debug(inline message: String)(implicit inline a: A): Unit =
    ${ LoggerTakingImplicitMacro.debugMessage('underlying, 'canLogEv, 'message)('a) }

  inline def debug(inline message: String, inline cause: Throwable)(implicit inline a: A): Unit =
    ${ LoggerTakingImplicitMacro.debugMessageCause('underlying, 'canLogEv, 'message, 'cause)('a) }

  inline def debug(inline message: String, inline args: Any*)(implicit inline a: A): Unit =
    ${ LoggerTakingImplicitMacro.debugMessageArgs('underlying, 'canLogEv, 'message, 'args)('a) }

  inline def debug(inline marker: Marker, inline message: String)(implicit inline a: A): Unit =
    ${ LoggerTakingImplicitMacro.debugMessageMarker('underlying, 'canLogEv, 'marker, 'message)('a) }

  inline def debug(inline marker: Marker, inline message: String, inline cause: Throwable)(implicit inline a: A): Unit =
    ${ LoggerTakingImplicitMacro.debugMessageCauseMarker('underlying, 'canLogEv, 'marker, 'message, 'cause)('a) }

  inline def debug(inline marker: Marker, inline message: String, inline args: Any*)(implicit inline a: A): Unit =
    ${ LoggerTakingImplicitMacro.debugMessageArgsMarker('underlying, 'canLogEv, 'marker, 'message, 'args)('a) }

  inline def whenDebugEnabled(inline body: Unit)(implicit inline a: A): Unit = ${
    LoggerTakingImplicitMacro.debugCode('underlying, 'canLogEv, 'body)('a)
  }

  // Trace

  inline def trace(inline message: String)(implicit inline a: A): Unit =
    ${ LoggerTakingImplicitMacro.traceMessage('underlying, 'canLogEv, 'message)('a) }

  inline def trace(inline message: String, inline cause: Throwable)(implicit inline a: A): Unit =
    ${ LoggerTakingImplicitMacro.traceMessageCause('underlying, 'canLogEv, 'message, 'cause)('a) }

  inline def trace(inline message: String, inline args: Any*)(implicit inline a: A): Unit =
    ${ LoggerTakingImplicitMacro.traceMessageArgs('underlying, 'canLogEv, 'message, 'args)('a) }

  inline def trace(inline marker: Marker, inline message: String)(implicit inline a: A): Unit =
    ${ LoggerTakingImplicitMacro.traceMessageMarker('underlying, 'canLogEv, 'marker, 'message)('a) }

  inline def trace(inline marker: Marker, inline message: String, inline cause: Throwable)(implicit inline a: A): Unit =
    ${ LoggerTakingImplicitMacro.traceMessageCauseMarker('underlying, 'canLogEv, 'marker, 'message, 'cause)('a) }

  inline def trace(inline marker: Marker, inline message: String, inline args: Any*)(implicit inline a: A): Unit =
    ${ LoggerTakingImplicitMacro.traceMessageArgsMarker('underlying, 'canLogEv, 'marker, 'message, 'args)('a) }

  inline def whenTraceEnabled(inline body: Unit)(implicit inline a: A): Unit = ${
    LoggerTakingImplicitMacro.traceCode('underlying, 'canLogEv, 'body)('a)
  }

  // scala 2

  // Error

  def error(message: String)(implicit a: A): Unit = macro Scala2LoggerTakingImplicitMacro.errorMessage[A]

  def error(message: String, cause: Throwable)(implicit a: A): Unit = macro
    Scala2LoggerTakingImplicitMacro.errorMessageCause[A]

  def error(message: String, args: Any*)(implicit a: A): Unit = macro
    Scala2LoggerTakingImplicitMacro.errorMessageArgs[A]

  def error(marker: Marker, message: String)(implicit a: A): Unit = macro
    Scala2LoggerTakingImplicitMacro.errorMessageMarker[A]

  def error(marker: Marker, message: String, cause: Throwable)(implicit a: A): Unit = macro
    Scala2LoggerTakingImplicitMacro.errorMessageCauseMarker[A]

  def error(marker: Marker, message: String, args: Any*)(implicit a: A): Unit = macro
    Scala2LoggerTakingImplicitMacro.errorMessageArgsMarker[A]

  def whenErrorEnabled(body: Unit)(implicit a: A): Unit = macro Scala2LoggerTakingImplicitMacro.errorCode[A]

  // Warn

  def warn(message: String)(implicit a: A): Unit = macro Scala2LoggerTakingImplicitMacro.warnMessage[A]

  def warn(message: String, cause: Throwable)(implicit a: A): Unit = macro
    Scala2LoggerTakingImplicitMacro.warnMessageCause[A]

  def warn(message: String, args: Any*)(implicit a: A): Unit = macro Scala2LoggerTakingImplicitMacro.warnMessageArgs[A]

  def warn(marker: Marker, message: String)(implicit a: A): Unit = macro
    Scala2LoggerTakingImplicitMacro.warnMessageMarker[A]

  def warn(marker: Marker, message: String, cause: Throwable)(implicit a: A): Unit = macro
    Scala2LoggerTakingImplicitMacro.warnMessageCauseMarker[A]

  def warn(marker: Marker, message: String, args: Any*)(implicit a: A): Unit = macro
    Scala2LoggerTakingImplicitMacro.warnMessageArgsMarker[A]

  def whenWarnEnabled(body: Unit)(implicit a: A): Unit = macro Scala2LoggerTakingImplicitMacro.warnCode[A]

  // Info

  def info(message: String)(implicit a: A): Unit = macro Scala2LoggerTakingImplicitMacro.infoMessage[A]

  def info(message: String, cause: Throwable)(implicit a: A): Unit = macro
    Scala2LoggerTakingImplicitMacro.infoMessageCause[A]

  def info(message: String, args: Any*)(implicit a: A): Unit = macro Scala2LoggerTakingImplicitMacro.infoMessageArgs[A]

  def info(marker: Marker, message: String)(implicit a: A): Unit = macro
    Scala2LoggerTakingImplicitMacro.infoMessageMarker[A]

  def info(marker: Marker, message: String, cause: Throwable)(implicit a: A): Unit = macro
    Scala2LoggerTakingImplicitMacro.infoMessageCauseMarker[A]

  def info(marker: Marker, message: String, args: Any*)(implicit a: A): Unit = macro
    Scala2LoggerTakingImplicitMacro.infoMessageArgsMarker[A]

  def whenInfoEnabled(body: Unit)(implicit a: A): Unit = macro Scala2LoggerTakingImplicitMacro.infoCode[A]

  // Debug

  def debug(message: String)(implicit a: A): Unit = macro Scala2LoggerTakingImplicitMacro.debugMessage[A]

  def debug(message: String, cause: Throwable)(implicit a: A): Unit = macro
    Scala2LoggerTakingImplicitMacro.debugMessageCause[A]

  def debug(message: String, args: Any*)(implicit a: A): Unit = macro
    Scala2LoggerTakingImplicitMacro.debugMessageArgs[A]

  def debug(marker: Marker, message: String)(implicit a: A): Unit = macro
    Scala2LoggerTakingImplicitMacro.debugMessageMarker[A]

  def debug(marker: Marker, message: String, cause: Throwable)(implicit a: A): Unit = macro
    Scala2LoggerTakingImplicitMacro.debugMessageCauseMarker[A]

  def debug(marker: Marker, message: String, args: Any*)(implicit a: A): Unit = macro
    Scala2LoggerTakingImplicitMacro.debugMessageArgsMarker[A]

  def whenDebugEnabled(body: Unit)(implicit a: A): Unit = macro Scala2LoggerTakingImplicitMacro.debugCode[A]

  // Trace

  def trace(message: String)(implicit a: A): Unit = macro Scala2LoggerTakingImplicitMacro.traceMessage[A]

  def trace(message: String, cause: Throwable)(implicit a: A): Unit = macro
    Scala2LoggerTakingImplicitMacro.traceMessageCause[A]

  def trace(message: String, args: Any*)(implicit a: A): Unit = macro
    Scala2LoggerTakingImplicitMacro.traceMessageArgs[A]

  def trace(marker: Marker, message: String)(implicit a: A): Unit = macro
    Scala2LoggerTakingImplicitMacro.traceMessageMarker[A]

  def trace(marker: Marker, message: String, cause: Throwable)(implicit a: A): Unit = macro
    Scala2LoggerTakingImplicitMacro.traceMessageCauseMarker[A]

  def trace(marker: Marker, message: String, args: Any*)(implicit a: A): Unit = macro
    Scala2LoggerTakingImplicitMacro.traceMessageArgsMarker[A]

  def whenTraceEnabled(body: Unit)(implicit a: A): Unit = macro Scala2LoggerTakingImplicitMacro.traceCode[A]

}
