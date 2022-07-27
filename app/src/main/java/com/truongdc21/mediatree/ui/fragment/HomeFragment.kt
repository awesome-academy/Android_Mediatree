package com.truongdc21.mediatree.ui.fragment

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.icu.text.Transliterator
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.truongdc21.mediatree.MainActivity
import com.truongdc21.mediatree.R
import com.truongdc21.mediatree.base.BaseFragment
import com.truongdc21.mediatree.data.model.Song
import com.truongdc21.mediatree.databinding.FragmentHomeBinding
import com.truongdc21.mediatree.service.MediaPlayService
import com.truongdc21.mediatree.ui.adapter.ArtistsAdapter
import com.truongdc21.mediatree.ui.adapter.PlayListAdapter
import com.truongdc21.mediatree.ui.adapter.SongAdapter
import com.truongdc21.mediatree.ui.adapter.callback.IClickItemSong
import com.truongdc21.mediatree.utils.ConstantMedia
import com.truongdc21.mediatree.utils.extension.showPropressbar
import com.truongdc21.mediatree.viewmodel.HomeViewModel
import com.truongdc21.mediatree.viewmodel.MediaSharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment :
        BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
        IClickItemSong {

    lateinit var  mActivityMain : Activity
    private val mViewModel : HomeViewModel by viewModels()
    private val mMediaSharedViewModel : MediaSharedViewModel by activityViewModels()
    private val adapterSong by lazy { SongAdapter(this) }
    private val adapterArtists by lazy { ArtistsAdapter() }
    private val adapterPlaylist by lazy { PlayListAdapter(this@HomeFragment.requireContext())}
    private val mProgressDialog by lazy { ProgressDialog(this@HomeFragment.requireContext()) }

    override fun initView() {
        hideView()
        setRecyclerviewSong()
        setRecyclerviewArtists()
        setRecyclerviewPlaylist()
    }

    override fun initData() {
        showAdapterSong()
        showAdapterPlaylist()
        showAdapterArtists()

        clickItemArtists()
        clickItemPlaylist()
        showLoading()

        activity?.let {
            mActivityMain = it
        }
    }

    private fun showLoading(){
        mViewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) mProgressDialog.showPropressbar()
            else {
                mProgressDialog.dismiss()
                showView()
            }
        }
    }

    private fun clickItemArtists() {
        adapterArtists.clickItemPlayList = {
            val action = HomeFragmentDirections.actionHomeFragmentToListSongFragment(it , null)
            this@HomeFragment.findNavController().navigate(
                action
            )
        }
    }

    private fun clickItemPlaylist() {
        adapterPlaylist.clickItemPlayList = {
            val action = HomeFragmentDirections.actionHomeFragmentToListSongFragment(null, it)
            this@HomeFragment.findNavController().navigate(
                action
            )
        }
    }
    private fun showAdapterSong() {
        mViewModel.getSongFirebase()
        mViewModel.songFromRemoteLiveData.observe(viewLifecycleOwner) {
            adapterSong.submitList(it)
        }
    }

    private fun showAdapterPlaylist() {
        mViewModel.getPlayListRemote()
        mViewModel.playlistFromRemoteLiveData.observe(viewLifecycleOwner) {
            adapterPlaylist.submitList(it)
        }
    }

    private fun showAdapterArtists() {
        mViewModel.getArtistRemote()
        mViewModel.artistFromRemoteLiveData.observe(viewLifecycleOwner) {
            adapterArtists.submitList(it)
        }
    }

    private fun setRecyclerviewSong(){
        binding.apply {
            rvSongs.layoutManager = LinearLayoutManager(this@HomeFragment.context)
            rvSongs.adapter = adapterSong
        }
    }

    private fun setRecyclerviewArtists(){
        binding.apply {
            rvArtists.layoutManager = LinearLayoutManager(
                this@HomeFragment.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            rvArtists.adapter = adapterArtists
        }
    }

    private fun setRecyclerviewPlaylist(){
        binding.apply {
            rvPlaylist.layoutManager = LinearLayoutManager(
                this@HomeFragment.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            rvPlaylist.adapter = adapterPlaylist
        }
    }

    private fun hideView() {
        binding.apply {
            tvArtists.isVisible = false
            tvPlaylist.isVisible = false
            tvSong.isVisible = false
        }
    }

    private fun showView() {
        binding.apply {
            tvArtists.isVisible = true
            tvPlaylist.isVisible = true
            tvSong.isVisible = true
        }
    }

    override fun onStop() {
        super.onStop()
        mProgressDialog.dismiss()
    }

    override fun clickItemSong(song: Song, position: Int) {
        mMediaSharedViewModel.addSongToList(song)
        mMediaSharedViewModel.setTitleTop(resources.getString(R.string.no_playlist))
        findNavController().navigate(
            R.id.action_homeFragment_to_mediaPlayBottomSheetFragment
        )
        if (!mMediaSharedViewModel.isConnectBoundService) {
            val intentService = Intent(this@HomeFragment.activity , MediaPlayService::class.java)
            val bundle = Bundle()
            val mlIstSong = mMediaSharedViewModel.mListSong
            bundle.putParcelableArrayList(
                ConstantMedia.KEY_INTENT_LIST_SONG ,
                mlIstSong as ArrayList
            )
            intentService.putExtras(bundle)
            activity?.startService(intentService)
            activity?.bindService(
                intentService ,
                mMediaSharedViewModel.mMediaServiceConnection,
                Context.BIND_AUTO_CREATE
            )
        }else {
            mMediaSharedViewModel.setMlistSong()
        }
    }

    override fun clickItemMore(song: Song) {}
}
