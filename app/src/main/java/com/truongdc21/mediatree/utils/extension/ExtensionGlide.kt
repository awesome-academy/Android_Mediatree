package com.truongdc21.mediatree.utils.extension

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.truongdc21.mediatree.R

fun ImageView.showImageGlideWithURI (uri: Uri){
        Glide.with(context)
                .load(uri)
                .placeholder(R.color.gray)
                .error(R.color.gray)
                .into(this)
}

fun ImageView.showImageGlideWithURL (url: String){
        Glide.with(context)
                .load(url)
                .placeholder(R.color.gray)
                .error(R.color.gray)
                .into(this)
}
