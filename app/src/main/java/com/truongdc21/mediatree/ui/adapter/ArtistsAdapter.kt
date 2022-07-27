package com.truongdc21.mediatree.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.truongdc21.mediatree.data.model.Artists
import com.truongdc21.mediatree.databinding.LayoutItemArtistsBinding
import com.truongdc21.mediatree.utils.diffcallback.ArtistsDiffCallBack
import com.truongdc21.mediatree.utils.extension.setAlphaAnimation
import com.truongdc21.mediatree.utils.extension.showImageGlideWithURL
import java.util.concurrent.Executors

class ArtistsAdapter(): ListAdapter< Artists, ArtistsAdapter.ArtistsViewHolder>(
    AsyncDifferConfig.Builder(ArtistsDiffCallBack())
        .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
        .build()
){
    var clickItemPlayList : ((Artists) -> Unit?)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistsViewHolder {
        val binding = LayoutItemArtistsBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return ArtistsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtistsViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class ArtistsViewHolder(
        private val binding: LayoutItemArtistsBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(itemArtists: Artists){
            binding.apply {
                itemArtists.uriArtists?.let {
                    imgAvtProfile.showImageGlideWithURL(it)
                }
                tvItemNameArtists.text = itemArtists.nameArtist
                viewArtists.setOnClickListener {
                    it.setAlphaAnimation()
                    clickItemPlayList?.invoke(itemArtists)
                }
            }
        }
    }
}
