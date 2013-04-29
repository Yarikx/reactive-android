package org.yarikx.reactiveandroid.demo

import android.app.Application
import org.yarikx.reactiveandroid.demo.fragments.Progress2Fragment
import org.yarikx.reactiveandroid.demo.fragments.BroadcastFragment
import org.yarikx.reactiveandroid.demo.fragments.{ FoldFragment, OneButtonFragment, ProgressFragment, TwoButtonsFragment, TakeWhileFragment }
import org.yarikx.reactiveandroid.demo.fragments.{ UntilFragment }
import scala.collection.immutable.ListMap
import org.yarikx.reactiveandroid.demo.fragments.SimpleStreamFragment
import org.yarikx.reactiveandroid.demo.fragments.ComposeSignalFragment

class Scalapp extends Application {

  val demosMap = ListMap(
    "Simple stream" -> new SimpleStreamFragment,
    "One button" -> new OneButtonFragment,
    "Take While" -> new TakeWhileFragment,
    "Until" -> new UntilFragment,
    "Two buttons, one handler" -> new TwoButtonsFragment,
    "Fold" -> new FoldFragment,
    "Progress" -> new ProgressFragment,
    "Progress2" -> new Progress2Fragment,
    "Signal compose" -> new ComposeSignalFragment,
    "Broadcast" -> new BroadcastFragment)

}

