package org.yarikx.reactiveandroid

import android.content.Context
import android.widget.Button
import android.util.AttributeSet

class ReactiveButtonHelper(context: Context, attrs: AttributeSet) extends Button(context, attrs) with ReactiveButton
