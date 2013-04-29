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

class TakeWhileFragment extends DemoFragment with Observing {

  override def onCreateView(inflater: LayoutInflater, vg: ViewGroup, bundle: Bundle) = {
    val view = inflater.inflate(R.layout.take_while_fragment, vg, false)
    
    val fireButton = view.findView(TR.rbutton)
    val editText = view.findView(TR.simple_field)
    
    //Create event stream of 'String's
    val strings = fireButton.clicks.map(_ => editText.getText().toString())
    //Take strings while string not propogate 'stop' 
    val cantStop = strings.takeWhile(str => str != "stop")

    //and finnaly log it
    logStream(cantStop)

    view
  }

}
