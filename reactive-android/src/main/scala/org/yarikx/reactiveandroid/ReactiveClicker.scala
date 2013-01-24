package org.yarikx.reactiveandroid

import android.view.View
import reactive.EventStream
import android.view.View.OnClickListener
import reactive.EventSource
import android.util.Log

trait ReactiveClicker extends View with ReactiveView {
  
  val clicks = new EventSource[Unit]{}
  
  private var listener: Option[OnClickListener] = None;
  
//  override def setOnClickListener(l: OnClickListener){
//    listener = Option(l)
//  }
  
  val mListener = new OnClickListener{
    
    def onClick(v: View){
      clicks.fire()
    }
    
  }
  
  def install(){
    Log.d("reactive debug", "installing")
    this.setOnClickListener(mListener);
  }

}