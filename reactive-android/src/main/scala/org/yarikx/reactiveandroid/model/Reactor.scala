package org.yarikx.reactiveandroid.model

import reactive.{ EventStream, Observing, Signal }

trait Reactor[T] extends Observing {
  protected def react(value: T): Unit

  def reactOn(es: EventStream[T]) {
    es.foreach(x => react(x))
  }

  def reactOn(signal: Signal[T]) {
    reactOn(signal.change)
    react(signal.now)
  }
}
