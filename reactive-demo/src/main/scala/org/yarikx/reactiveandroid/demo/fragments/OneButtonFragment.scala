package com.yarikx.reactiveandroid.demo

import android.app.{ Activity, Fragment }
import android.os.Bundle
import android.view.{ LayoutInflater, ViewGroup }
import com.yarikx.reactiveandroid.demo.TypedResource._
import com.yarikx.reactiveandroid.demo.utils.Utils._
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

