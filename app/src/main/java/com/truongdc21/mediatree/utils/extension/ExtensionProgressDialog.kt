package com.truongdc21.mediatree.utils.extension

import android.app.ProgressDialog
import com.truongdc21.mediatree.R

fun ProgressDialog.showPropressbar(){
    this.let {
        it.setCancelable(false)
        it.show()
        it.setContentView(R.layout.layout_custom_propressdialog)
        it.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }
}
