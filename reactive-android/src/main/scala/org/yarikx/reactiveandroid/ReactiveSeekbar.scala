package org.yarikx.reactiveandroid

import android.widget.SeekBar
import reactive.Var

trait ReactiveSeekbar extends SeekBar {

  lazy val value = Var[Int](this.getProgress())

  setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener {
    def onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
    	value.update(progress)
    }
    def onStartTrackingTouch(seekBar: SeekBar) {

    }
    def onStopTrackingTouch(seekBar: SeekBar) {

    }
  })

}