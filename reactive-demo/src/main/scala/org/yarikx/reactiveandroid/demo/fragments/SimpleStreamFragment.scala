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

class SimpleStreamFragment extends DemoFragment with Observing {

  override def onCreateView(inflater: LayoutInflater, vg: ViewGroup, bundle: Bundle) = {
    val view = inflater.inflate(R.layout.simple_stream_fragment, vg, false)
    
    val button = view.findView(TR.simple_button)
    val editText = view.findView(TR.simple_field)
    
    //Create event stream of 'String's
    val eventStream = new EventSource[String]

    //Fire EditText state for every click to created stream
    button.setOnClickListener(new View.OnClickListener{
      def onClick(view: View)={
        eventStream.fire(editText.getText().toString())
      }
    })
    
    //Handling every event
    eventStream.foreach(s => log(s))

    view
  }

}
