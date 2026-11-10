package com.typesafe.scalalogging

import org.slf4j.Marker
class LoggerImpl {

  // Error

  def error(message: String): Unit = macro Scala2LoggerMacro.errorMessage

  def error(message: String, cause: Throwable): Unit = macro Scala2LoggerMacro.errorMessageCause

  def error(message: String, args: Any*): Unit = macro Scala2LoggerMacro.errorMessageArgs

  def error(marker: Marker, message: String): Unit = macro Scala2LoggerMacro.errorMessageMarker

  def error(marker: Marker, message: String, cause: Throwable): Unit = macro Scala2LoggerMacro.errorMessageCauseMarker

  def error(marker: Marker, message: String, args: Any*): Unit = macro Scala2LoggerMacro.errorMessageArgsMarker

  def whenErrorEnabled(body: Unit): Unit = macro Scala2LoggerMacro.errorCode

  // Warn

  def warn(message: String): Unit = macro Scala2LoggerMacro.warnMessage

  def warn(message: String, cause: Throwable): Unit = macro Scala2LoggerMacro.warnMessageCause

  def warn(message: String, args: Any*): Unit = macro Scala2LoggerMacro.warnMessageArgs

  def warn(marker: Marker, message: String): Unit = macro Scala2LoggerMacro.warnMessageMarker

  def warn(marker: Marker, message: String, cause: Throwable): Unit = macro Scala2LoggerMacro.warnMessageCauseMarker

  def warn(marker: Marker, message: String, args: Any*): Unit = macro Scala2LoggerMacro.warnMessageArgsMarker

  def whenWarnEnabled(body: Unit): Unit = macro Scala2LoggerMacro.warnCode

  // Info

  def info(message: String): Unit = macro Scala2LoggerMacro.infoMessage

  def info(message: String, cause: Throwable): Unit = macro Scala2LoggerMacro.infoMessageCause

  def info(message: String, args: Any*): Unit = macro Scala2LoggerMacro.infoMessageArgs

  def info(marker: Marker, message: String): Unit = macro Scala2LoggerMacro.infoMessageMarker

  def info(marker: Marker, message: String, cause: Throwable): Unit = macro Scala2LoggerMacro.infoMessageCauseMarker

  def info(marker: Marker, message: String, args: Any*): Unit = macro Scala2LoggerMacro.infoMessageArgsMarker

  def whenInfoEnabled(body: Unit): Unit = macro Scala2LoggerMacro.infoCode

  // Debug

  def debug(message: String): Unit = macro Scala2LoggerMacro.debugMessage

  def debug(message: String, cause: Throwable): Unit = macro Scala2LoggerMacro.debugMessageCause

  def debug(message: String, args: Any*): Unit = macro Scala2LoggerMacro.debugMessageArgs

  def debug(marker: Marker, message: String): Unit = macro Scala2LoggerMacro.debugMessageMarker

  def debug(marker: Marker, message: String, cause: Throwable): Unit = macro Scala2LoggerMacro.debugMessageCauseMarker

  def debug(marker: Marker, message: String, args: Any*): Unit = macro Scala2LoggerMacro.debugMessageArgsMarker

  def whenDebugEnabled(body: Unit): Unit = macro Scala2LoggerMacro.debugCode

  // Trace

  def trace(message: String): Unit = macro Scala2LoggerMacro.traceMessage

  def trace(message: String, cause: Throwable): Unit = macro Scala2LoggerMacro.traceMessageCause

  def trace(message: String, args: Any*): Unit = macro Scala2LoggerMacro.traceMessageArgs

  def trace(marker: Marker, message: String): Unit = macro Scala2LoggerMacro.traceMessageMarker

  def trace(marker: Marker, message: String, cause: Throwable): Unit = macro Scala2LoggerMacro.traceMessageCauseMarker

  def trace(marker: Marker, message: String, args: Any*): Unit = macro Scala2LoggerMacro.traceMessageArgsMarker

  def whenTraceEnabled(body: Unit): Unit = macro Scala2LoggerMacro.traceCode
}
