package com.truongdc21.mediatree.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity @Parcelize
data class Song(
    @PrimaryKey (autoGenerate = true)
    val Id : Int? = null,
    val artist : String? = null,
    val imgSongURl : String? = null,
    val songURL : String? = null,
    val title : String? = null
): Parcelable
