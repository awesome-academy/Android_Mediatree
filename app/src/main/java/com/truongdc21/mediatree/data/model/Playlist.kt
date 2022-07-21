package com.truongdc21.mediatree.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Playlist (
    val listSong : MutableList<String>? = null,
    val name : String? = null,
    val uriPlaylist : String? = null
): Parcelable
