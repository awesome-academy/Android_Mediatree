package com.truongdc21.mediatree.utils.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.truongdc21.mediatree.data.model.Artists

class ArtistsDiffCallBack : DiffUtil.ItemCallback<Artists>() {
    override fun areItemsTheSame(oldItem: Artists, newItem: Artists): Boolean {
        return oldItem.nameArtist == newItem.nameArtist
    }

    override fun areContentsTheSame(oldItem: Artists, newItem: Artists): Boolean {
        return  oldItem == oldItem
    }
}
