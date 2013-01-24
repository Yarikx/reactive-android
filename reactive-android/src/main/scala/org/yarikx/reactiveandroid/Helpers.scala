package org.yarikx.reactiveandroid

import android.content.Context
import android.widget.Button
import android.util.AttributeSet

object Helpers {

  class ReactiveButtonHelper(context: Context, attrs: AttributeSet, defStyle: Int) extends Button(context, attrs, defStyle) with ReactiveButton

}