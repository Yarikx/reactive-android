package org.yarikx.reactiveandroid.demo.fragments

import android.support.v4.app.Fragment
import org.yarikx.reactiveandroid.demo.DemoActivity
import org.yarikx.reactiveandroid.demo.utils.LogActivity

abstract class DemoFragment extends Fragment {
  implicit def activity = getActivity()

  def log(s: String) = getActivity match {
    case la: LogActivity => la.log(s)
    case _ =>
  }
}

