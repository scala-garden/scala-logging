package com.typesafe.scalalogging

import org.slf4j.Marker
import scala.reflect.macros.blackbox

private[scalalogging] object Scala2LoggerTakingImplicitMacro {

  type LoggerContext[A] = blackbox.Context

  // Error

  def errorMessage[A](c: LoggerContext[A])(message: c.Expr[String])(a: c.Expr[A]): c.universe.Tree = {
    import c.universe._
    val underlying = q"${c.prefix}.underlying"
    val canLogEv = q"${c.prefix}.canLogEv"
    q"""if ($underlying.isErrorEnabled) {
          $underlying.error($canLogEv.logMessage($message, $a))
          $canLogEv.afterLog($a)
        }"""
  }

  def errorMessageCause[A](
      c: LoggerContext[A]
  )(message: c.Expr[String], cause: c.Expr[Throwable])(a: c.Expr[A]): c.universe.Tree = {
    import c.universe._
    val underlying = q"${c.prefix}.underlying"
    val canLogEv = q"${c.prefix}.canLogEv"
    q"""if ($underlying.isErrorEnabled) {
          $underlying.error($canLogEv.logMessage($message, $a), $cause)
          $canLogEv.afterLog($a)
        }"""
  }

  def errorMessageArgs[A](
      c: LoggerContext[A]
  )(message: c.Expr[String], args: c.Expr[Any]*)(a: c.Expr[A]): c.universe.Tree = {
    import c.universe._
    val underlying = q"${c.prefix}.underlying"
    val canLogEv = q"${c.prefix}.canLogEv"
    if (args.length == 2) {
      q"""if ($underlying.isErrorEnabled) {
            $underlying.error($canLogEv.logMessage($message, $a), List(${args(0)}, ${args(1)}): _*)
            $canLogEv.afterLog($a)
          }"""
    } else {
      q"""if ($underlying.isErrorEnabled) {
            $underlying.error($canLogEv.logMessage($message, $a), ..$args)
            $canLogEv.afterLog($a)
          }"""
    }
  }

  def errorMessageMarker[A](
      c: LoggerContext[A]
  )(marker: c.Expr[Marker], message: c.Expr[String])(a: c.Expr[A]): c.universe.Tree = {
    import c.universe._
    val underlying = q"${c.prefix}.underlying"
    val canLogEv = q"${c.prefix}.canLogEv"
    q"""if ($underlying.isErrorEnabled($marker)) {
          $underlying.error($marker, $canLogEv.logMessage($message, $a))
          $canLogEv.afterLog($a)
        }"""
  }

  def errorMessageCauseMarker[A](
      c: LoggerContext[A]
  )(marker: c.Expr[Marker], message: c.Expr[String], cause: c.Expr[Throwable])(a: c.Expr[A]): c.universe.Tree = {
    import c.universe._
    val underlying = q"${c.prefix}.underlying"
    val canLogEv = q"${c.prefix}.canLogEv"
    q"""if ($underlying.isErrorEnabled($marker)) {
          $underlying.error($marker, $canLogEv.logMessage($message, $a), $cause)
          $canLogEv.afterLog($a)
        }"""
  }

  def errorMessageArgsMarker[A](
      c: LoggerContext[A]
  )(marker: c.Expr[Marker], message: c.Expr[String], args: c.Expr[Any]*)(a: c.Expr[A]): c.universe.Tree = {
    import c.universe._
    val underlying = q"${c.prefix}.underlying"
    val canLogEv = q"${c.prefix}.canLogEv"
    if (args.length == 2) {
      q"""if ($underlying.isErrorEnabled($marker)) {
          $underlying.error($marker, $canLogEv.logMessage($message, $a), List(${args(0)}, ${args(1)}): _*)
          $canLogEv.afterLog($a)
        }"""
    } else {
      q"""if ($underlying.isErrorEnabled($marker)) {
          $underlying.error($marker, $canLogEv.logMessage($message, $a), ..$args)
          $canLogEv.afterLog($a)
        }"""
    }
  }

  def errorCode[A](c: LoggerContext[A])(body: c.Expr[Unit])(a: c.Expr[A]): c.universe.Tree = {
    import c.universe._
    val underlying = q"${c.prefix}.underlying"
    val canLogEv = q"${c.prefix}.canLogEv"
    q"""if ($underlying.isErrorEnabled) {
          $underlying.error($canLogEv.logMessage($body))
          $canLogEv.afterLog($a)
        }"""
  }

  // Warn

  def warnMessage[A](c: LoggerContext[A])(message: c.Expr[String])(a: c.Expr[A]): c.universe.Tree = {
    import c.universe._
    val underlying = q"${c.prefix}.underlying"
    val canLogEv = q"${c.prefix}.canLogEv"
    q"""if ($underlying.isWarnEnabled) {
          $underlying.warn($canLogEv.logMessage($message, $a))
          $canLogEv.afterLog($a)
        }"""
  }

  def warnMessageCause[A](
      c: LoggerContext[A]
  )(message: c.Expr[String], cause: c.Expr[Throwable])(a: c.Expr[A]): c.universe.Tree = {
    import c.universe._
    val underlying = q"${c.prefix}.underlying"
    val canLogEv = q"${c.prefix}.canLogEv"
    q"""if ($underlying.isWarnEnabled) {
          $underlying.warn($canLogEv.logMessage($message, $a), $cause)
          $canLogEv.afterLog($a)
        }"""
  }

  def warnMessageArgs[A](
      c: LoggerContext[A]
  )(message: c.Expr[String], args: c.Expr[Any]*)(a: c.Expr[A]): c.universe.Tree = {
    import c.universe._
    val underlying = q"${c.prefix}.underlying"
    val canLogEv = q"${c.prefix}.canLogEv"
    if (args.length == 2) {
      q"""if ($underlying.isWarnEnabled) {
            $underlying.warn($canLogEv.logMessage($message, $a), List(${args(0)}, ${args(1)}): _*)
            $canLogEv.afterLog($a)
          }"""
    } else {
      q"""if ($underlying.isWarnEnabled) {
            $underlying.warn($canLogEv.logMessage($message, $a), ..$args)
            $canLogEv.afterLog($a)
          }"""
    }
  }

  def warnMessageMarker[A](
      c: LoggerContext[A]
  )(marker: c.Expr[Marker], message: c.Expr[String])(a: c.Expr[A]): c.universe.Tree = {
    import c.universe._
    val underlying = q"${c.prefix}.underlying"
    val canLogEv = q"${c.prefix}.canLogEv"
    q"""if ($underlying.isWarnEnabled($marker)) {
          $underlying.warn($marker, $canLogEv.logMessage($message, $a))
          $canLogEv.afterLog($a)
        }"""
  }

  def warnMessageCauseMarker[A](
      c: LoggerContext[A]
  )(marker: c.Expr[Marker], message: c.Expr[String], cause: c.Expr[Throwable])(a: c.Expr[A]): c.universe.Tree = {
    import c.universe._
    val underlying = q"${c.prefix}.underlying"
    val canLogEv = q"${c.prefix}.canLogEv"
    q"""if ($underlying.isWarnEnabled($marker)) {
          $underlying.warn($marker, $canLogEv.logMessage($message, $a), $cause)
          $canLogEv.afterLog($a)
        }"""
  }

  def warnMessageArgsMarker[A](
      c: LoggerContext[A]
  )(marker: c.Expr[Marker], message: c.Expr[String], args: c.Expr[Any]*)(a: c.Expr[A]): c.universe.Tree = {
    import c.universe._
    val underlying = q"${c.prefix}.underlying"
    val canLogEv = q"${c.prefix}.canLogEv"
    if (args.length == 2) {
      q"""if ($underlying.isWarnEnabled($marker)) {
          $underlying.warn($marker, $canLogEv.logMessage($message, $a), List(${args(0)}, ${args(1)}): _*)
          $canLogEv.afterLog($a)
        }"""
    } else {
      q"""if ($underlying.isWarnEnabled($marker)) {
          $underlying.warn($marker, $canLogEv.logMessage($message, $a), ..$args)
          $canLogEv.afterLog($a)
        }"""
    }
  }

  def warnCode[A](c: LoggerContext[A])(body: c.Expr[Unit])(a: c.Expr[A]): c.universe.Tree = {
    import c.universe._
    val underlying = q"${c.prefix}.underlying"
    val canLogEv = q"${c.prefix}.canLogEv"
    q"""if ($underlying.isWarnEnabled) {
          $underlying.warn($canLogEv.logMessage($body))
          $canLogEv.afterLog($a)
        }"""
  }

  // Info

  def infoMessage[A](c: LoggerContext[A])(message: c.Expr[String])(a: c.Expr[A]): c.universe.Tree = {
    import c.universe._
    val underlying = q"${c.prefix}.underlying"
    val canLogEv = q"${c.prefix}.canLogEv"
    q"""if ($underlying.isInfoEnabled) {
          $underlying.info($canLogEv.logMessage($message, $a))
          $canLogEv.afterLog($a)
        }"""
  }

  def infoMessageCause[A](
      c: LoggerContext[A]
  )(message: c.Expr[String], cause: c.Expr[Throwable])(a: c.Expr[A]): c.universe.Tree = {
    import c.universe._
    val underlying = q"${c.prefix}.underlying"
    val canLogEv = q"${c.prefix}.canLogEv"
    q"""if ($underlying.isInfoEnabled) {
          $underlying.info($canLogEv.logMessage($message, $a), $cause)
          $canLogEv.afterLog($a)
        }"""
  }

  def infoMessageArgs[A](
      c: LoggerContext[A]
  )(message: c.Expr[String], args: c.Expr[Any]*)(a: c.Expr[A]): c.universe.Tree = {
    import c.universe._
    val underlying = q"${c.prefix}.underlying"
    val canLogEv = q"${c.prefix}.canLogEv"
    if (args.length == 2) {
      q"""if ($underlying.isInfoEnabled) {
            $underlying.info($canLogEv.logMessage($message, $a), List(${args(0)}, ${args(1)}): _*)
            $canLogEv.afterLog($a)
          }"""
    } else {
      q"""if ($underlying.isInfoEnabled) {
            $underlying.info($canLogEv.logMessage($message, $a), ..$args)
            $canLogEv.afterLog($a)
          }"""
    }
  }

  def infoMessageMarker[A](
      c: LoggerContext[A]
  )(marker: c.Expr[Marker], message: c.Expr[String])(a: c.Expr[A]): c.universe.Tree = {
    import c.universe._
    val underlying = q"${c.prefix}.underlying"
    val canLogEv = q"${c.prefix}.canLogEv"
    q"""if ($underlying.isInfoEnabled($marker)) {
          $underlying.info($marker, $canLogEv.logMessage($message, $a))
          $canLogEv.afterLog($a)
        }"""
  }

  def infoMessageCauseMarker[A](
      c: LoggerContext[A]
  )(marker: c.Expr[Marker], message: c.Expr[String], cause: c.Expr[Throwable])(a: c.Expr[A]): c.universe.Tree = {
    import c.universe._
    val underlying = q"${c.prefix}.underlying"
    val canLogEv = q"${c.prefix}.canLogEv"
    q"""if ($underlying.isInfoEnabled($marker)) {
          $underlying.info($marker, $canLogEv.logMessage($message, $a), $cause)
          $canLogEv.afterLog($a)
        }"""
  }

  def infoMessageArgsMarker[A](
      c: LoggerContext[A]
  )(marker: c.Expr[Marker], message: c.Expr[String], args: c.Expr[Any]*)(a: c.Expr[A]): c.universe.Tree = {
    import c.universe._
    val underlying = q"${c.prefix}.underlying"
    val canLogEv = q"${c.prefix}.canLogEv"
    if (args.length == 2) {
      q"""if ($underlying.isInfoEnabled($marker)) {
          $underlying.info($marker, $canLogEv.logMessage($message, $a), List(${args(0)}, ${args(1)}): _*)
          $canLogEv.afterLog($a)
        }"""
    } else {
      q"""if ($underlying.isInfoEnabled($marker)) {
          $underlying.info($marker, $canLogEv.logMessage($message, $a), ..$args)
          $canLogEv.afterLog($a)
        }"""
    }
  }

  def infoCode[A](c: LoggerContext[A])(body: c.Expr[Unit])(a: c.Expr[A]): c.universe.Tree = {
    import c.universe._
    val underlying = q"${c.prefix}.underlying"
    val canLogEv = q"${c.prefix}.canLogEv"
    q"""if ($underlying.isInfoEnabled) {
          $underlying.info($canLogEv.logMessage($body))
          $canLogEv.afterLog($a)
        }"""
  }

  // Debug

  def debugMessage[A](c: LoggerContext[A])(message: c.Expr[String])(a: c.Expr[A]): c.universe.Tree = {
    import c.universe._
    val underlying = q"${c.prefix}.underlying"
    val canLogEv = q"${c.prefix}.canLogEv"
    q"""if ($underlying.isDebugEnabled) {
          $underlying.debug($canLogEv.logMessage($message, $a))
          $canLogEv.afterLog($a)
        }"""
  }

  def debugMessageCause[A](
      c: LoggerContext[A]
  )(message: c.Expr[String], cause: c.Expr[Throwable])(a: c.Expr[A]): c.universe.Tree = {
    import c.universe._
    val underlying = q"${c.prefix}.underlying"
    val canLogEv = q"${c.prefix}.canLogEv"
    q"""if ($underlying.isDebugEnabled) {
          $underlying.debug($canLogEv.logMessage($message, $a), $cause)
          $canLogEv.afterLog($a)
        }"""
  }

  def debugMessageArgs[A](
      c: LoggerContext[A]
  )(message: c.Expr[String], args: c.Expr[Any]*)(a: c.Expr[A]): c.universe.Tree = {
    import c.universe._
    val underlying = q"${c.prefix}.underlying"
    val canLogEv = q"${c.prefix}.canLogEv"
    if (args.length == 2) {
      q"""if ($underlying.isDebugEnabled) {
            $underlying.debug($canLogEv.logMessage($message, $a), List(${args(0)}, ${args(1)}): _*)
            $canLogEv.afterLog($a)
          }"""
    } else {
      q"""if ($underlying.isDebugEnabled) {
            $underlying.debug($canLogEv.logMessage($message, $a), ..$args)
            $canLogEv.afterLog($a)
          }"""
    }
  }

  def debugMessageMarker[A](
      c: LoggerContext[A]
  )(marker: c.Expr[Marker], message: c.Expr[String])(a: c.Expr[A]): c.universe.Tree = {
    import c.universe._
    val underlying = q"${c.prefix}.underlying"
    val canLogEv = q"${c.prefix}.canLogEv"
    q"""if ($underlying.isDebugEnabled($marker)) {
          $underlying.debug($marker, $canLogEv.logMessage($message, $a))
          $canLogEv.afterLog($a)
        }"""
  }

  def debugMessageCauseMarker[A](
      c: LoggerContext[A]
  )(marker: c.Expr[Marker], message: c.Expr[String], cause: c.Expr[Throwable])(a: c.Expr[A]): c.universe.Tree = {
    import c.universe._
    val underlying = q"${c.prefix}.underlying"
    val canLogEv = q"${c.prefix}.canLogEv"
    q"""if ($underlying.isDebugEnabled($marker)) {
          $underlying.debug($marker, $canLogEv.logMessage($message, $a), $cause)
          $canLogEv.afterLog($a)
        }"""
  }

  def debugMessageArgsMarker[A](
      c: LoggerContext[A]
  )(marker: c.Expr[Marker], message: c.Expr[String], args: c.Expr[Any]*)(a: c.Expr[A]): c.universe.Tree = {
    import c.universe._
    val underlying = q"${c.prefix}.underlying"
    val canLogEv = q"${c.prefix}.canLogEv"
    if (args.length == 2) {
      q"""if ($underlying.isDebugEnabled($marker)) {
          $underlying.debug($marker, $canLogEv.logMessage($message, $a), List(${args(0)}, ${args(1)}): _*)
          $canLogEv.afterLog($a)
        }"""
    } else {
      q"""if ($underlying.isDebugEnabled($marker)) {
          $underlying.debug($marker, $canLogEv.logMessage($message, $a), ..$args)
          $canLogEv.afterLog($a)
        }"""
    }
  }

  def debugCode[A](c: LoggerContext[A])(body: c.Expr[Unit])(a: c.Expr[A]): c.universe.Tree = {
    import c.universe._
    val underlying = q"${c.prefix}.underlying"
    val canLogEv = q"${c.prefix}.canLogEv"
    q"""if ($underlying.isDebugEnabled) {
          $underlying.debug($canLogEv.logMessage($body))
          $canLogEv.afterLog($a)
        }"""
  }

  // Trace

  def traceMessage[A](c: LoggerContext[A])(message: c.Expr[String])(a: c.Expr[A]): c.universe.Tree = {
    import c.universe._
    val underlying = q"${c.prefix}.underlying"
    val canLogEv = q"${c.prefix}.canLogEv"
    q"""if ($underlying.isTraceEnabled) {
          $underlying.trace($canLogEv.logMessage($message, $a))
          $canLogEv.afterLog($a)
        }"""
  }

  def traceMessageCause[A](
      c: LoggerContext[A]
  )(message: c.Expr[String], cause: c.Expr[Throwable])(a: c.Expr[A]): c.universe.Tree = {
    import c.universe._
    val underlying = q"${c.prefix}.underlying"
    val canLogEv = q"${c.prefix}.canLogEv"
    q"""if ($underlying.isTraceEnabled) {
          $underlying.trace($canLogEv.logMessage($message, $a), $cause)
          $canLogEv.afterLog($a)
        }"""
  }

  def traceMessageArgs[A](
      c: LoggerContext[A]
  )(message: c.Expr[String], args: c.Expr[Any]*)(a: c.Expr[A]): c.universe.Tree = {
    import c.universe._
    val underlying = q"${c.prefix}.underlying"
    val canLogEv = q"${c.prefix}.canLogEv"
    if (args.length == 2) {
      q"""if ($underlying.isTraceEnabled) {
            $underlying.trace($canLogEv.logMessage($message, $a), List(${args(0)}, ${args(1)}): _*)
            $canLogEv.afterLog($a)
          }"""
    } else {
      q"""if ($underlying.isTraceEnabled) {
            $underlying.trace($canLogEv.logMessage($message, $a), ..$args)
            $canLogEv.afterLog($a)
          }"""
    }
  }

  def traceMessageMarker[A](
      c: LoggerContext[A]
  )(marker: c.Expr[Marker], message: c.Expr[String])(a: c.Expr[A]): c.universe.Tree = {
    import c.universe._
    val underlying = q"${c.prefix}.underlying"
    val canLogEv = q"${c.prefix}.canLogEv"
    q"""if ($underlying.isTraceEnabled($marker)) {
          $underlying.trace($marker, $canLogEv.logMessage($message, $a))
          $canLogEv.afterLog($a)
        }"""
  }

  def traceMessageCauseMarker[A](
      c: LoggerContext[A]
  )(marker: c.Expr[Marker], message: c.Expr[String], cause: c.Expr[Throwable])(a: c.Expr[A]): c.universe.Tree = {
    import c.universe._
    val underlying = q"${c.prefix}.underlying"
    val canLogEv = q"${c.prefix}.canLogEv"
    q"""if ($underlying.isTraceEnabled($marker)) {
          $underlying.trace($marker, $canLogEv.logMessage($message, $a), $cause)
          $canLogEv.afterLog($a)
        }"""
  }

  def traceMessageArgsMarker[A](
      c: LoggerContext[A]
  )(marker: c.Expr[Marker], message: c.Expr[String], args: c.Expr[Any]*)(a: c.Expr[A]): c.universe.Tree = {
    import c.universe._
    val underlying = q"${c.prefix}.underlying"
    val canLogEv = q"${c.prefix}.canLogEv"
    if (args.length == 2) {
      q"""if ($underlying.isTraceEnabled($marker)) {
          $underlying.trace($marker, $canLogEv.logMessage($message, $a), List(${args(0)}, ${args(1)}): _*)
          $canLogEv.afterLog($a)
        }"""
    } else {
      q"""if ($underlying.isTraceEnabled($marker)) {
          $underlying.trace($marker, $canLogEv.logMessage($message, $a), ..$args)
          $canLogEv.afterLog($a)
        }"""
    }
  }

  def traceCode[A](c: LoggerContext[A])(body: c.Expr[Unit])(a: c.Expr[A]): c.universe.Tree = {
    import c.universe._
    val underlying = q"${c.prefix}.underlying"
    val canLogEv = q"${c.prefix}.canLogEv"
    q"""if ($underlying.isTraceEnabled) {
          $underlying.trace($canLogEv.logMessage($body))
          $canLogEv.afterLog($a)
        }"""
  }
}
