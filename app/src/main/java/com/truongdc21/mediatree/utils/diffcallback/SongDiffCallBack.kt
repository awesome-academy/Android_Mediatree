package com.truongdc21.mediatree.utils.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.truongdc21.mediatree.data.model.Song

class SongDiffCallBack : DiffUtil.ItemCallback<Song>() {
    override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
        return oldItem.songURL  == newItem.songURL
    }

    override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
        return oldItem == oldItem
    }
}
