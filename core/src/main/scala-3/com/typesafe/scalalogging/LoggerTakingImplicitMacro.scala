package com.typesafe.scalalogging

import org.slf4j.{Marker, Logger => Underlying}

import scala.quoted.*

private[scalalogging] object LoggerTakingImplicitMacro {

  // Error

  def errorMessage[A: Type](underlying: Expr[Underlying], canLogEv: Expr[CanLog[A]], message: Expr[String])(
      a: Expr[A]
  )(using Quotes): Expr[Unit] =
    '{
      if ($underlying.isErrorEnabled) {
        $underlying.error($canLogEv.logMessage($message, $a))
        $canLogEv.afterLog($a)
      }
    }

  def errorMessageCause[A: Type](
      underlying: Expr[Underlying],
      canLogEv: Expr[CanLog[A]],
      message: Expr[String],
      cause: Expr[Throwable]
  )(a: Expr[A])(using Quotes): Expr[Unit] =
    '{
      if ($underlying.isErrorEnabled) {
        $underlying.error($canLogEv.logMessage($message, $a), $cause)
        $canLogEv.afterLog($a)
      }
    }

  def errorMessageArgs[A: Type](
      underlying: Expr[Underlying],
      canLogEv: Expr[CanLog[A]],
      message: Expr[String],
      args: Expr[Seq[Any]]
  )(a: Expr[A])(using Quotes): Expr[Unit] = {
    val formatedArgs = LoggerMacro.formatArgs(args)
    if (formatedArgs.lengthIs == 1)
      '{
        if ($underlying.isErrorEnabled) {
          $underlying.error($canLogEv.logMessage($message, $a), ${ formatedArgs.head })
          $canLogEv.afterLog($a)
        }
      }
    else
      '{
        if ($underlying.isErrorEnabled) {
          $underlying.error($canLogEv.logMessage($message, $a), ${ Expr.ofSeq(formatedArgs) }*)
          $canLogEv.afterLog($a)
        }
      }
  }

  def errorMessageMarker[A: Type](
      underlying: Expr[Underlying],
      canLogEv: Expr[CanLog[A]],
      marker: Expr[Marker],
      message: Expr[String]
  )(a: Expr[A])(using Quotes): Expr[Unit] =
    '{
      if ($underlying.isErrorEnabled($marker)) {
        $underlying.error($marker, $canLogEv.logMessage($message, $a))
        $canLogEv.afterLog($a)
      }
    }

  def errorMessageCauseMarker[A: Type](
      underlying: Expr[Underlying],
      canLogEv: Expr[CanLog[A]],
      marker: Expr[Marker],
      message: Expr[String],
      cause: Expr[Throwable]
  )(a: Expr[A])(using Quotes): Expr[Unit] =
    '{
      if ($underlying.isErrorEnabled($marker)) {
        $underlying.error($marker, $canLogEv.logMessage($message, $a), $cause)
        $canLogEv.afterLog($a)
      }
    }

  def errorMessageArgsMarker[A: Type](
      underlying: Expr[Underlying],
      canLogEv: Expr[CanLog[A]],
      marker: Expr[Marker],
      message: Expr[String],
      args: Expr[Seq[Any]]
  )(a: Expr[A])(using Quotes): Expr[Unit] = {
    val formatedArgs = LoggerMacro.formatArgs(args)
    if (formatedArgs.lengthIs == 1)
      '{
        if ($underlying.isErrorEnabled($marker)) {
          $underlying.error($marker, $canLogEv.logMessage($message, $a), ${ formatedArgs.head })
          $canLogEv.afterLog($a)
        }
      }
    else
      '{
        if ($underlying.isErrorEnabled($marker)) {
          $underlying.error($marker, $canLogEv.logMessage($message, $a), ${ Expr.ofSeq(formatedArgs) }*)
          $canLogEv.afterLog($a)
        }
      }
  }

  def errorCode[A: Type](underlying: Expr[Underlying], canLogEv: Expr[CanLog[A]], body: Expr[Unit])(
      a: Expr[A]
  )(using Quotes) =
    '{
      if ($underlying.isErrorEnabled) {
        $body
        $canLogEv.afterLog($a)
      }
    }

  // Warn

  def warnMessage[A: Type](underlying: Expr[Underlying], canLogEv: Expr[CanLog[A]], message: Expr[String])(
      a: Expr[A]
  )(using Quotes): Expr[Unit] =
    '{
      if ($underlying.isWarnEnabled) {
        $underlying.warn($canLogEv.logMessage($message, $a))
        $canLogEv.afterLog($a)
      }
    }

  def warnMessageCause[A: Type](
      underlying: Expr[Underlying],
      canLogEv: Expr[CanLog[A]],
      message: Expr[String],
      cause: Expr[Throwable]
  )(a: Expr[A])(using Quotes): Expr[Unit] =
    '{
      if ($underlying.isWarnEnabled) {
        $underlying.warn($canLogEv.logMessage($message, $a), $cause)
        $canLogEv.afterLog($a)
      }
    }

  def warnMessageArgs[A: Type](
      underlying: Expr[Underlying],
      canLogEv: Expr[CanLog[A]],
      message: Expr[String],
      args: Expr[Seq[Any]]
  )(a: Expr[A])(using Quotes): Expr[Unit] = {
    val formatedArgs = LoggerMacro.formatArgs(args)
    if (formatedArgs.lengthIs == 1)
      '{
        if ($underlying.isWarnEnabled) {
          $underlying.warn($canLogEv.logMessage($message, $a), ${ formatedArgs.head })
          $canLogEv.afterLog($a)
        }
      }
    else
      '{
        if ($underlying.isWarnEnabled) {
          $underlying.warn($canLogEv.logMessage($message, $a), ${ Expr.ofSeq(formatedArgs) }*)
          $canLogEv.afterLog($a)
        }
      }
  }

  def warnMessageMarker[A: Type](
      underlying: Expr[Underlying],
      canLogEv: Expr[CanLog[A]],
      marker: Expr[Marker],
      message: Expr[String]
  )(a: Expr[A])(using Quotes): Expr[Unit] =
    '{
      if ($underlying.isWarnEnabled($marker)) {
        $underlying.warn($marker, $canLogEv.logMessage($message, $a))
        $canLogEv.afterLog($a)
      }
    }

  def warnMessageCauseMarker[A: Type](
      underlying: Expr[Underlying],
      canLogEv: Expr[CanLog[A]],
      marker: Expr[Marker],
      message: Expr[String],
      cause: Expr[Throwable]
  )(a: Expr[A])(using Quotes): Expr[Unit] =
    '{
      if ($underlying.isWarnEnabled($marker)) {
        $underlying.warn($marker, $canLogEv.logMessage($message, $a), $cause)
        $canLogEv.afterLog($a)
      }
    }

  def warnMessageArgsMarker[A: Type](
      underlying: Expr[Underlying],
      canLogEv: Expr[CanLog[A]],
      marker: Expr[Marker],
      message: Expr[String],
      args: Expr[Seq[Any]]
  )(a: Expr[A])(using Quotes): Expr[Unit] = {
    val formatedArgs = LoggerMacro.formatArgs(args)
    if (formatedArgs.lengthIs == 1)
      '{
        if ($underlying.isWarnEnabled($marker)) {
          $underlying.warn($marker, $canLogEv.logMessage($message, $a), ${ formatedArgs.head })
          $canLogEv.afterLog($a)
        }
      }
    else
      '{
        if ($underlying.isWarnEnabled($marker)) {
          $underlying.warn($marker, $canLogEv.logMessage($message, $a), ${ Expr.ofSeq(formatedArgs) }*)
          $canLogEv.afterLog($a)
        }
      }
  }

  def warnCode[A: Type](underlying: Expr[Underlying], canLogEv: Expr[CanLog[A]], body: Expr[Unit])(
      a: Expr[A]
  )(using Quotes) =
    '{
      if ($underlying.isWarnEnabled) {
        $body
        $canLogEv.afterLog($a)
      }
    }

  // Info

  def infoMessage[A: Type](underlying: Expr[Underlying], canLogEv: Expr[CanLog[A]], message: Expr[String])(
      a: Expr[A]
  )(using Quotes): Expr[Unit] =
    '{
      if ($underlying.isInfoEnabled) {
        $underlying.info($canLogEv.logMessage($message, $a))
        $canLogEv.afterLog($a)
      }
    }

  def infoMessageCause[A: Type](
      underlying: Expr[Underlying],
      canLogEv: Expr[CanLog[A]],
      message: Expr[String],
      cause: Expr[Throwable]
  )(a: Expr[A])(using Quotes): Expr[Unit] =
    '{
      if ($underlying.isInfoEnabled) {
        $underlying.info($canLogEv.logMessage($message, $a), $cause)
        $canLogEv.afterLog($a)
      }
    }

  def infoMessageArgs[A: Type](
      underlying: Expr[Underlying],
      canLogEv: Expr[CanLog[A]],
      message: Expr[String],
      args: Expr[Seq[Any]]
  )(a: Expr[A])(using Quotes): Expr[Unit] = {
    val formatedArgs = LoggerMacro.formatArgs(args)
    if (formatedArgs.lengthIs == 1)
      '{
        if ($underlying.isInfoEnabled) {
          $underlying.info($canLogEv.logMessage($message, $a), ${ formatedArgs.head })
          $canLogEv.afterLog($a)
        }
      }
    else
      '{
        if ($underlying.isInfoEnabled) {
          $underlying.info($canLogEv.logMessage($message, $a), ${ Expr.ofSeq(formatedArgs) }*)
          $canLogEv.afterLog($a)
        }
      }
  }

  def infoMessageMarker[A: Type](
      underlying: Expr[Underlying],
      canLogEv: Expr[CanLog[A]],
      marker: Expr[Marker],
      message: Expr[String]
  )(a: Expr[A])(using Quotes): Expr[Unit] =
    '{
      if ($underlying.isInfoEnabled($marker)) {
        $underlying.info($marker, $canLogEv.logMessage($message, $a))
        $canLogEv.afterLog($a)
      }
    }

  def infoMessageCauseMarker[A: Type](
      underlying: Expr[Underlying],
      canLogEv: Expr[CanLog[A]],
      marker: Expr[Marker],
      message: Expr[String],
      cause: Expr[Throwable]
  )(a: Expr[A])(using Quotes): Expr[Unit] =
    '{
      if ($underlying.isInfoEnabled($marker)) {
        $underlying.info($marker, $canLogEv.logMessage($message, $a), $cause)
        $canLogEv.afterLog($a)
      }
    }

  def infoMessageArgsMarker[A: Type](
      underlying: Expr[Underlying],
      canLogEv: Expr[CanLog[A]],
      marker: Expr[Marker],
      message: Expr[String],
      args: Expr[Seq[Any]]
  )(a: Expr[A])(using Quotes): Expr[Unit] = {
    val formatedArgs = LoggerMacro.formatArgs(args)
    if (formatedArgs.lengthIs == 1)
      '{
        if ($underlying.isInfoEnabled($marker)) {
          $underlying.info($marker, $canLogEv.logMessage($message, $a), ${ formatedArgs.head })
          $canLogEv.afterLog($a)
        }
      }
    else
      '{
        if ($underlying.isInfoEnabled($marker)) {
          $underlying.info($marker, $canLogEv.logMessage($message, $a), ${ Expr.ofSeq(formatedArgs) }*)
          $canLogEv.afterLog($a)
        }
      }
  }

  def infoCode[A: Type](underlying: Expr[Underlying], canLogEv: Expr[CanLog[A]], body: Expr[Unit])(
      a: Expr[A]
  )(using Quotes) =
    '{
      if ($underlying.isInfoEnabled) {
        $body
        $canLogEv.afterLog($a)
      }
    }

  // Debug

  def debugMessage[A: Type](underlying: Expr[Underlying], canLogEv: Expr[CanLog[A]], message: Expr[String])(
      a: Expr[A]
  )(using Quotes): Expr[Unit] =
    '{
      if ($underlying.isDebugEnabled) {
        $underlying.debug($canLogEv.logMessage($message, $a))
        $canLogEv.afterLog($a)
      }
    }

  def debugMessageCause[A: Type](
      underlying: Expr[Underlying],
      canLogEv: Expr[CanLog[A]],
      message: Expr[String],
      cause: Expr[Throwable]
  )(a: Expr[A])(using Quotes): Expr[Unit] =
    '{
      if ($underlying.isDebugEnabled) {
        $underlying.debug($canLogEv.logMessage($message, $a), $cause)
        $canLogEv.afterLog($a)
      }
    }

  def debugMessageArgs[A: Type](
      underlying: Expr[Underlying],
      canLogEv: Expr[CanLog[A]],
      message: Expr[String],
      args: Expr[Seq[Any]]
  )(a: Expr[A])(using Quotes): Expr[Unit] = {
    val formatedArgs = LoggerMacro.formatArgs(args)
    if (formatedArgs.lengthIs == 1)
      '{
        if ($underlying.isDebugEnabled) {
          $underlying.debug($canLogEv.logMessage($message, $a), ${ formatedArgs.head })
          $canLogEv.afterLog($a)
        }
      }
    else
      '{
        if ($underlying.isDebugEnabled) {
          $underlying.debug($canLogEv.logMessage($message, $a), ${ Expr.ofSeq(formatedArgs) }*)
          $canLogEv.afterLog($a)
        }
      }
  }

  def debugMessageMarker[A: Type](
      underlying: Expr[Underlying],
      canLogEv: Expr[CanLog[A]],
      marker: Expr[Marker],
      message: Expr[String]
  )(a: Expr[A])(using Quotes): Expr[Unit] =
    '{
      if ($underlying.isDebugEnabled($marker)) {
        $underlying.debug($marker, $canLogEv.logMessage($message, $a))
        $canLogEv.afterLog($a)
      }
    }

  def debugMessageCauseMarker[A: Type](
      underlying: Expr[Underlying],
      canLogEv: Expr[CanLog[A]],
      marker: Expr[Marker],
      message: Expr[String],
      cause: Expr[Throwable]
  )(a: Expr[A])(using Quotes): Expr[Unit] =
    '{
      if ($underlying.isDebugEnabled($marker)) {
        $underlying.debug($marker, $canLogEv.logMessage($message, $a), $cause)
        $canLogEv.afterLog($a)
      }
    }

  def debugMessageArgsMarker[A: Type](
      underlying: Expr[Underlying],
      canLogEv: Expr[CanLog[A]],
      marker: Expr[Marker],
      message: Expr[String],
      args: Expr[Seq[Any]]
  )(a: Expr[A])(using Quotes): Expr[Unit] = {
    val formatedArgs = LoggerMacro.formatArgs(args)
    if (formatedArgs.lengthIs == 1)
      '{
        if ($underlying.isDebugEnabled($marker)) {
          $underlying.debug($marker, $canLogEv.logMessage($message, $a), ${ formatedArgs.head })
          $canLogEv.afterLog($a)
        }
      }
    else
      '{
        if ($underlying.isDebugEnabled($marker)) {
          $underlying.debug($marker, $canLogEv.logMessage($message, $a), ${ Expr.ofSeq(formatedArgs) }*)
          $canLogEv.afterLog($a)
        }
      }
  }

  def debugCode[A: Type](underlying: Expr[Underlying], canLogEv: Expr[CanLog[A]], body: Expr[Unit])(
      a: Expr[A]
  )(using Quotes) =
    '{
      if ($underlying.isDebugEnabled) {
        $body
        $canLogEv.afterLog($a)
      }
    }

  // Trace

  def traceMessage[A: Type](underlying: Expr[Underlying], canLogEv: Expr[CanLog[A]], message: Expr[String])(
      a: Expr[A]
  )(using Quotes): Expr[Unit] =
    '{
      if ($underlying.isTraceEnabled) {
        $underlying.trace($canLogEv.logMessage($message, $a))
        $canLogEv.afterLog($a)
      }
    }

  def traceMessageCause[A: Type](
      underlying: Expr[Underlying],
      canLogEv: Expr[CanLog[A]],
      message: Expr[String],
      cause: Expr[Throwable]
  )(a: Expr[A])(using Quotes): Expr[Unit] =
    '{
      if ($underlying.isTraceEnabled) {
        $underlying.trace($canLogEv.logMessage($message, $a), $cause)
        $canLogEv.afterLog($a)
      }
    }

  def traceMessageArgs[A: Type](
      underlying: Expr[Underlying],
      canLogEv: Expr[CanLog[A]],
      message: Expr[String],
      args: Expr[Seq[Any]]
  )(a: Expr[A])(using Quotes): Expr[Unit] = {
    val formatedArgs = LoggerMacro.formatArgs(args)
    if (formatedArgs.lengthIs == 1)
      '{
        if ($underlying.isTraceEnabled) {
          $underlying.trace($canLogEv.logMessage($message, $a), ${ formatedArgs.head })
          $canLogEv.afterLog($a)
        }
      }
    else
      '{
        if ($underlying.isTraceEnabled) {
          $underlying.trace($canLogEv.logMessage($message, $a), ${ Expr.ofSeq(formatedArgs) }*)
          $canLogEv.afterLog($a)
        }
      }
  }

  def traceMessageMarker[A: Type](
      underlying: Expr[Underlying],
      canLogEv: Expr[CanLog[A]],
      marker: Expr[Marker],
      message: Expr[String]
  )(a: Expr[A])(using Quotes): Expr[Unit] =
    '{
      if ($underlying.isTraceEnabled($marker)) {
        $underlying.trace($marker, $canLogEv.logMessage($message, $a))
        $canLogEv.afterLog($a)
      }
    }

  def traceMessageCauseMarker[A: Type](
      underlying: Expr[Underlying],
      canLogEv: Expr[CanLog[A]],
      marker: Expr[Marker],
      message: Expr[String],
      cause: Expr[Throwable]
  )(a: Expr[A])(using Quotes): Expr[Unit] =
    '{
      if ($underlying.isTraceEnabled($marker)) {
        $underlying.trace($marker, $canLogEv.logMessage($message, $a), $cause)
        $canLogEv.afterLog($a)
      }
    }

  def traceMessageArgsMarker[A: Type](
      underlying: Expr[Underlying],
      canLogEv: Expr[CanLog[A]],
      marker: Expr[Marker],
      message: Expr[String],
      args: Expr[Seq[Any]]
  )(a: Expr[A])(using Quotes): Expr[Unit] = {
    val formatedArgs = LoggerMacro.formatArgs(args)
    if (formatedArgs.lengthIs == 1)
      '{
        if ($underlying.isTraceEnabled($marker)) {
          $underlying.trace($marker, $canLogEv.logMessage($message, $a), ${ formatedArgs.head })
          $canLogEv.afterLog($a)
        }
      }
    else
      '{
        if ($underlying.isTraceEnabled($marker)) {
          $underlying.trace($marker, $canLogEv.logMessage($message, $a), ${ Expr.ofSeq(formatedArgs) }*)
          $canLogEv.afterLog($a)
        }
      }
  }

  def traceCode[A: Type](underlying: Expr[Underlying], canLogEv: Expr[CanLog[A]], body: Expr[Unit])(
      a: Expr[A]
  )(using Quotes) =
    '{
      if ($underlying.isTraceEnabled) {
        $body
        $canLogEv.afterLog($a)
      }
    }
}
