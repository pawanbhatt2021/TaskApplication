package com.example.taskapplication.presentation.common.extensions

import android.content.Context
import android.content.res.Resources

val Int.dp: Int get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

val Int.sp: Float get() =  ( this.dp / Resources.getSystem().displayMetrics.scaledDensity)
fun Context.pxToSp(px: Float): Float {
    return px / resources.configuration.fontScale / resources.displayMetrics.density
}


