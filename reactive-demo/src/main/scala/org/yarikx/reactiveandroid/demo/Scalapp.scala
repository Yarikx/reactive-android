package org.yarikx.reactiveandroid.demo

import android.app.Application
import org.yarikx.reactiveandroid.demo.fragments._
import scala.collection.immutable.ListMap

class Scalapp extends Application {

  val demosMap = ListMap(
    "Simple stream" -> new SimpleStreamFragment,
    "Map" -> new MappedFragment,
    "Filter" -> new FilteredFragment,
    "One button" -> new OneButtonFragment,
    "Take While" -> new TakeWhileFragment,
    "Until" -> new UntilFragment,
    "Two buttons, one handler" -> new TwoButtonsFragment,
    "Fold" -> new FoldFragment,
    "EditText Signal" -> new EditTextSignalFragment,
    "Progress" -> new ProgressFragment,
    "Progress2" -> new Progress2Fragment,
    "Signal compose" -> new ComposeSignalFragment,
    "Broadcast" -> new BroadcastFragment,
    "Async Worker" -> new AsyncFragment,
    "Search" -> new SearchFragment,
    "Line draw" -> new LineDrawFragment,
    "Double click" -> new DoubleClickFragment)

}

