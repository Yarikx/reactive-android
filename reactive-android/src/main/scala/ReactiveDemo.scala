package com.yarikx.reactiveandroid

import _root_.android.app.Activity
import _root_.android.os.Bundle
import reactive.EventSource
import android.util.Log
import reactive.Observing
import org.yarikx.reactiveandroid.ReactiveButton
import android.widget.Button
import android.widget.Toast
import android.view.View.OnTouchListener
import android.view.MotionEvent
import android.view.View
import android.content.Context
import android.view.ViewGroup
import android.widget.LinearLayout
import reactive.EventStream
import reactive.Signal
import reactive.Signal
import android.graphics.Path
import reactive.Signal
import reactive.Var
import scala.collection.mutable.ListBuffer
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Color

class ReactiveDemo extends Activity with TypedActivity with Observing {
  override def onCreate(bundle: Bundle) {
    super.onCreate(bundle)
    setContentView(R.layout.main)

    findView(TR.textview).setText("hello, scala world is hard, not for everyone")

    val b = ReactiveButton(this)
    b.setText("Push me")
    val pane = findView(TR.pane)

    b.clicks.foldLeft(0)((x, _) => x + 1).foreach { x =>
      Toast.makeText(this, "" + x, Toast.LENGTH_SHORT).show();
    }

    pane.addView(b)

    val mainStream = new EventSource[MotionEvent]()

    val my = new MyCanvas(this)
    pane.addView(my, new LinearLayout.LayoutParams(-1, -1 ,1))
    
    val down = my.touches.filter(_.getAction()==MotionEvent.ACTION_DOWN)
    val move = my.touches.filter(_.getAction()==MotionEvent.ACTION_MOVE)
    val up = my.touches.filter(_.getAction()==MotionEvent.ACTION_UP)
    
    def createPath(x: Float, y: Float)={
      val p = new Path
      p.moveTo(x, y)
      p
    }
    
    for{
      md <- down;
      p = createPath(md.getX, md.getY);
      v = move.takeUntil(up).foldLeft(p)((path, evt) => {val p = path; p.lineTo(evt.getX, evt.getY); p}).hold(p);
      _ = v.foreach({ x=> 
          my.current = Option(x) 
          my.invalidate()
        });
      um <- up.once;
      path = v.now;
      _ = path.moveTo(um.getX, um.getY)
    }  {
      my.paths += path
      my.invalidate()
    }

  }
}

class MyCanvas(context: Context) extends View(context) {
  var current: Option[Path] = None
  val touches = new EventSource[MotionEvent]
  val paths = ListBuffer[Path]()
  override def onTouchEvent(evt: MotionEvent) = {
    touches.fire(evt)
    true
  }
  
  val defPaint = new Paint();
  defPaint.setColor(Color.WHITE)
  defPaint.setStyle(Paint.Style.STROKE);
  defPaint.setAntiAlias(true)
  override def onDraw(c: Canvas){
    super.onDraw(c)
    
    paths.foreach(c.drawPath(_, defPaint))
    current.foreach(c.drawPath(_, defPaint))
    
  }
}
