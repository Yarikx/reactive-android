package org.yarikx.reactiveandroid.demo

import android.os.Bundle
import android.support.v4.app.{ FragmentActivity, ListFragment }
import android.widget.ArrayAdapter
import org.yarikx.reactiveandroid.demo.fragments.{ FoldFragment, OneButtonFragment, TwoButtonsFragment }
import org.yarikx.reactiveandroid.demo.utils.ActivityUtils
import scala.collection.JavaConversions._
import scala.collection.immutable.ListMap

class DemoActivity extends FragmentActivity with ActivityUtils with TypedActivity {

  val demosMap = ListMap(
    "Simple button" -> new OneButtonFragment,
    "Two buttons, one handler" -> new TwoButtonsFragment,
    "Fold" -> new FoldFragment)

  val tags = demosMap.map{case (tag, _) => tag}.toSeq

  lazy val fm = this.getSupportFragmentManager()
  lazy val frame = findView(TR.frame_layout)
  lazy val logView = findView(TR.logger)
  lazy val smallScreen = frame != null
  var currentPosition = -1

  var list: ListFragment = null

  override def onCreate(bundle: Bundle) {
    super.onCreate(bundle)
    setContentView(R.layout.demo_activity)

    if (smallScreen) {
      list = new ListFragment
      fm.beginTransaction.add(R.id.frame_layout, list, "Listfrgm").commit()
    } else {
      list = fm.findFragmentById(R.id.list_frag).asInstanceOf[ListFragment]
    }

  }

  override def onPostCreate(bundle: Bundle) {
    super.onPostCreate(bundle)

    list.getListView().setOnItemClickListener { pos: Int =>
      if (currentPosition != pos) {
        currentPosition = pos
        val tag = tags(pos)
        val fragment = demosMap(tag)

        val transaction = fm.beginTransaction
        if (smallScreen) {
          transaction.replace(R.id.frame_layout, fragment, tag)
          transaction.addToBackStack(null)
        } else {
          transaction.replace(R.id.demo_content, fragment)
        }
        transaction.commit()
      }
    }

    val adapter = new ArrayAdapter[String](this, android.R.layout.simple_list_item_1, tags)
    list.setListAdapter(adapter)
  }

  def log(s: String) = logView.setText(logView.getText()+s+"\n")
}

