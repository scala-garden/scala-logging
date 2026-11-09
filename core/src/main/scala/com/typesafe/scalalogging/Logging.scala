package com.typesafe.scalalogging

import org.slf4j.LoggerFactory

/** Defines `logger` as a lazy value initialized with an underlying `org.slf4j.Logger` named according to the class into
  * which this trait is mixed.
  */
trait LazyLogging {

  @transient
  protected lazy val logger: Logger =
    Logger(LoggerFactory.getLogger(getClass.getName))
}

/** Defines `logger` as a value initialized with an underlying `org.slf4j.Logger` named according to the class into
  * which this trait is mixed.
  */
trait StrictLogging {

  protected val logger: Logger =
    Logger(LoggerFactory.getLogger(getClass.getName))
}

/** Defines an abstract `logger` that will be useful while writing some trait which needs access to any logger without
  * deciding on an specific implementation.
  */
trait AnyLogging {

  protected val logger: Logger
}
