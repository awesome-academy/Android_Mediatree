package com.truongdc21.mediatree.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.truongdc21.mediatree.R
import com.truongdc21.mediatree.data.model.Playlist
import com.truongdc21.mediatree.databinding.LayoutItemPlaylistBinding
import com.truongdc21.mediatree.utils.diffcallback.PlayListDiffCallBack
import com.truongdc21.mediatree.utils.extension.setAlphaAnimation
import com.truongdc21.mediatree.utils.extension.showImageGlideWithURL
import java.util.concurrent.Executors

class PlayListAdapter(
    private val mContext : Context
): ListAdapter< Playlist, PlayListAdapter.PlayListViewHolder>(
    AsyncDifferConfig.Builder(PlayListDiffCallBack())
        .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
        .build()
){
    var clickItemPlayList : ((Playlist) -> Unit?)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayListViewHolder {
        val binding = LayoutItemPlaylistBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return PlayListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayListViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class PlayListViewHolder(
        private val binding: LayoutItemPlaylistBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(itemPlayList: Playlist){
            binding.apply {
                itemPlayList.uriPlaylist?.let {
                    imgItemPlayList.showImageGlideWithURL(it)
                }
                tvItemNamePlaylist.text = itemPlayList.name
                tvItemTracks.text = "${itemPlayList.listSong?.size} " +
                        " ${mContext.resources.getString(R.string.tracks)}"
                viewPlayList.setOnClickListener {
                    it.setAlphaAnimation()
                    clickItemPlayList?.invoke(itemPlayList)
                }
            }
        }
    }
}
