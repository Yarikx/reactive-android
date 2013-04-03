package org.yarikx.reactiveandroid.demo

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.{ FragmentActivity, ListFragment }
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ScrollView
import android.widget.TextView
import org.yarikx.reactiveandroid.demo.fragments.{ FoldFragment, OneButtonFragment, TwoButtonsFragment }
import org.yarikx.reactiveandroid.demo.utils.ActivityUtils
import org.yarikx.reactiveandroid.demo.utils.LogActivity
import scala.collection.JavaConversions._
import scala.collection.immutable.ListMap

class DemoActivity extends FragmentActivity with ActivityUtils with TypedActivity with LogActivity{

  lazy val app = getApplication().asInstanceOf[Scalapp]

  lazy val demosMap = app.demosMap

  lazy val tags = demosMap.map { case (tag, _) => tag }.toSeq

  lazy val fm = this.getSupportFragmentManager()
  lazy val frame = findView(TR.frame_layout)
  lazy val smallScreen = frame != null
  var currentPosition = -1

  var list: ListFragment = null

  override def onCreate(bundle: Bundle) {
    super.onCreate(bundle)
    setContentView(R.layout.demo_activity)

    if (smallScreen) {
      list = new ListFragment
      fm.beginTransaction.replace(R.id.frame_layout, list, "Listfrgm").commit()
    } else {
      list = fm.findFragmentById(R.id.list_frag).asInstanceOf[ListFragment]
    }

  }

  override def onPostCreate(bundle: Bundle) {
    super.onPostCreate(bundle)

    list.getListView().setOnItemClickListener { pos: Int =>
      val tag = tags(pos)
      if (smallScreen) {
        val intent = new Intent(this, classOf[ContentActivity])
        intent.putExtra("fragment", tag)
        startActivity(intent)
      } else {
        if (currentPosition != pos) {
          currentPosition = pos

          val fragment = demosMap(tag)

          fm.beginTransaction
            .replace(R.id.demo_content, fragment)
            .commit()
        }

      }

    }

    val adapter = new ArrayAdapter[String](this, android.R.layout.simple_list_item_1, tags)
    list.setListAdapter(adapter)
  }

}

