package org.yarikx.reactiveandroid.demo.utils

import android.support.v4.app.FragmentActivity
import android.view.View
import android.widget.ScrollView
import android.widget.TextView
import org.yarikx.reactiveandroid.demo.R

trait LogActivity extends FragmentActivity {
  lazy val logView = findViewById(R.id.logger).asInstanceOf[TextView]
  lazy val scrollView = findViewById(R.id.scroll).asInstanceOf[ScrollView]

  def log(s: String) {
    logView.setText(logView.getText() + s + "\n")
    scrollView.fullScroll(View.FOCUS_DOWN)
  }

}
