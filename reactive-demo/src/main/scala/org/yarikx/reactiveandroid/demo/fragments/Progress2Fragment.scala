package org.yarikx.reactiveandroid.demo.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.{ LayoutInflater, ViewGroup }
import org.yarikx.reactiveandroid.demo.{ R, TR }
import org.yarikx.reactiveandroid.demo.TypedResource._
import reactive.Observing

class Progress2Fragment extends DemoFragment with Observing {

  override def onCreateView(inflater: LayoutInflater, vg: ViewGroup, bundle: Bundle) = {
    val view = inflater.inflate(R.layout.progress2_fragment, vg, false)
    val progress = view.findView(TR.progressBar)
    val seekbar1 = view.findView(TR.seekbar1)
    val seekbar2 = view.findView(TR.seekbar2)
    val checkbox = view.findView(TR.checkBox)

    val max = progress.getMax

    progress.reactOn(
      seekbar1.values
        .map(max - _))

    seekbar1.reactOn(
      seekbar2.values
        .map(max - _))

    val filtered = for {
      b <- checkbox.values
      v <- seekbar1.values.change.nonrecursive
      if b
    } yield {max - v}

    seekbar2.reactOn(filtered.hold(seekbar1.values.now))

    view
  }

}
