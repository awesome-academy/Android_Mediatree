package com.truongdc21.mediatree.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Artists(
    val listSong : MutableList<String>? = null,
    val nameArtist : String? = null,
    val uriArtists : String? = null
): Parcelable
