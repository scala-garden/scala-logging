package com.typesafe.scalalogging

trait TaggedType {
  type @@[+T, U] = T with tag.Tagged[U]
}
