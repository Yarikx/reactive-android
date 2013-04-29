package org.yarikx.reactiveandroid.demo.fragments;

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.{ LayoutInflater, ViewGroup }
import org.yarikx.reactiveandroid.demo.{ R, TR }
import org.yarikx.reactiveandroid.demo.TypedResource._
import org.yarikx.reactiveandroid.util.AndroidUtils._
import reactive.Observing

class ComposeSignalFragment extends DemoFragment with Observing {

  override def onCreateView(inflater: LayoutInflater, vg: ViewGroup, bundle: Bundle) = {
    val view = inflater.inflate(R.layout.sliders_signals, vg, false)
    val slider1 = view.findView(TR.seekbar1)
    val slider2 = view.findView(TR.seekbar2)
    val text = view.findView(TR.resultText)

    val generated = for {
      x <- slider1.values
      y <- slider2.values
    } yield { s"$x * $y = ${x*y}" }

    text.reactOn(generated)

    generated.change.throttle(500).inUi.foreach(log)

    view
  }

}
