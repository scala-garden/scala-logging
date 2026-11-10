package com.typesafe.scalalogging

import org.slf4j.{Marker, Logger as Underlying}
import scala.language.experimental.macros

trait LoggerImpl {
  def underlying: Underlying

  // Error
  inline def error(inline message: String): Unit = ${ LoggerMacro.errorMessage('underlying, 'message) }
  inline def error(inline message: String, inline cause: Throwable): Unit = ${
    LoggerMacro.errorMessageCause('underlying, 'message, 'cause)
  }
  inline def error(inline message: String, inline args: Any*): Unit = ${
    LoggerMacro.errorMessageArgs('underlying, 'message, 'args)
  }
  inline def error(inline marker: Marker, inline message: String): Unit = ${
    LoggerMacro.errorMessageMarker('underlying, 'marker, 'message)
  }
  inline def error(inline marker: Marker, inline message: String, inline cause: Throwable): Unit = ${
    LoggerMacro.errorMessageCauseMarker('underlying, 'marker, 'message, 'cause)
  }
  inline def error(inline marker: Marker, inline message: String, inline args: Any*): Unit = ${
    LoggerMacro.errorMessageArgsMarker('underlying, 'marker, 'message, 'args)
  }
  inline def whenErrorEnabled(inline body: Unit): Unit = ${ LoggerMacro.errorCode('underlying, 'body) }

  // Warn
  inline def warn(inline message: String): Unit = ${ LoggerMacro.warnMessage('underlying, 'message) }
  inline def warn(inline message: String, inline cause: Throwable): Unit = ${
    LoggerMacro.warnMessageCause('underlying, 'message, 'cause)
  }
  inline def warn(inline message: String, inline args: Any*): Unit = ${
    LoggerMacro.warnMessageArgs('underlying, 'message, 'args)
  }
  inline def warn(inline marker: Marker, inline message: String): Unit = ${
    LoggerMacro.warnMessageMarker('underlying, 'marker, 'message)
  }
  inline def warn(inline marker: Marker, inline message: String, inline cause: Throwable): Unit = ${
    LoggerMacro.warnMessageCauseMarker('underlying, 'marker, 'message, 'cause)
  }
  inline def warn(inline marker: Marker, inline message: String, inline args: Any*): Unit = ${
    LoggerMacro.warnMessageArgsMarker('underlying, 'marker, 'message, 'args)
  }
  inline def whenWarnEnabled(inline body: Unit): Unit = ${ LoggerMacro.warnCode('underlying, 'body) }

  // Info
  inline def info(inline message: String): Unit = ${ LoggerMacro.infoMessage('underlying, 'message) }
  inline def info(inline message: String, inline cause: Throwable): Unit = ${
    LoggerMacro.infoMessageCause('underlying, 'message, 'cause)
  }
  inline def info(inline message: String, inline args: Any*): Unit = ${
    LoggerMacro.infoMessageArgs('underlying, 'message, 'args)
  }
  inline def info(inline marker: Marker, inline message: String): Unit = ${
    LoggerMacro.infoMessageMarker('underlying, 'marker, 'message)
  }
  inline def info(inline marker: Marker, inline message: String, inline cause: Throwable): Unit = ${
    LoggerMacro.infoMessageCauseMarker('underlying, 'marker, 'message, 'cause)
  }
  inline def info(inline marker: Marker, inline message: String, inline args: Any*): Unit = ${
    LoggerMacro.infoMessageArgsMarker('underlying, 'marker, 'message, 'args)
  }
  inline def whenInfoEnabled(inline body: Unit): Unit = ${ LoggerMacro.infoCode('underlying, 'body) }

  // Debug
  inline def debug(inline message: String): Unit = ${ LoggerMacro.debugMessage('underlying, 'message) }
  inline def debug(inline message: String, inline cause: Throwable): Unit = ${
    LoggerMacro.debugMessageCause('underlying, 'message, 'cause)
  }
  inline def debug(inline message: String, inline args: Any*): Unit = ${
    LoggerMacro.debugMessageArgs('underlying, 'message, 'args)
  }
  inline def debug(inline marker: Marker, inline message: String): Unit = ${
    LoggerMacro.debugMessageMarker('underlying, 'marker, 'message)
  }
  inline def debug(inline marker: Marker, inline message: String, inline cause: Throwable): Unit = ${
    LoggerMacro.debugMessageCauseMarker('underlying, 'marker, 'message, 'cause)
  }
  inline def debug(inline marker: Marker, inline message: String, inline args: Any*): Unit = ${
    LoggerMacro.debugMessageArgsMarker('underlying, 'marker, 'message, 'args)
  }
  inline def whenDebugEnabled(inline body: Unit): Unit = ${ LoggerMacro.debugCode('underlying, 'body) }

  // Trace
  inline def trace(inline message: String): Unit = ${ LoggerMacro.traceMessage('underlying, 'message) }
  inline def trace(inline message: String, inline cause: Throwable): Unit = ${
    LoggerMacro.traceMessageCause('underlying, 'message, 'cause)
  }
  inline def trace(inline message: String, inline args: Any*): Unit = ${
    LoggerMacro.traceMessageArgs('underlying, 'message, 'args)
  }
  inline def trace(inline marker: Marker, inline message: String): Unit = ${
    LoggerMacro.traceMessageMarker('underlying, 'marker, 'message)
  }
  inline def trace(inline marker: Marker, inline message: String, inline cause: Throwable): Unit = ${
    LoggerMacro.traceMessageCauseMarker('underlying, 'marker, 'message, 'cause)
  }
  inline def trace(inline marker: Marker, inline message: String, inline args: Any*): Unit = ${
    LoggerMacro.traceMessageArgsMarker('underlying, 'marker, 'message, 'args)
  }
  inline def whenTraceEnabled(inline body: Unit): Unit = ${ LoggerMacro.traceCode('underlying, 'body) }

  // scala 2

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
