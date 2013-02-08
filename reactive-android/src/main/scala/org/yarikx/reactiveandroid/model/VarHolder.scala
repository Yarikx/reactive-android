package org.yarikx.reactiveandroid.model

import reactive.Var

trait VarHolder[T] {
  lazy val values = new Var[T](current)

  protected def update(value: T) = values.update(value)
  protected def current: T
}
