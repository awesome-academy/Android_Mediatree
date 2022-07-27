package com.truongdc21.mediatree.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.truongdc21.mediatree.data.model.Song
import com.truongdc21.mediatree.databinding.LayoutItemSongBinding
import com.truongdc21.mediatree.ui.adapter.callback.IClickItemSong
import com.truongdc21.mediatree.utils.diffcallback.SongDiffCallBack
import com.truongdc21.mediatree.utils.extension.setAlphaAnimation
import com.truongdc21.mediatree.utils.extension.showImageGlideWithURL
import java.util.concurrent.Executors

class SongAdapter(
    private val iClickItemSong: IClickItemSong
): ListAdapter< Song, SongAdapter.SongViewHolder>(
    AsyncDifferConfig.Builder(SongDiffCallBack())
        .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
        .build()
){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val binding = LayoutItemSongBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return SongViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.onBind(getItem(position) , position)
    }

    inner class SongViewHolder(
        private val binding: LayoutItemSongBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(itemSong : Song , position: Int){
            binding.apply {
                itemSong.imgSongURl?.let {
                    imgItemSong.showImageGlideWithURL(it)
                }
                tvItemNameSong.text = itemSong.title
                tvItemAtists.text = itemSong.artist
                viewItemSong.setOnClickListener {
                    it.setAlphaAnimation()
                    iClickItemSong.clickItemSong(itemSong , position)
                }
                imgMore.setOnClickListener {
                    it.setAlphaAnimation()
                    iClickItemSong.clickItemMore(itemSong)
                }
            }
        }
    }
}
