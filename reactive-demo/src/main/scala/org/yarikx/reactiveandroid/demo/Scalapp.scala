package org.yarikx.reactiveandroid.demo

import android.app.Application
import android.util.Log
import org.yarikx.reactiveandroid.demo.fragments.{FoldFragment, OneButtonFragment, TwoButtonsFragment}
import scala.collection.immutable.ListMap

class Scalapp extends Application {

  val demosMap = ListMap(
    "Simple button" -> new OneButtonFragment,
    "Two buttons, one handler" -> new TwoButtonsFragment,
    "Fold" -> new FoldFragment)

}










