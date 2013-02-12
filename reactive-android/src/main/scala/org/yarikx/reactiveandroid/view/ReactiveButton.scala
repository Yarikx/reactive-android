package org.yarikx.reactiveandroid.view

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import org.yarikx.reactiveandroid.model.ReactiveClicker

object ReactiveButton {
  def apply(context: Context) =
    new Button(context) with ReactiveButton

  def apply(context: Context, attrs: AttributeSet, defStyle: Int) =
    new Button(context, attrs, defStyle) with ReactiveButton

  def apply(context: Context, attrs: AttributeSet) =
    new Button(context, attrs) with ReactiveButton

}
