package org.yarikx.reactiveandroid.model

import android.view.View
import android.view.View.OnClickListener
import reactive.EventSource

trait ReactiveClicker extends View with EventStreamHolder[Unit] {

  lazy val clicks = eventStream

  this.setOnClickListener(new OnClickListener {
    def onClick(v: View) = propagate()
  });

}
