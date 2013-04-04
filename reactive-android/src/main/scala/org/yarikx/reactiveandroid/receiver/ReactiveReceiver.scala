package org.yarikx.reactiveandroid.receiver

import android.content.{ BroadcastReceiver, Context, Intent }
import reactive.EventSource

class ReactiveReceiver extends BroadcastReceiver {
  lazy val intents = new EventSource[(Context, Intent)]

  def onReceive(context: Context, intent: Intent) {
    intents.fire((context, intent))
  }
}
