package org.yarikx.reactiveandroid.demo.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.{ LayoutInflater, ViewGroup }
import org.yarikx.reactiveandroid.demo.{ R, TR }
import org.yarikx.reactiveandroid.demo.TypedResource._
import org.yarikx.reactiveandroid.demo.utils.Utils._
import reactive.Observing
import org.yarikx.reactiveandroid.util.AndroidUtils._

class AsyncFragment extends DemoFragment with Observing {

  override def onCreateView(inflater: LayoutInflater, vg: ViewGroup, bundle: Bundle) = {
    val view = inflater.inflate(R.layout.async_fragment, vg, false)
    val buttonBad = view.findView(TR.button1)
    val buttonGood = view.findView(TR.button2)

    //making heavy work in the same thread
    buttonBad.clicks
    .foldLeft(0)((prev,_) => prev + 1)
    .map{x =>
      Thread.sleep(1000);
      x*x
    }
    .foreach(res => log("bad "+res))

    //making heavy work in other thread
    buttonGood.clicks
    .nonblocking
    .foldLeft(0)((prev,_) => prev + 1)
    .map{x =>
      Thread.sleep(1000);
      x*x
    }
    .inUi
    .foreach(res => log("good "+res))

    view
  }

}
