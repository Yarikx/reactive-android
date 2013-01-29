package org.yarikx.reactiveandroid

import android.view.View
import reactive.EventStream
import android.view.View.OnClickListener
import reactive.EventSource
import android.util.Log

trait ReactiveClicker extends View {

  val clicks = new EventSource[Unit] {}

  private var listener: Option[OnClickListener] = None;

  val mListener = new OnClickListener {
    def onClick(v: View) = clicks.fire()
  }

  this.setOnClickListener(mListener);

}