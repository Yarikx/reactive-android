package org.yarikx.reactiveandroid.view

import android.text.{ Editable, TextWatcher }
import android.widget.{ CheckBox, CheckedTextView, EditText, ProgressBar, SeekBar, TextView, ToggleButton }
import org.yarikx.reactiveandroid.model.{ ReactiveClicker, Reactor, VarHolder }

trait ReactiveTextView extends TextView with Reactor[String] {
  def react(s: String) = this.setText(s)
}

trait ReactiveEditText extends EditText with Reactor[String] with VarHolder[String] {
  def react(t: String) = this.setText(t)
  def current = this.getText().toString

  addTextChangedListener(new TextWatcher() {
    def afterTextChanged(s: Editable) = {}
    def beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) = {}
    def onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) = update(s.toString)
  });

}

trait ReactiveToggleButton extends ToggleButton with Reactor[Boolean] with VarHolder[Boolean] with ReactiveClicker {
  def react(state: Boolean) = this.setChecked(state)
  def current = this.isChecked

  this.clicks.foreach(_ => update(this.isChecked()))
}

trait ReactiveCheckBox extends CheckBox with Reactor[Boolean] with VarHolder[Boolean] with ReactiveClicker {
  def react(state: Boolean) = this.setChecked(state)
  def current = this.isChecked

  this.clicks.foreach(_ => update(this.isChecked()))
}

trait ReactiveCheckedTextView extends CheckedTextView with Reactor[Boolean] with VarHolder[Boolean] with ReactiveClicker {
  def react(state: Boolean) = this.setChecked(state)
  def current = this.isChecked

  this.clicks.foreach(_ => update(this.isChecked()))
}

trait ReactiveProgressBar extends ProgressBar with Reactor[Int] {
  def react(state: Int) = this.setProgress(state)
  def current = this.getProgress
}

trait ReactiveSeekbar extends SeekBar with Reactor[Int] with VarHolder[Int] {

  def react(v: Int) = this.setProgress(v)
  def current = this.getProgress()

  setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener {
    def onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
      update(progress)
    }
    def onStartTrackingTouch(seekBar: SeekBar) {

    }
    def onStopTrackingTouch(seekBar: SeekBar) {

    }
  })

}

