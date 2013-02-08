package org.yarikx.reactiveandroid.view

import android.text.{ Editable, TextWatcher }
import android.widget.EditText
import org.yarikx.reactiveandroid.model.{ Reactor, VarHolder }

trait ReactiveEditText extends EditText with Reactor[String] with VarHolder[String] {
  def react(t: String) = this.setText(t)
  def current = this.getText().toString

  addTextChangedListener(new TextWatcher() {
    def afterTextChanged(s: Editable) = {}
    def beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) = {}
    def onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) = update(s.toString)
  });

}
