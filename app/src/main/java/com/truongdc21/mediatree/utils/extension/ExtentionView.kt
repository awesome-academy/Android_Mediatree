package com.truongdc21.mediatree.utils.extension

import android.view.View
import android.view.animation.AlphaAnimation

fun View.setAlphaAnimation(){
    this.startAnimation(AlphaAnimation(9f, 0.1f))
}
