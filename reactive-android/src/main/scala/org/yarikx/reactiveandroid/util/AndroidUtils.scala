package org.yarikx.reactiveandroid.util

import reactive.EventSource
import reactive.EventStream
import android.os.Handler
import android.content.Context
import android.app.Activity

object AndroidUtils {
  implicit def eventStream2android[T](es: EventStream[T])(implicit activity: Activity) = new AndroidEventStream(es){
    def executeRunnable(r:Runnable)=activity.runOnUiThread(r)
  }
}

abstract class AndroidEventStream[T](es: EventStream[T]) {
  def executeRunnable(r: Runnable)

  def inUi = es.withRunner { f =>
    val runnable = new Runnable {
      def run() = f
    }
    executeRunnable(runnable)
  }

}
