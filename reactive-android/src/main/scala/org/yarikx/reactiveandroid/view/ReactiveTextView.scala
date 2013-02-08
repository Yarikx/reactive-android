package org.yarikx.reactiveandroid.view

import android.widget.TextView
import org.yarikx.reactiveandroid.model.{ Reactor, VarHolder }
import reactive.Observing

trait ReactiveTextView extends TextView with Reactor[String] {
  def react(s: String) = this.setText(s)
}

