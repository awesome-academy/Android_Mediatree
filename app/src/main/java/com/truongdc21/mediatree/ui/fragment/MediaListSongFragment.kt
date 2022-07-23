package com.truongdc21.mediatree.ui.fragment

import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.truongdc21.mediatree.MainActivity
import com.truongdc21.mediatree.base.BaseFragment
import com.truongdc21.mediatree.data.model.Song
import com.truongdc21.mediatree.databinding.FragmentMediaListSongBinding
import com.truongdc21.mediatree.ui.adapter.SongAdapter
import com.truongdc21.mediatree.ui.adapter.callback.IClickItemSong
import com.truongdc21.mediatree.viewmodel.MediaSharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MediaListSongFragment :
        BaseFragment<FragmentMediaListSongBinding>(FragmentMediaListSongBinding::inflate),
        IClickItemSong {

    private val  mMediaSharedViewModel: MediaSharedViewModel by activityViewModels()
    private val adapterSong by lazy { SongAdapter(this) }

    override fun initView() {
        binding.apply {
            rvSong.layoutManager = LinearLayoutManager(this@MediaListSongFragment.requireContext())
            rvSong.adapter = adapterSong
        }
    }

    override fun initData() {
        showAdapterSong()
    }

    private fun showAdapterSong() {
        mMediaSharedViewModel.listSongLiveData.observe(viewLifecycleOwner) { listSong ->
            adapterSong.submitList(listSong)
        }
    }

    override fun clickItemSong(song: Song , position: Int) {
        mMediaSharedViewModel.startMusic(position)
    }

    override fun clickItemMore(song: Song) {}
}
