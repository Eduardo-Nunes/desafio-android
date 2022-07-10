package com.picpay.desafio.android.extensions

import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes

fun ImageView.changeOrHideImage(
    @DrawableRes drawable: Int?,
    delayHide: Long = context.shortDelayAnimTime
) {
    if (delayHide > 0) postDelayed(changeOrHideImage(this, drawable), delayHide)
    else changeOrHideImage(this, drawable).run()
}

private fun changeOrHideImage(imageView: ImageView, @DrawableRes drawable: Int?): Runnable =
    Runnable {
        imageView.visibility = View.INVISIBLE
        imageView.visibility = if (drawable == null) {
            View.GONE
        } else {
            imageView.setImageResource(drawable)
            View.VISIBLE
        }
    }