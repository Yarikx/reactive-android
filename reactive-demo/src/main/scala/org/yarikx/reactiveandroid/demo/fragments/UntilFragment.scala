package org.yarikx.reactiveandroid.demo.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.{ LayoutInflater, ViewGroup }
import org.yarikx.reactiveandroid.demo.{ R, TR }
import org.yarikx.reactiveandroid.demo.TypedResource._
import org.yarikx.reactiveandroid.demo.utils.Utils._
import reactive.Observing
import reactive.EventSource
import android.view.View

class UntilFragment extends DemoFragment with Observing {

  override def onCreateView(inflater: LayoutInflater, vg: ViewGroup, bundle: Bundle) = {
    val view = inflater.inflate(R.layout.until_fragment, vg, false)
    
    val fireButton = view.findView(TR.rbutton)
    val editText = view.findView(TR.simple_field)

    val stopButton = view.findView(TR.stop_rbutton)
    
    //Create event stream of 'String's
    val strings = fireButton.clicks.map(_ => editText.getText().toString())

    //
    val cantStop = strings.until(stopButton.clicks)

    logStream(cantStop)

    view
  }

}
