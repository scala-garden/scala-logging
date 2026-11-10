package com.typesafe.scalalogging

import org.slf4j.Marker

class LoggerTakingImplicitImpl[A] private[scalalogging] {

  // Error

  def error(message: String)(implicit a: A): Unit = macro Scala2LoggerTakingImplicitMacro.errorMessage[A]

  def error(message: String, cause: Throwable)(implicit a: A): Unit =
    macro Scala2LoggerTakingImplicitMacro.errorMessageCause[A]

  def error(message: String, args: Any*)(implicit a: A): Unit =
    macro Scala2LoggerTakingImplicitMacro.errorMessageArgs[A]

  def error(marker: Marker, message: String)(implicit a: A): Unit =
    macro Scala2LoggerTakingImplicitMacro.errorMessageMarker[A]

  def error(marker: Marker, message: String, cause: Throwable)(implicit a: A): Unit =
    macro Scala2LoggerTakingImplicitMacro.errorMessageCauseMarker[A]

  def error(marker: Marker, message: String, args: Any*)(implicit a: A): Unit =
    macro Scala2LoggerTakingImplicitMacro.errorMessageArgsMarker[A]

  def whenErrorEnabled(body: Unit)(implicit a: A): Unit = macro Scala2LoggerTakingImplicitMacro.errorCode[A]

  // Warn

  def warn(message: String)(implicit a: A): Unit = macro Scala2LoggerTakingImplicitMacro.warnMessage[A]

  def warn(message: String, cause: Throwable)(implicit a: A): Unit =
    macro Scala2LoggerTakingImplicitMacro.warnMessageCause[A]

  def warn(message: String, args: Any*)(implicit a: A): Unit = macro Scala2LoggerTakingImplicitMacro.warnMessageArgs[A]

  def warn(marker: Marker, message: String)(implicit a: A): Unit =
    macro Scala2LoggerTakingImplicitMacro.warnMessageMarker[A]

  def warn(marker: Marker, message: String, cause: Throwable)(implicit a: A): Unit =
    macro Scala2LoggerTakingImplicitMacro.warnMessageCauseMarker[A]

  def warn(marker: Marker, message: String, args: Any*)(implicit a: A): Unit =
    macro Scala2LoggerTakingImplicitMacro.warnMessageArgsMarker[A]

  def whenWarnEnabled(body: Unit)(implicit a: A): Unit = macro Scala2LoggerTakingImplicitMacro.warnCode[A]

  // Info

  def info(message: String)(implicit a: A): Unit = macro Scala2LoggerTakingImplicitMacro.infoMessage[A]

  def info(message: String, cause: Throwable)(implicit a: A): Unit =
    macro Scala2LoggerTakingImplicitMacro.infoMessageCause[A]

  def info(message: String, args: Any*)(implicit a: A): Unit = macro Scala2LoggerTakingImplicitMacro.infoMessageArgs[A]

  def info(marker: Marker, message: String)(implicit a: A): Unit =
    macro Scala2LoggerTakingImplicitMacro.infoMessageMarker[A]

  def info(marker: Marker, message: String, cause: Throwable)(implicit a: A): Unit =
    macro Scala2LoggerTakingImplicitMacro.infoMessageCauseMarker[A]

  def info(marker: Marker, message: String, args: Any*)(implicit a: A): Unit =
    macro Scala2LoggerTakingImplicitMacro.infoMessageArgsMarker[A]

  def whenInfoEnabled(body: Unit)(implicit a: A): Unit = macro Scala2LoggerTakingImplicitMacro.infoCode[A]

  // Debug

  def debug(message: String)(implicit a: A): Unit = macro Scala2LoggerTakingImplicitMacro.debugMessage[A]

  def debug(message: String, cause: Throwable)(implicit a: A): Unit =
    macro Scala2LoggerTakingImplicitMacro.debugMessageCause[A]

  def debug(message: String, args: Any*)(implicit a: A): Unit =
    macro Scala2LoggerTakingImplicitMacro.debugMessageArgs[A]

  def debug(marker: Marker, message: String)(implicit a: A): Unit =
    macro Scala2LoggerTakingImplicitMacro.debugMessageMarker[A]

  def debug(marker: Marker, message: String, cause: Throwable)(implicit a: A): Unit =
    macro Scala2LoggerTakingImplicitMacro.debugMessageCauseMarker[A]

  def debug(marker: Marker, message: String, args: Any*)(implicit a: A): Unit =
    macro Scala2LoggerTakingImplicitMacro.debugMessageArgsMarker[A]

  def whenDebugEnabled(body: Unit)(implicit a: A): Unit = macro Scala2LoggerTakingImplicitMacro.debugCode[A]

  // Trace

  def trace(message: String)(implicit a: A): Unit = macro Scala2LoggerTakingImplicitMacro.traceMessage[A]

  def trace(message: String, cause: Throwable)(implicit a: A): Unit =
    macro Scala2LoggerTakingImplicitMacro.traceMessageCause[A]

  def trace(message: String, args: Any*)(implicit a: A): Unit =
    macro Scala2LoggerTakingImplicitMacro.traceMessageArgs[A]

  def trace(marker: Marker, message: String)(implicit a: A): Unit =
    macro Scala2LoggerTakingImplicitMacro.traceMessageMarker[A]

  def trace(marker: Marker, message: String, cause: Throwable)(implicit a: A): Unit =
    macro Scala2LoggerTakingImplicitMacro.traceMessageCauseMarker[A]

  def trace(marker: Marker, message: String, args: Any*)(implicit a: A): Unit =
    macro Scala2LoggerTakingImplicitMacro.traceMessageArgsMarker[A]

  def whenTraceEnabled(body: Unit)(implicit a: A): Unit = macro Scala2LoggerTakingImplicitMacro.traceCode[A]
}
