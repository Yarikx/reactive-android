package org.yarikx.reactiveandroid

import android.content.Context
import android.widget.Button
import android.util.AttributeSet

trait ReactiveButton extends Button
  with ReactiveClicker {

}

object ReactiveButton {
  def apply(context: Context) =
    new Button(context) with ReactiveButton

  def apply(context: Context, attrs: AttributeSet, defStyle: Int) =
    new Button(context, attrs, defStyle) with ReactiveButton

  def apply(context: Context, attrs: AttributeSet) =
    new Button(context, attrs) with ReactiveButton

}