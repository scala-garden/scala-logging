package com.typesafe.scalalogging

import org.slf4j.{LoggerFactory, Logger => Underlying}
import scala.reflect.ClassTag

/** Companion for [[Logger]], providing a factory for [[Logger]]s.
  */
object Logger {

  /** Create a [[Logger]] wrapping the given underlying `org.slf4j.Logger`.
    */
  def apply(underlying: Underlying): Logger =
    new Logger(underlying)

  /** Create a [[LoggerTakingImplicit]] wrapping the given underlying `org.slf4j.Logger`.
    */
  def takingImplicit[A](underlying: Underlying)(implicit ev: CanLog[A]): LoggerTakingImplicit[A] =
    new LoggerTakingImplicit[A](underlying)

  /** Create a [[Logger]] for the given name. Example:
    * {{{
    *   val logger = Logger("application")
    * }}}
    */
  def apply(name: String): Logger =
    new Logger(LoggerFactory.getLogger(name))

  /** Create a [[LoggerTakingImplicit]] for the given name. Example:
    * {{{
    *   val logger = Logger.takingImplicit[CorrelationId]("application")
    * }}}
    */
  def takingImplicit[A](name: String)(implicit ev: CanLog[A]): LoggerTakingImplicit[A] =
    new LoggerTakingImplicit[A](LoggerFactory.getLogger(name))

  /** Create a [[Logger]] wrapping the created underlying `org.slf4j.Logger`.
    */
  def apply(clazz: Class[_]): Logger =
    new Logger(LoggerFactory.getLogger(clazz.getName))

  /** Create a [[LoggerTakingImplicit]] wrapping the created underlying `org.slf4j.Logger`.
    */
  def takingImplicit[A](clazz: Class[_])(implicit ev: CanLog[A]): LoggerTakingImplicit[A] =
    new LoggerTakingImplicit[A](LoggerFactory.getLogger(clazz.getName))

  /** Create a [[Logger]] for the runtime class wrapped by the implicit class tag parameter. Example:
    * {{{
    *   val logger = Logger[MyClass]
    * }}}
    */
  def apply[T](implicit ct: ClassTag[T]): Logger =
    new Logger(LoggerFactory.getLogger(ct.runtimeClass.getName.stripSuffix("$")))

  /** Create a [[LoggerTakingImplicit]] for the runtime class wrapped by the implicit class tag parameter. Example:
    * {{{
    *   val logger = Logger.takingImplicit[MyClass, CorrelationId]
    * }}}
    */
  def takingImplicit[T, A](implicit ct: ClassTag[T], ev: CanLog[A]): LoggerTakingImplicit[A] =
    new LoggerTakingImplicit[A](LoggerFactory.getLogger(ct.runtimeClass.getName.stripSuffix("$")))
}

/** Implementation of a fast logger based on macros and an underlying `org.slf4j.Logger`.
  */
@SerialVersionUID(538248225L)
final class Logger private[scalalogging] (val underlying: Underlying) extends LoggerImpl with Serializable
