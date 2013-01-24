package org.yarikx.reactiveandroid

import android.content.Context
import android.widget.Button
import android.util.AttributeSet

trait ReactiveButton extends Button
  with ReactiveClicker {

}

object ReactiveButton {
  def apply(context: Context) =
    ret(new Button(context) with ReactiveButton)

  def apply(context: Context, attrs: AttributeSet, defStyle: Int) =
    ret(new Button(context, attrs, defStyle) with ReactiveButton)

  def apply(context: Context, attrs: AttributeSet) =
    ret(new Button(context, attrs) with ReactiveButton)

  private def ret[A <: ReactiveView](b: A) = { b.install(); b }
}