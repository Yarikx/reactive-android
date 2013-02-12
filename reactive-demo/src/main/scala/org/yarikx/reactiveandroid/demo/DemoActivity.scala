package com.yarikx.reactiveandroid.demo

import android.app.Activity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.yarikx.reactiveandroid.demo.utils.ActivityUtils
import scala.collection.JavaConversions._

class DemoActivity extends Activity with ActivityUtils with TypedActivity {

  val demos = List(
    "First demo" -> new OneButtonFragment)

  val demosMap = demos.toMap
  lazy val fm = this.getFragmentManager()

  override def onCreate(bundle: Bundle) {
    super.onCreate(bundle)
    setContentView(R.layout.demo_activity)
    val list = findView(TR.list)
    val adapter = new ArrayAdapter[String](this, android.R.layout.simple_list_item_1, demos.map(_._1))
    list.setAdapter(adapter)

    list.setOnItemClickListener { pos: Int =>
      val transaction = fm.beginTransaction
      val tag = demos(pos)._1
      val fragment = demosMap(tag)
      transaction.replace(R.id.content, fragment, tag)
      transaction.commit()
    }

  }
}

