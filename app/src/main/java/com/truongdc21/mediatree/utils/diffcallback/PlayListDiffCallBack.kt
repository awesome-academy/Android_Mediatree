package com.truongdc21.mediatree.utils.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.truongdc21.mediatree.data.model.Playlist
import com.truongdc21.mediatree.data.model.Song

class PlayListDiffCallBack : DiffUtil.ItemCallback<Playlist>() {
    override fun areItemsTheSame(oldItem: Playlist, newItem: Playlist): Boolean {
        return oldItem.name == oldItem.name
    }

    override fun areContentsTheSame(oldItem: Playlist, newItem: Playlist): Boolean {
        return oldItem == oldItem
    }
}
