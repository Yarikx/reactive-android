package org.yarikx.reactiveandroid.model

import reactive.EventSource

trait EventStreamHolder[T] {
  lazy val eventStream = new EventSource[T]
  protected def propagate(event: T) = eventStream.fire(event)
}
