package com.truongdc21.mediatree.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Song(
    @PrimaryKey (autoGenerate = true)
    val Id : Int?,
    val mediaId : String?,
    val title : String?,
    val artist : String?,
    val songUrl : String?,
    val imageSong : String?
)
