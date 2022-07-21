package com.truongdc21.mediatree.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Song(
    @PrimaryKey (autoGenerate = true)
    val Id : Int? = null,
    val artist : String? = null,
    val imgSongURl : String? = null,
    val songURL : String? = null,
    val title : String? = null
)
