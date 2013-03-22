package org.yarikx.reactiveandroid.demo.fragments

import android.support.v4.app.Fragment
import org.yarikx.reactiveandroid.demo.DemoActivity

abstract class DemoFragment extends Fragment {

  lazy val daOpt = getActivity match {
    case da: DemoActivity => Some(da)
    case _ => None
  }
  def log(s: String) = daOpt.foreach(_.log(s))
}

