package org.yarikx.reactiveandroid.view

import android.content.Context
import android.widget.Button
import android.util.AttributeSet
import org.yarikx.reactiveandroid.model.ReactiveClicker

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
