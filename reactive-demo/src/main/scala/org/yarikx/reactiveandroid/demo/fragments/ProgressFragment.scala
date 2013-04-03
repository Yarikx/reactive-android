package org.yarikx.reactiveandroid.demo.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.{ LayoutInflater, ViewGroup }
import org.yarikx.reactiveandroid.demo.{ R, TR }
import org.yarikx.reactiveandroid.demo.TypedResource._
import reactive.Observing

class ProgressFragment extends DemoFragment with Observing {

  override def onCreateView(inflater: LayoutInflater, vg: ViewGroup, bundle: Bundle) = {
    val view = inflater.inflate(R.layout.progress_fragment, vg, false)
    val progress = view.findView(TR.progressBar)
    val seekbar1 = view.findView(TR.seekbar1)

    val max = progress.getMax

    progress.reactOn(
      seekbar1.values
        .map(max - _))

    view
  }

}
