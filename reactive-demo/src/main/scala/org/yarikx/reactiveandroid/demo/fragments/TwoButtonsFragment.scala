package org.yarikx.reactiveandroid.demo.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.{LayoutInflater, ViewGroup}
import org.yarikx.reactiveandroid.demo.{R, TR}
import org.yarikx.reactiveandroid.demo.TypedResource._
import org.yarikx.reactiveandroid.demo.utils.Utils._
import reactive.Observing

class TwoButtonsFragment extends Fragment with Observing {

  override def onCreateView(inflater: LayoutInflater, vg: ViewGroup, bundle: Bundle) = {
    val view = inflater.inflate(R.layout.two_buttons_fragment, vg, false)
    val button1 = view.findView(TR.button1)
    val button2 = view.findView(TR.button2)

    (button1.clicks | button2.clicks).foreach(_ =>
      toast("Clicked", getActivity))

    view
  }

}

