package org.yarikx.reactiveandroid.demo.fragments

import android.content.Context
import android.graphics.{ Canvas, Paint, Path }
import android.os.Bundle
import android.view.{ LayoutInflater, MotionEvent, View, ViewGroup }
import org.yarikx.reactiveandroid.demo.{ R, TR }
import org.yarikx.reactiveandroid.demo.TypedResource._
import org.yarikx.reactiveandroid.demo.utils.Utils._
import org.yarikx.reactiveandroid.util.AndroidUtils._
import reactive.{ EventSource, Observing }

class LineDrawFragment extends DemoFragment with Observing {

  override def onCreateView(inflater: LayoutInflater, vg: ViewGroup, bundle: Bundle) = {
    val view = inflater.inflate(R.layout.line_draw_fragment, vg, false)
    val layout = view.findView(TR.frame_layout)

    //create our view to get touch events and render lines
    val canvas = new CanvasView(getActivity)
    layout.addView(canvas)

    //create eventStreams for different touch events
    val downs = canvas.touches.filter(_.getAction() == MotionEvent.ACTION_DOWN)
    val moves = canvas.touches.filter(_.getAction() == MotionEvent.ACTION_MOVE)
    val ups = canvas.touches.filter(_.getAction() == MotionEvent.ACTION_UP)

    //helper function to create path from x,y
    def createPath(x: Float, y: Float) = {
      val p = new Path
      p.moveTo(x, y)
      p
    }

    //create event stream of created paths
    val paths = for {
      downEvent <- downs;
      startPath = createPath(downEvent.getX, downEvent.getY);
      pathStream = moves.until(ups)
        .foldLeft(startPath)((p, evt) => { p.lineTo(evt.getX, evt.getY); p });
      pathToDraw <- pathStream
    } yield pathToDraw

    //just render 
    paths.foreach { x =>
      canvas.current = Option(x)
      canvas.invalidate()
    }

    //return our view
    view
  }

}

class CanvasView(context: Context) extends View(context) {
  var current: Option[Path] = None
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
    current.foreach(c.drawPath(_, defPaint))
  }
}
