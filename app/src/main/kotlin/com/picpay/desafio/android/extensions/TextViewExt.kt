package com.picpay.desafio.android.extensions

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet

fun TextView.postChangeOrHideText(
    text: String?,
    delayHide: Long = context.shortDelayAnimTime
) {
    if (delayHide > 0) postOnAnimationDelayed(changeOrHideText(this, text), delayHide)
    else changeOrHideText(this, text).run()
}

private fun changeOrHideText(imageView: TextView, text: String?): Runnable = Runnable {
    imageView.visibility = View.INVISIBLE
    imageView.visibility = if (text.isNullOrBlank()) {
        ConstraintSet.GONE
    } else {
        imageView.text = text
        View.VISIBLE
    }
}