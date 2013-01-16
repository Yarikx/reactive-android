package com.yarikx.reactiveandroid

import _root_.android.app.Activity
import _root_.android.os.Bundle
import reactive.EventSource
import android.util.Log
import reactive.Observing

class ReactiveDemo extends Activity with TypedActivity with Observing{
  override def onCreate(bundle: Bundle) {
    super.onCreate(bundle)
    setContentView(R.layout.main)

    findView(TR.textview).setText("hello, scala world is hard, not for everyone")
    
    val q = new EventSource[String];
    q.foreach(x => Log.d("reactive",x))
    q.fire("wow")
  }
}
