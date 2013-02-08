package org.yarikx.reactiveandroid.view

import android.widget.SeekBar
import org.yarikx.reactiveandroid.model.{Reactor, VarHolder}

trait ReactiveSeekbar extends SeekBar with Reactor[Int] with VarHolder[Int] {

  def react(v: Int) = this.setProgress(v)
  def current = this.getProgress()

  setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener {
    def onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
      update(progress)
    }
    def onStartTrackingTouch(seekBar: SeekBar) {

    }
    def onStopTrackingTouch(seekBar: SeekBar) {

    }
  })

}
