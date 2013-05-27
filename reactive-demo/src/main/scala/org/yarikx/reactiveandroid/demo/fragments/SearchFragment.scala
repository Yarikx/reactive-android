package org.yarikx.reactiveandroid.demo.fragments

import android.os.Bundle
import android.util.Log
import android.view.{ LayoutInflater, ViewGroup }
import org.yarikx.reactiveandroid.demo.{ R, TR }
import org.yarikx.reactiveandroid.demo.TypedResource._
import org.yarikx.reactiveandroid.demo.utils.Utils._
import org.yarikx.reactiveandroid.util.AndroidUtils._
import reactive.Observing

class SearchFragment extends DemoFragment with Observing {

  override def onCreateView(inflater: LayoutInflater, vg: ViewGroup, bundle: Bundle) = {
    val view = inflater.inflate(R.layout.search_fragment, vg, false)
    val searchField = view.findView(TR.search_field)
    val searchResultText = view.findView(TR.search_result)

    val keyword = searchField.values
    val results = keyword.change
      .zipWithStaleness
      .async
      .map {
        case (word, isOld) =>
          //make some heavy work
          Thread.sleep(1000)
          Log.d("async debug", word)
          //check if it is still fresh
          if (isOld()) None
          else Option(s"result for $word")
      }
      .collect {
        case Some(res) => res
      }
      .inUi

    searchResultText reactOn results

    view
  }

}
