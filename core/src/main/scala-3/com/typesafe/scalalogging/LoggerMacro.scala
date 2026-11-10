package com.typesafe.scalalogging

import org.slf4j.Marker
import scala.quoted.*
import org.slf4j.{Logger as Underlying}

private[scalalogging] object LoggerMacro {

  // Error

  def errorMessage(underlying: Expr[Underlying], message: Expr[String])(using Quotes): Expr[Unit] = {
    val (messageFormat, args) = deconstructInterpolatedMessage(message)
    errorMessageArgs(underlying, messageFormat, Expr.ofSeq(args))
  }

  def errorMessageCause(underlying: Expr[Underlying], message: Expr[String], cause: Expr[Throwable])(using Quotes) =
    '{ if ($underlying.isErrorEnabled) $underlying.error($message, $cause) }

  def errorMessageArgs(underlying: Expr[Underlying], message: Expr[String], args: Expr[Seq[Any]])(using Quotes) = {
    val anyRefArgs = formatArgs(args)
    if (anyRefArgs.isEmpty)
      '{ if ($underlying.isErrorEnabled) $underlying.error($message) }
    else if (anyRefArgs.lengthIs == 1)
      '{ if ($underlying.isErrorEnabled) $underlying.error($message, ${ anyRefArgs.head }) }
    else
      '{ if ($underlying.isErrorEnabled) $underlying.error($message, ${ Expr.ofSeq(anyRefArgs) }*) }
  }

  def errorMessageMarker(underlying: Expr[Underlying], marker: Expr[Marker], message: Expr[String])(using Quotes) = {
    val (messageFormat, args) = deconstructInterpolatedMessage(message)
    errorMessageArgsMarker(underlying, marker, messageFormat, Expr.ofSeq(args))
  }

  def errorMessageCauseMarker(
      underlying: Expr[Underlying],
      marker: Expr[Marker],
      message: Expr[String],
      cause: Expr[Throwable]
  )(using Quotes) =
    '{ if ($underlying.isErrorEnabled($marker)) $underlying.error($marker, $message, $cause) }

  def errorMessageArgsMarker(
      underlying: Expr[Underlying],
      marker: Expr[Marker],
      message: Expr[String],
      args: Expr[Seq[Any]]
  )(using Quotes) = {
    val anyRefArgs = formatArgs(args)
    if (anyRefArgs.isEmpty)
      '{ if ($underlying.isErrorEnabled($marker)) $underlying.error($marker, $message) }
    else if (anyRefArgs.lengthIs == 1)
      '{ if ($underlying.isErrorEnabled($marker)) $underlying.error($marker, $message, ${ anyRefArgs.head }) }
    else
      '{ if ($underlying.isErrorEnabled($marker)) $underlying.error($marker, $message, ${ Expr.ofSeq(anyRefArgs) }*) }
  }

  def errorCode(underlying: Expr[Underlying], body: Expr[Unit])(using Quotes) =
    '{ if ($underlying.isErrorEnabled) $body }

  // Warn

  def warnMessage(underlying: Expr[Underlying], message: Expr[String])(using Quotes): Expr[Unit] = {
    val (messageFormat, args) = deconstructInterpolatedMessage(message)
    warnMessageArgs(underlying, messageFormat, Expr.ofSeq(args))
  }

  def warnMessageCause(underlying: Expr[Underlying], message: Expr[String], cause: Expr[Throwable])(using Quotes) =
    '{ if ($underlying.isWarnEnabled) $underlying.warn($message, $cause) }

  def warnMessageArgs(underlying: Expr[Underlying], message: Expr[String], args: Expr[Seq[Any]])(using Quotes) = {
    val anyRefArgs = formatArgs(args)
    if (anyRefArgs.isEmpty)
      '{ if ($underlying.isWarnEnabled) $underlying.warn($message) }
    else if (anyRefArgs.lengthIs == 1)
      '{ if ($underlying.isWarnEnabled) $underlying.warn($message, ${ anyRefArgs.head }) }
    else
      '{ if ($underlying.isWarnEnabled) $underlying.warn($message, ${ Expr.ofSeq(anyRefArgs) }*) }
  }

  def warnMessageMarker(underlying: Expr[Underlying], marker: Expr[Marker], message: Expr[String])(using Quotes) = {
    val (messageFormat, args) = deconstructInterpolatedMessage(message)
    warnMessageArgsMarker(underlying, marker, messageFormat, Expr.ofSeq(args))
  }

  def warnMessageCauseMarker(
      underlying: Expr[Underlying],
      marker: Expr[Marker],
      message: Expr[String],
      cause: Expr[Throwable]
  )(using Quotes) =
    '{ if ($underlying.isWarnEnabled($marker)) $underlying.warn($marker, $message, $cause) }

  def warnMessageArgsMarker(
      underlying: Expr[Underlying],
      marker: Expr[Marker],
      message: Expr[String],
      args: Expr[Seq[Any]]
  )(using Quotes) = {
    val anyRefArgs = formatArgs(args)
    if (anyRefArgs.isEmpty)
      '{ if ($underlying.isWarnEnabled($marker)) $underlying.warn($marker, $message) }
    else if (anyRefArgs.lengthIs == 1)
      '{ if ($underlying.isWarnEnabled($marker)) $underlying.warn($marker, $message, ${ anyRefArgs.head }) }
    else
      '{ if ($underlying.isWarnEnabled($marker)) $underlying.warn($marker, $message, ${ Expr.ofSeq(anyRefArgs) }*) }
  }

  def warnCode(underlying: Expr[Underlying], body: Expr[Unit])(using Quotes) =
    '{ if ($underlying.isWarnEnabled) $body }

  // Info

  def infoMessage(underlying: Expr[Underlying], message: Expr[String])(using Quotes): Expr[Unit] = {
    val (messageFormat, args) = deconstructInterpolatedMessage(message)
    infoMessageArgs(underlying, messageFormat, Expr.ofSeq(args))
  }

  def infoMessageCause(underlying: Expr[Underlying], message: Expr[String], cause: Expr[Throwable])(using Quotes) =
    '{ if ($underlying.isInfoEnabled) $underlying.info($message, $cause) }

  def infoMessageArgs(underlying: Expr[Underlying], message: Expr[String], args: Expr[Seq[Any]])(using Quotes) = {
    val anyRefArgs = formatArgs(args)
    if (anyRefArgs.isEmpty)
      '{ if ($underlying.isInfoEnabled) $underlying.info($message) }
    else if (anyRefArgs.lengthIs == 1)
      '{ if ($underlying.isInfoEnabled) $underlying.info($message, ${ anyRefArgs.head }) }
    else
      '{ if ($underlying.isInfoEnabled) $underlying.info($message, ${ Expr.ofSeq(anyRefArgs) }*) }
  }

  def infoMessageMarker(underlying: Expr[Underlying], marker: Expr[Marker], message: Expr[String])(using Quotes) = {
    val (messageFormat, args) = deconstructInterpolatedMessage(message)
    infoMessageArgsMarker(underlying, marker, messageFormat, Expr.ofSeq(args))
  }

  def infoMessageCauseMarker(
      underlying: Expr[Underlying],
      marker: Expr[Marker],
      message: Expr[String],
      cause: Expr[Throwable]
  )(using Quotes) =
    '{ if ($underlying.isInfoEnabled($marker)) $underlying.info($marker, $message, $cause) }

  def infoMessageArgsMarker(
      underlying: Expr[Underlying],
      marker: Expr[Marker],
      message: Expr[String],
      args: Expr[Seq[Any]]
  )(using Quotes) = {
    val anyRefArgs = formatArgs(args)
    if (anyRefArgs.isEmpty)
      '{ if ($underlying.isInfoEnabled($marker)) $underlying.info($marker, $message) }
    else if (anyRefArgs.lengthIs == 1)
      '{ if ($underlying.isInfoEnabled($marker)) $underlying.info($marker, $message, ${ anyRefArgs.head }) }
    else
      '{ if ($underlying.isInfoEnabled($marker)) $underlying.info($marker, $message, ${ Expr.ofSeq(anyRefArgs) }*) }
  }

  def infoCode(underlying: Expr[Underlying], body: Expr[Unit])(using Quotes) =
    '{ if ($underlying.isInfoEnabled) $body }

  // Debug

  def debugMessage(underlying: Expr[Underlying], message: Expr[String])(using Quotes): Expr[Unit] = {
    val (messageFormat, args) = deconstructInterpolatedMessage(message)
    debugMessageArgs(underlying, messageFormat, Expr.ofSeq(args))
  }

  def debugMessageCause(underlying: Expr[Underlying], message: Expr[String], cause: Expr[Throwable])(using Quotes) =
    '{ if ($underlying.isDebugEnabled) $underlying.debug($message, $cause) }

  def debugMessageArgs(underlying: Expr[Underlying], message: Expr[String], args: Expr[Seq[Any]])(using Quotes) = {
    val anyRefArgs = formatArgs(args)
    if (anyRefArgs.isEmpty)
      '{ if ($underlying.isDebugEnabled) $underlying.debug($message) }
    else if (anyRefArgs.lengthIs == 1)
      '{ if ($underlying.isDebugEnabled) $underlying.debug($message, ${ anyRefArgs.head }) }
    else
      '{ if ($underlying.isDebugEnabled) $underlying.debug($message, ${ Expr.ofSeq(anyRefArgs) }*) }
  }

  def debugMessageMarker(underlying: Expr[Underlying], marker: Expr[Marker], message: Expr[String])(using Quotes) = {
    val (messageFormat, args) = deconstructInterpolatedMessage(message)
    debugMessageArgsMarker(underlying, marker, messageFormat, Expr.ofSeq(args))
  }

  def debugMessageCauseMarker(
      underlying: Expr[Underlying],
      marker: Expr[Marker],
      message: Expr[String],
      cause: Expr[Throwable]
  )(using Quotes) =
    '{ if ($underlying.isDebugEnabled($marker)) $underlying.debug($marker, $message, $cause) }

  def debugMessageArgsMarker(
      underlying: Expr[Underlying],
      marker: Expr[Marker],
      message: Expr[String],
      args: Expr[Seq[Any]]
  )(using Quotes) = {
    val anyRefArgs = formatArgs(args)
    if (anyRefArgs.isEmpty)
      '{ if ($underlying.isDebugEnabled($marker)) $underlying.debug($marker, $message) }
    else if (anyRefArgs.lengthIs == 1)
      '{ if ($underlying.isDebugEnabled($marker)) $underlying.debug($marker, $message, ${ anyRefArgs.head }) }
    else
      '{ if ($underlying.isDebugEnabled($marker)) $underlying.debug($marker, $message, ${ Expr.ofSeq(anyRefArgs) }*) }
  }

  def debugCode(underlying: Expr[Underlying], body: Expr[Unit])(using Quotes) =
    '{ if ($underlying.isDebugEnabled) $body }

  // Trace

  def traceMessage(underlying: Expr[Underlying], message: Expr[String])(using Quotes): Expr[Unit] = {
    val (messageFormat, args) = deconstructInterpolatedMessage(message)
    traceMessageArgs(underlying, messageFormat, Expr.ofSeq(args))
  }

  def traceMessageCause(underlying: Expr[Underlying], message: Expr[String], cause: Expr[Throwable])(using Quotes) =
    '{ if ($underlying.isTraceEnabled) $underlying.trace($message, $cause) }

  def traceMessageArgs(underlying: Expr[Underlying], message: Expr[String], args: Expr[Seq[Any]])(using Quotes) = {
    val anyRefArgs = formatArgs(args)
    if (anyRefArgs.isEmpty)
      '{ if ($underlying.isTraceEnabled) $underlying.trace($message) }
    else if (anyRefArgs.lengthIs == 1)
      '{ if ($underlying.isTraceEnabled) $underlying.trace($message, ${ anyRefArgs.head }) }
    else
      '{ if ($underlying.isTraceEnabled) $underlying.trace($message, ${ Expr.ofSeq(anyRefArgs) }*) }
  }

  def traceMessageMarker(underlying: Expr[Underlying], marker: Expr[Marker], message: Expr[String])(using Quotes) = {
    val (messageFormat, args) = deconstructInterpolatedMessage(message)
    traceMessageArgsMarker(underlying, marker, messageFormat, Expr.ofSeq(args))
  }

  def traceMessageCauseMarker(
      underlying: Expr[Underlying],
      marker: Expr[Marker],
      message: Expr[String],
      cause: Expr[Throwable]
  )(using Quotes) =
    '{ if ($underlying.isTraceEnabled($marker)) $underlying.trace($marker, $message, $cause) }

  def traceMessageArgsMarker(
      underlying: Expr[Underlying],
      marker: Expr[Marker],
      message: Expr[String],
      args: Expr[Seq[Any]]
  )(using Quotes) = {
    val anyRefArgs = formatArgs(args)
    if (anyRefArgs.isEmpty)
      '{ if ($underlying.isTraceEnabled($marker)) $underlying.trace($marker, $message) }
    else if (anyRefArgs.lengthIs == 1)
      '{ if ($underlying.isTraceEnabled($marker)) $underlying.trace($marker, $message, ${ anyRefArgs.head }) }
    else
      '{ if ($underlying.isTraceEnabled($marker)) $underlying.trace($marker, $message, ${ Expr.ofSeq(anyRefArgs) }*) }
  }

  def traceCode(underlying: Expr[Underlying], body: Expr[Unit])(using Quotes) =
    '{ if ($underlying.isTraceEnabled) $body }

  /** Checks whether `message` is an interpolated string and transforms it into SLF4J string interpolation. */
  private def deconstructInterpolatedMessage(message: Expr[String])(using Quotes): (Expr[String], Seq[Expr[Any]]) = {
    import quotes.reflect.*
    import util.*

    message.asTerm match {
      case Inlined(_, _, Apply(Select(Apply(Select(Select(_, "StringContext"), _), messageNode), _), argumentsNode)) =>
        val messageTextPartsOpt: Option[List[String]] =
          messageNode.collectFirst { case Typed(Repeated(ls, _), _) =>
            ls.collect { case Literal(StringConstant(s)) => s }
          }
        val argsOpt: Option[List[Term]] =
          argumentsNode.collectFirst { case Typed(Repeated(ls, _), _) =>
            ls
          }

        (messageTextPartsOpt, argsOpt) match {
          case (Some(messageTextParts), Some(args)) =>
            val format = messageTextParts.iterator
              // Emulate standard interpolator escaping
              .map(StringContext.processEscapes)
              // Escape literal slf4j format anchors if the resulting call will require a format string
              .map(str => if (args.nonEmpty) str.replace("{}", "\\{}") else str)
              .mkString("{}")

            val formatArgs = args.map(_.asExpr)

            (Expr(format), formatArgs)
          case _ =>
            (message, Seq.empty)
        }
      case _ => (message, Seq.empty)
    }
  }
  def formatArgs(args: Expr[Seq[Any]])(using q: Quotes): Seq[Expr[AnyRef]] = {
    import quotes.reflect.*
    import util.*

    args.asTerm match {
      case p @ Inlined(_, _, Typed(Repeated(v, _), _)) =>
        v.map {
          case t if t.tpe <:< TypeRepr.of[AnyRef] => t.asExprOf[AnyRef]
          case t                                  => '{ ${ t.asExpr }.asInstanceOf[AnyRef] }
        }
      case Repeated(v, _) =>
        v.map {
          case t if t.tpe <:< TypeRepr.of[AnyRef] => t.asExprOf[AnyRef]
          case t                                  => '{ ${ t.asExpr }.asInstanceOf[AnyRef] }
        }
      case _ => Seq.empty
    }
  }
}
