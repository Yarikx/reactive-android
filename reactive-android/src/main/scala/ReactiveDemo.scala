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

    val text = findView(TR.textview)
    text.setText("hello, scala world is hard, not for everyone")

    val b = findView(TR.rbutton)
    val pane = findView(TR.pane)

    b.clicks.foldLeft(0)((x, _) => x + 1).foreach { x =>
      makeToast(""+ x);
    }

    val canvas = new MyCanvas(this)
    pane.addView(canvas, new LinearLayout.LayoutParams(-1, -1 ,1))
    
    val downs = canvas.touches.filter(_.getAction()==MotionEvent.ACTION_DOWN)
    val moves = canvas.touches.filter(_.getAction()==MotionEvent.ACTION_MOVE)
    val ups = canvas.touches.filter(_.getAction()==MotionEvent.ACTION_UP)
    
    def createPath(x: Float, y: Float)={
      val p = new Path
      p.moveTo(x, y)
      p
    }
    
    val paths = for{
      down <- downs;
      path = createPath(down.getX, down.getY);
      v = moves.until(ups).foldLeft(path)((p, evt) => {p.lineTo(evt.getX, evt.getY); p}).hold(path);
      up <- ups.once;
      currentPath = v.now;
      _ = currentPath.moveTo(up.getX, up.getY)
    }  yield currentPath
    
    
    paths.foreach{x=> 
      canvas.paths += x
      canvas.invalidate()
    }

    //    downs.map(_ => System.currentTimeMillis())

        val doubleClicks = for{
          d1 <- downs.copy
          t1 = System.currentTimeMillis()
          d2 <- downs.once.copy
          t2 = System.currentTimeMillis()
          if t2-t1 <500;
          u <- ups.once
        } yield {
          u
        }
        
        doubleClicks.foreach(x => makeToast(""+x))

    //    val doubleClicks = downs.flatMap{d1 => 
    //      val t1 = System.currentTimeMillis()
    //      downs.once.map{d2=> 
    //      	val t2 = System.currentTimeMillis()
    //      	(d2, t2)
    //      }.filter{
    //        case (d2, t2) => t2 - t1 < 500
    //        case _ => false
    //      }
    //    }.map(_._1)

//    val doubleClicks = downs.map(_ => System.currentTimeMillis)
//      .foldLeft((0L, 0L)) {
//        case ((_, lastT), newT) => (lastT, newT)
//      }.collect { case (lastT, newT) if newT - lastT < 500 => () }

//    doubleClicks.foldLeft(0)((x, _) => x+1).foreach(x => makeToast("doubleClick "+x))


    val v1 = Var(2)
    val v2 = Var(3)
    val v3 = Var(12)
    
    val bar1 = findView(TR.seekbar1)
    val bar2 = findView(TR.seekbar2)
    
    for{
      x1 <- bar1.value;
      x2 <- bar2.value
    }{
      text.setText("%d * %d = %d".format(x1, x2, x1*x2))
    }

    val yes = for(
      x <- v1;
      y <- v2
    ) yield { x + y }

  }
  
  def makeToast(s: String)=
    Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
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
