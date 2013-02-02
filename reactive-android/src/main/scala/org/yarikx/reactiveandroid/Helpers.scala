package org.yarikx.reactiveandroid

import android.content.Context
import android.widget.Button
import android.util.AttributeSet
import android.widget.SeekBar

class ReactiveButtonHelper(context: Context, attrs: AttributeSet) extends Button(context, attrs) with ReactiveButton
class ReactiveSeekbarHelper(context: Context, attrs: AttributeSet) extends SeekBar(context, attrs) with ReactiveSeekbar