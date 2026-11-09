package com.typesafe.scalalogging

trait TaggedType {
  type @@[+T, U] = T & tag.Tagged[U]
}
