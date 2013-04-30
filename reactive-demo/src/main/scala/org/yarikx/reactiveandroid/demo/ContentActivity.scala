package org.yarikx.reactiveandroid.demo

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.View
import android.widget.ScrollView
import android.widget.TextView
import org.yarikx.reactiveandroid.demo.utils.LogActivity
import org.yarikx.reactiveandroid.demo.utils.ActivityUtils

class ContentActivity extends FragmentActivity with LogActivity with TypedActivity {

 

  override def onCreate(bundle: Bundle) {
    super.onCreate(bundle)
    setContentView(R.layout.content_layout)
    val fragmentName = getIntent.getExtras.getString("fragment")

    val app = getApplication().asInstanceOf[Scalapp]

    app.demosMap.get(fragmentName).foreach { fragment =>
      getSupportFragmentManager()
        .beginTransaction
        .add(R.id.frame_layout, fragment, "demo_fragment")
        .commit()
    }

  }

  
}
