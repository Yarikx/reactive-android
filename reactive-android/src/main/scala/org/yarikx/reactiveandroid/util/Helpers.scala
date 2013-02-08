package org.yarikx.reactiveandroid.util

import android.content.Context
import android.util.AttributeSet
import android.widget.{Button, EditText, SeekBar, TextView}
import org.yarikx.reactiveandroid.view.{ReactiveButton, ReactiveEditText, ReactiveSeekbar, ReactiveTextView}

class ReactiveButtonHelper(context: Context, attrs: AttributeSet) extends Button(context, attrs) with ReactiveButton

class ReactiveSeekbarHelper(context: Context, attrs: AttributeSet) extends SeekBar(context, attrs) with ReactiveSeekbar

class ReactiveTextViewHelper(context: Context, attrs: AttributeSet) extends TextView(context, attrs) with ReactiveTextView

class ReactiveEditTextHelper(context: Context, attrs: AttributeSet) extends EditText(context, attrs) with ReactiveEditText
