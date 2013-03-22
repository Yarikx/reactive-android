package org.yarikx.reactiveandroid.demo.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.{ LayoutInflater, ViewGroup }
import org.yarikx.reactiveandroid.demo.{ R, TR }
import org.yarikx.reactiveandroid.demo.TypedResource._
import org.yarikx.reactiveandroid.demo.utils.Utils._
import reactive.Observing

class FoldFragment extends Fragment with Observing {

  override def onCreateView(inflater: LayoutInflater, vg: ViewGroup, bundle: Bundle) = {
    val view = inflater.inflate(R.layout.fold_fragment, vg, false)
    val button = view.findView(TR.button)

    button.clicks
      .foldLeft(0)((x, _) => x+1)
      .foreach(x =>
      toast(s"$x times clicked", getActivity))

    view
  }

}

