package org.yarikx.reactiveandroid.demo

import android.os.Bundle
import android.support.v4.app.{ FragmentActivity, ListFragment }
import android.widget.ArrayAdapter
import org.yarikx.reactiveandroid.demo.utils.ActivityUtils
import scala.collection.JavaConversions._

class DemoActivity extends FragmentActivity with ActivityUtils with TypedActivity {

  val demos = List(
    "First demo" -> new OneButtonFragment)

  val demosMap = demos.toMap
  lazy val fm = this.getSupportFragmentManager()
  lazy val frame = findView(TR.frame_layout)
  lazy val smallScreen = frame != null

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
      val tag = demos(pos)._1
      val fragment = demosMap(tag)

      val transaction = fm.beginTransaction
      if (smallScreen) {
        transaction.replace(R.id.frame_layout, fragment, tag)
        transaction.addToBackStack(null)
      } else {
        transaction.replace(R.id.demo_content, fragment, tag)
      }
      transaction.commit()
    }

    val adapter = new ArrayAdapter[String](this, android.R.layout.simple_list_item_1, demos.map(_._1))
    list.setListAdapter(adapter)
  }
}

