package org.yarikx.reactiveandroid.util

import reactive.EventSource
import reactive.EventStream
import android.os.Handler
import android.content.Context
import android.app.Activity
import android.os.AsyncTask

object AndroidUtils {
  implicit def eventStream2android[T](es: EventStream[T])(implicit activity: Activity) =
    new AndroidEventStream(es)
}

class AndroidEventStream[T](es: EventStream[T]) {
  type Runner = ( =>Unit) => Unit

  private[this] def runInUi(f: =>Unit)(implicit activity: Activity) = activity.runOnUiThread(
    new Runnable{
      def run() = f
    })
  

  def inUi(implicit activity: Activity) = es.withRunner{f =>
    runInUi(f)
  }

  def async = es.withRunner(f => new Thread{
    override def run() = f
  }.start)

}
