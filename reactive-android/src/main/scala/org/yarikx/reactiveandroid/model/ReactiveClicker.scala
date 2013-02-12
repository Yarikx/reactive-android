package org.yarikx.reactiveandroid.model

import android.view.View
import android.view.View.OnClickListener
import reactive.EventSource

trait ReactiveClicker extends View with EventStreamHolder[Unit] {

  val clicks = new EventSource[Unit] {}

  lazy val mListener = new OnClickListener {
    def onClick(v: View) = clicks.fire()
  }

  this.setOnClickListener(mListener);

}
