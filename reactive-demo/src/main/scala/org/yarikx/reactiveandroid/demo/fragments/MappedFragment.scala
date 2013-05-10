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

class MappedFragment extends DemoFragment with Observing {

  class ReactiveButton extends Button(this.getActivity){
    val clicks = new EventSource[Unit]()

    this.setOnClickListener(new OnClickListener {
      def onClick(v: View) = clicks.fire()
    });
  }

  override def onCreateView(inflater: LayoutInflater, vg: ViewGroup, bundle: Bundle) = {
    val view = inflater.inflate(R.layout.mapped, vg, false)
   
    val editText = view.findView(TR.simple_field)
    val layout = view.findView(TR.simpleLayout)

    val button = new ReactiveButton
    button.setText(R.string.fire)

    layout.addView(button)

    //creating new EventStream[String]
    val strings = button.clicks.map(_ => editText.getText.toString)

    //one more transformation
    val coolStrings = strings.map(str => s"!!!  $str  !!!")
    
    //Handling every event
    coolStrings.foreach(s => log(s))

    view
  }

}
