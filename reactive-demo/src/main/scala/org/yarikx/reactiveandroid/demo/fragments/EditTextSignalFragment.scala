package org.yarikx.reactiveandroid.demo.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.{ LayoutInflater, ViewGroup }
import org.yarikx.reactiveandroid.demo.{ R, TR }
import org.yarikx.reactiveandroid.demo.TypedResource._
import org.yarikx.reactiveandroid.demo.utils.Utils._
import reactive.Observing
import reactive.EventSource
import android.view.View

class EditTextSignalFragment extends DemoFragment with Observing {

  override def onCreateView(inflater: LayoutInflater, vg: ViewGroup, bundle: Bundle) = {
    val view = inflater.inflate(R.layout.edit_text_signal, vg, false)
    
    val editText = view.findView(TR.rfield)
    val rlabel = view.findView(TR.rtext)

    val reversed = editText.values.map(str => str.reverse)

    rlabel.reactOn(reversed)

    view
  }

}
