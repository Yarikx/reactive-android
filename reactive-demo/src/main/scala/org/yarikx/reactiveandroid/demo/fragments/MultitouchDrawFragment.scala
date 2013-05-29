package org.yarikx.reactiveandroid.demo.fragments

import android.content.Context
import android.graphics.{ Canvas, Paint, Path }
import android.os.Bundle
import android.support.v4.view.MotionEventCompat
import android.util.Log
import android.view.{ LayoutInflater, MotionEvent, View, ViewGroup }
import org.yarikx.reactiveandroid.demo.{ R, TR }
import org.yarikx.reactiveandroid.demo.TypedResource._
import org.yarikx.reactiveandroid.demo.utils.Utils._
import org.yarikx.reactiveandroid.util.AndroidUtils._
import reactive.{ EventSource, Observing }
import scala.collection.mutable.HashMap

class MultitouchDrawFragment extends DemoFragment with Observing {

  override def onCreateView(inflater: LayoutInflater, vg: ViewGroup, bundle: Bundle) = {
    val view = inflater.inflate(R.layout.line_draw_fragment, vg, false)
    val layout = view.findView(TR.frame_layout)

    //create our view to get touch events and render lines
    val canvas = new MultitouchCanvasView(getActivity)
    layout.addView(canvas)

    //helper function to create path from x,y
    def createPath(x: Float, y: Float) = {
      val p = new Path
      p.moveTo(x, y)
      p
    }

    val touches = canvas.touches

    for (id <- 0 to 2) {
      val myTouches =
        touches.filter { evt =>
          if (evt.findPointerIndex(id) == -1) false
          else if (evt.getActionMasked() == MotionEvent.ACTION_MOVE) true
          else evt.getPointerId(evt.getActionIndex()) == id
        }
          .map { evt =>
            val action = MotionEventCompat.getActionMasked(evt)
            val index = evt.findPointerIndex(id)
            val actionSymbol = action match {
              case MotionEvent.ACTION_DOWN => 'down
              case MotionEventCompat.ACTION_POINTER_DOWN => 'down
              case MotionEvent.ACTION_UP => 'up
              case MotionEventCompat.ACTION_POINTER_UP => 'up
              case MotionEvent.ACTION_MOVE => 'move
              case _ => 'cancel
            }
            val x = evt.getX(index)
            val y = evt.getY(index)
            (actionSymbol, x, y)
          }
      val downs = myTouches.collect { case ('down, x, y) => (x, y) }
      val moves = myTouches.collect { case ('move, x, y) => (x, y) }
      val ups = myTouches.collect { case ('up, x, y) => (x, y) }

      //create event stream of created paths
      val paths = for {
        (downX, downY) <- downs;
        startPath = createPath(downX, downY);
        pathStream = moves.until(ups)
          .foldLeft(startPath)((p, move) => { p.lineTo(move._1, move._2); p });
        pathToDraw <- pathStream
      } yield pathToDraw

      //just render 
      paths.foreach { x =>
        canvas.lines += id -> x
        canvas.invalidate()
      }

      myTouches.foreach { case (ac, x, y) => Log.d("react", s"id=$id $ac $x $y") }
    }

    //return our view
    view
  }

}

class MultitouchCanvasView(context: Context) extends View(context) {
  val lines = new HashMap[Int, Path]()
  val touches = new EventSource[MotionEvent]

  override def onTouchEvent(evt: MotionEvent) = {
    touches.fire(evt)
    true
  }

  val defPaint = new Paint();
  defPaint.setColor(context.getResources.getColor(R.color.line_draw_color))
  defPaint.setStyle(Paint.Style.STROKE)
  defPaint.setAntiAlias(true)

  override def onDraw(c: Canvas) {
    super.onDraw(c)
    lines.values.foreach(c.drawPath(_, defPaint))
  }
}
