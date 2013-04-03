package org.yarikx.reactiveandroid.util

import android.content.Context
import android.util.AttributeSet
import android.widget.{Button, CheckBox, EditText, ProgressBar, SeekBar, TextView}
import org.yarikx.reactiveandroid.model.ReactiveClicker
import org.yarikx.reactiveandroid.view.{ReactiveCheckBox, ReactiveEditText, ReactiveProgressBar, ReactiveSeekbar, ReactiveTextView}

class ReactiveButtonHelper(context: Context, attrs: AttributeSet) extends Button(context, attrs) with ReactiveClicker

class RSeekbar(context: Context, attrs: AttributeSet) extends SeekBar(context, attrs) with ReactiveSeekbar

class ReactiveTextViewHelper(context: Context, attrs: AttributeSet) extends TextView(context, attrs) with ReactiveTextView

class ReactiveEditTextHelper(context: Context, attrs: AttributeSet) extends EditText(context, attrs) with ReactiveEditText

class RProgressBar(context: Context, attrs: AttributeSet) extends ProgressBar(context, attrs) with ReactiveProgressBar

class RCheckBox(context: Context, attrs: AttributeSet) extends CheckBox(context, attrs) with ReactiveCheckBox
