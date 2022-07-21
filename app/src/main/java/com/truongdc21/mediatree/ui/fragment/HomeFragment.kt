package com.truongdc21.mediatree.ui.fragment

import android.app.ProgressDialog
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.truongdc21.mediatree.base.BaseFragment
import com.truongdc21.mediatree.databinding.FragmentHomeBinding
import com.truongdc21.mediatree.ui.adapter.ArtistsAdapter
import com.truongdc21.mediatree.ui.adapter.PlayListAdapter
import com.truongdc21.mediatree.ui.adapter.SongAdapter
import com.truongdc21.mediatree.utils.extension.showPropressbar
import com.truongdc21.mediatree.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val mViewModel : HomeViewModel by viewModels()
    private val adapterSong by lazy { SongAdapter(this@HomeFragment.requireContext()) }
    private val adapterArtists by lazy { ArtistsAdapter(this@HomeFragment.requireContext()) }
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

        clickItemSong()
        clickItemArtists()
        clickItemPlaylist()
        showLoading()
    }

    private fun clickItemSong() {
        adapterSong.clickItemSong = { mSong ->

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
}
