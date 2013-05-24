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
import android.view.MotionEvent
import android.util.Log

class DoubleClickFragment extends DemoFragment with Observing {

  override def onCreateView(inflater: LayoutInflater, vg: ViewGroup, bundle: Bundle) = {
    val view = inflater.inflate(R.layout.double_click_fragment, vg, false)
    val area = view.findView(TR.touch_area)

    val touches = new EventSource[MotionEvent]

    //propogate events to stream
    area.setOnTouchListener(new View.OnTouchListener{
    	def onTouch(v: View, event: MotionEvent):Boolean = {
    		touches.fire(event)
    		true
    	}
   	});

   	touches.foreach(e => Log.d("touchs", s"$e"))

    val downs = touches.filter(event => event.getAction() == MotionEvent.ACTION_DOWN)

    val doubleClicks = for{
      first <- downs
      t1 = first.getDownTime
      second <- downs.once
      t2 = second.getDownTime
      third <- downs.once
      t3 = third.getDownTime
      if t2 - t1 > 500
      if t3 - t2 < 500
    } yield second

    logStream(doubleClicks.map(ev => "double"))

    view
  }

}
