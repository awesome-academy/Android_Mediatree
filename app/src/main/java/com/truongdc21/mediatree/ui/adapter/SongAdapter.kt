package com.truongdc21.mediatree.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.truongdc21.mediatree.data.model.Song
import com.truongdc21.mediatree.databinding.LayoutItemSongBinding
import com.truongdc21.mediatree.utils.diffcallback.SongDiffCallBack
import com.truongdc21.mediatree.utils.extension.setAlphaAnimation
import com.truongdc21.mediatree.utils.extension.showImageGlideWithURL
import java.util.concurrent.Executors

class SongAdapter(
    private val mContext : Context
): ListAdapter< Song, SongAdapter.SongViewHolder>(
    AsyncDifferConfig.Builder(SongDiffCallBack())
        .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
        .build()
){
    var clickItemSong : ((Song) -> Unit?)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val binding = LayoutItemSongBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return SongViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class SongViewHolder(
        private val binding: LayoutItemSongBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(itemSong : Song){
            binding.apply {
                itemSong.imgSongURl?.let {
                    imgItemSong.showImageGlideWithURL(it)
                }
                tvItemNameSong.text = itemSong.title
                tvItemAtists.text = itemSong.artist
                viewItemSong.setOnClickListener {
                    clickItemSong?.invoke(itemSong)
                    it.setAlphaAnimation()
                }
            }
        }
    }
}
