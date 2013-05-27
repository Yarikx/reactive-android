package org.yarikx.reactiveandroid.demo

import android.app.Application
import org.yarikx.reactiveandroid.demo.fragments.Progress2Fragment
import org.yarikx.reactiveandroid.demo.fragments.BroadcastFragment
import org.yarikx.reactiveandroid.demo.fragments._
import scala.collection.immutable.ListMap
import org.yarikx.reactiveandroid.demo.fragments.SimpleStreamFragment
import org.yarikx.reactiveandroid.demo.fragments.ComposeSignalFragment
import org.yarikx.reactiveandroid.demo.fragments.FilteredFragment
import org.yarikx.reactiveandroid.demo.fragments.MappedFragment
import org.yarikx.reactiveandroid.demo.fragments.DoubleClickFragment

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
    "Double click" -> new DoubleClickFragment)

}

