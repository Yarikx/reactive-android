package org.yarikx.reactiveandroid.demo.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.{ AdapterView, Toast }

trait ActivityUtils extends Activity {
  implicit val context: Context = this

  implicit def unit2runnable(f: => Any) = new Runnable() {
    def run = f
  }

  implicit def any2itemClick(f: Int => Any) = new AdapterView.OnItemClickListener() {
    def onItemClick(adapterView: AdapterView[_], view: View, position: Int, id: Long) {
      f(position)
    }
  }

}

object Utils {
  def toast(s: String, context: Context) =
    Toast.makeText(context, s, Toast.LENGTH_SHORT).show
}

