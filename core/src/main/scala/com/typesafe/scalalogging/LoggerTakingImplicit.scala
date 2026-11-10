package com.typesafe.scalalogging

import org.slf4j.{Logger => Underlying}

trait CanLog[A] {
  def logMessage(originalMsg: String, context: A): String
  def afterLog(context: A): Unit = {
    val _ = context
  }
  def getContext()(implicit context: A): A = context
}

@SerialVersionUID(957385465L)
class LoggerTakingImplicit[A] private[scalalogging] (val underlying: Underlying)(implicit val canLogEv: CanLog[A])
    extends LoggerTakingImplicitImpl[A]
    with Serializable
