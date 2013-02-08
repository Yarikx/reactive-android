package com.yarikx.reactiveandroid

import android.app.Activity
import android.content.Context
import android.graphics.{Canvas, Color, Paint, Path}
import android.os.Bundle
import android.view.{MotionEvent, View}
import android.widget.{LinearLayout, Toast}
import reactive.{EventSource, Observing}
import scala.collection.mutable.ListBuffer

class ReactiveDemo extends Activity with TypedActivity with Observing {
  override def onCreate(bundle: Bundle) {
    super.onCreate(bundle)
    setContentView(R.layout.main)

    val text = findView(TR.rtext)
    text.setText("hello, scala world is hard, not for everyone")

    val b = findView(TR.rbutton)
    val pane = findView(TR.pane)

    //fold demo
    b.clicks.foldLeft(0)((x, _) => x + 1).foreach { x =>
      makeToast("" + x);
    }

    //draw demo
    val canvas = new MyCanvas(this)
    pane.addView(canvas, new LinearLayout.LayoutParams(-1, -1, 1))

    val downs = canvas.touches.filter(_.getAction() == MotionEvent.ACTION_DOWN)
    val moves = canvas.touches.filter(_.getAction() == MotionEvent.ACTION_MOVE)
    val ups = canvas.touches.filter(_.getAction() == MotionEvent.ACTION_UP)

    def createPath(x: Float, y: Float) = {
      val p = new Path
      p.moveTo(x, y)
      p
    }

    val paths = for {
      down <- downs;
      path = createPath(down.getX, down.getY);
      v = moves.until(ups).foldLeft(path)((p, evt) => { p.lineTo(evt.getX, evt.getY); p }).hold(path);
      up <- ups.once;
      currentPath = v.now;
      _ = currentPath.moveTo(up.getX, up.getY)
    } yield currentPath

    paths.foreach { x =>
      canvas.paths += x
      canvas.invalidate()
    }

    //double click demo

    val doubleClicks = for {
      d1 <- downs
      t1 = System.currentTimeMillis()
      d2 <- downs.once
      t2 = System.currentTimeMillis()
      if t2 - t1 < 500;
      u <- ups.once
    } yield u

    doubleClicks.foreach(x => makeToast(x.toString))

    //Signal demo
    val bar1 = findView(TR.seekbar1)
    val bar2 = findView(TR.seekbar2)

    val strs = for {
      x1 <- bar1.values;
      x2 <- bar2.values
    } yield {
      "%d * %d = %d".format(x1, x2, x1 * x2)
    }

    text reactOn strs

    //setText field
    val edit = findView(TR.redit)
    text reactOn edit.values.map(x => x.reverse)

  }

  @inline def makeToast(s: String) =
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
  override def onDraw(c: Canvas) {
    super.onDraw(c)

    paths.foreach(c.drawPath(_, defPaint))
    current.foreach(c.drawPath(_, defPaint))

  }
}
