package com.truongdc21.mediatree.ui.fragment

import android.app.ProgressDialog
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.truongdc21.mediatree.R
import com.truongdc21.mediatree.base.BaseFragment
import com.truongdc21.mediatree.databinding.FragmentListSongBinding
import com.truongdc21.mediatree.ui.adapter.SongAdapter
import com.truongdc21.mediatree.utils.extension.setAlphaAnimation
import com.truongdc21.mediatree.utils.extension.showPropressbar
import com.truongdc21.mediatree.viewmodel.ListSongViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListSongFragment : BaseFragment<FragmentListSongBinding>(FragmentListSongBinding::inflate) {

    private val mViewModel : ListSongViewModel by viewModels()
    private val adapterSong  by lazy { SongAdapter(this@ListSongFragment.requireContext()) }
    private val mProgressDialog by lazy { ProgressDialog(this@ListSongFragment.requireContext()) }

    val argsCurrent by navArgs<ListSongFragmentArgs>()

    override fun initView() {
        binding.apply {
            rvSong.layoutManager = LinearLayoutManager(this@ListSongFragment.context)
            rvSong.adapter = adapterSong
            imgBack.setOnClickListener {
                it.setAlphaAnimation()
                findNavController().navigate(
                    R.id.action_listSongFragment_to_homeFragment
                )
            }
        }
    }

    override fun initData() {
        hideBottomNavigation()
        getDataFromArgs()
        showAdapterSong()
        showPropressbar()
    }

    override fun onDetach() {
        super.onDetach()
        showBottomNavigation()
    }

    private fun getDataFromArgs() {
        if (argsCurrent.currentArtists != null){
            val artists = argsCurrent.currentArtists
            artists?.nameArtist?.let {
                setTitle(it)
            }
            artists?.listSong?.let{
                mViewModel.getSongFromPlayList(it)
            }
        }

        if (argsCurrent.currentPlaylist != null) {
            val playList = argsCurrent.currentPlaylist
            playList?.name?.let {
                setTitle(it)
            }
            playList?.listSong?.let {
                mViewModel.getSongFromArtists(it)
            }
        }

    }

    private fun showAdapterSong() {
        mViewModel.listSongLiveData.observe(viewLifecycleOwner) {
            if(it.isNotEmpty()) checkListIsNotEmpty()
            else checkListIsEmpty()
            adapterSong.submitList(it)
        }
    }

    private fun setTitle(title : String){
        binding.tvTitle.text = title
    }

    private fun checkListIsEmpty() {
        binding.apply {
            tvSong.isVisible = false
            btnPlayAll.isVisible = false
            viewIsEmpty.isVisible = true
        }
    }

    private fun showPropressbar() {
        mViewModel.loadListSong.observe(viewLifecycleOwner) {
            if (it) {
                mProgressDialog.showPropressbar()
                binding.btnPlayAll.isVisible = false
            }
            else mProgressDialog.dismiss()
        }
    }

    private fun checkListIsNotEmpty() {
        binding.apply {
            tvSong.isVisible = true
            btnPlayAll.isVisible = true
            viewIsEmpty.isVisible = false
        }
    }

    private fun hideBottomNavigation() {
        val bottomNavigationView =
            activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView?.visibility = View.GONE
    }

    private fun showBottomNavigation() {
        val bottomNavigationView =
            activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView?.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        mProgressDialog.dismiss()
    }
}
