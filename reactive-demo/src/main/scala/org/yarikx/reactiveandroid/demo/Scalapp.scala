package org.yarikx.reactiveandroid.demo

import android.app.Application
import org.yarikx.reactiveandroid.demo.fragments.Progress2Fragment
import org.yarikx.reactiveandroid.demo.fragments.{ FoldFragment, OneButtonFragment, ProgressFragment, TwoButtonsFragment }
import scala.collection.immutable.ListMap

class Scalapp extends Application {

  val demosMap = ListMap(
    "Simple button" -> new OneButtonFragment,
    "Two buttons, one handler" -> new TwoButtonsFragment,
    "Fold" -> new FoldFragment,
    "Progress" -> new ProgressFragment,
    "Progress2" -> new Progress2Fragment)

}

