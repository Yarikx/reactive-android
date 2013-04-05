package org.yarikx.reactiveandroid.receiver

import android.content.{ BroadcastReceiver, Context, Intent }
import org.yarikx.reactiveandroid.model.EventStreamHolder
import reactive.EventSource

class ReactiveReceiver extends BroadcastReceiver with EventStreamHolder[(Context, Intent)]{
  def onReceive(context: Context, intent: Intent) {
    propagate((context, intent))
  }
}
