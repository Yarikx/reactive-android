package org.yarikx.reactiveandroid.demo.fragments

import android.content.{ Context, Intent, IntentFilter }
import android.os.{ BatteryManager, Bundle }
import android.support.v4.app.Fragment
import android.view.{ LayoutInflater, ViewGroup }
import org.yarikx.reactiveandroid.demo.{ R, TR }
import org.yarikx.reactiveandroid.demo.TypedResource._
import org.yarikx.reactiveandroid.demo.utils.Utils._
import org.yarikx.reactiveandroid.receiver.ReactiveReceiver
import org.yarikx.reactiveandroid.util.AndroidUtils._
import reactive.{ EventSource, Observing }

class BroadcastFragment extends DemoFragment with Observing {

  val receiver = new ReactiveReceiver()
  val action = "android.intent.action.BATTERY_CHANGED"
  val filter = new IntentFilter(action)

  override def onCreate(bundle: Bundle) {
    super.onCreate(bundle)

    receiver.eventStream
      .map {
        case (_, intent) => calculateBatteryLevel(intent)
      }
      .nonblocking
      .inUi
      .foreach(s => log(s))
  }

  override def onCreateView(inflater: LayoutInflater, vg: ViewGroup, bundle: Bundle) = {
    val view = inflater.inflate(R.layout.broadcast_fragment, vg, false)
    val button = view.findView(TR.button)

    view
  }

  override def onResume() {
    super.onResume()
    this.getActivity.registerReceiver(receiver, filter)
  }

  override def onPause() {
    super.onPause()
    this.getActivity.unregisterReceiver(receiver)
  }

  private def calculateBatteryLevel(intent: Intent) = {
    val state = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
    val isCharging = state == BatteryManager.BATTERY_STATUS_CHARGING ||
      state == BatteryManager.BATTERY_STATUS_FULL
    val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
    val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)

    val stateString = if (isCharging) "charging" else "discharging"
    val percent = 100 * level / scale

    s"battery is $stateString, level = $percent"
  }

}

