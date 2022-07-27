package com.truongdc21.mediatree.ui.adapter.callback

import android.icu.text.Transliterator
import com.truongdc21.mediatree.data.model.Song

interface IClickItemSong {

    fun clickItemSong(song: Song, position: Int)

    fun clickItemMore(song: Song)

}
