package org.yarikx.reactiveandroid.demo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.{ LayoutInflater, ViewGroup }
import org.yarikx.reactiveandroid.demo.TypedResource._
import org.yarikx.reactiveandroid.demo.utils.Utils._
import reactive.Observing

class OneButtonFragment extends Fragment with Observing {

  override def onCreateView(inflater: LayoutInflater, vg: ViewGroup, bundle: Bundle) = {
    val view = inflater.inflate(R.layout.one_button_fragment, vg, false)
    val button = view.findView(TR.button)

    button.clicks.foreach(x =>
      toast("Clicked", getActivity))

    view
  }

}

