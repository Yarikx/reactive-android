package org.yarikx.reactiveandroid.demo.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.{ LayoutInflater, ViewGroup }
import org.yarikx.reactiveandroid.demo.{ R, TR }
import org.yarikx.reactiveandroid.demo.TypedResource._
import org.yarikx.reactiveandroid.demo.utils.Utils._
import reactive.Observing
import reactive.EventSource
import android.widget.Button
import reactive.EventSource
import android.view.View
import android.view.View.OnClickListener

class FilteredFragment extends DemoFragment with Observing {

  override def onCreateView(inflater: LayoutInflater, vg: ViewGroup, bundle: Bundle) = {
    val view = inflater.inflate(R.layout.filtered, vg, false)
   
    val editText = view.findView(TR.simple_field)
    val button = view.findView(TR.rbutton)

    //creating new EventStream[String]
    val strings = button.clicks.map(_ => editText.getText.toString)

    //create filterd eventStream
    val filtered = strings.filter(s => s.length > 0)
    
    //Handling every event
    filtered.foreach(s => log(s))

    view
  }

}
