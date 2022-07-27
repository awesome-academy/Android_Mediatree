package com.truongdc21.mediatree.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.truongdc21.mediatree.ui.fragment.MediaImageFragment
import com.truongdc21.mediatree.ui.fragment.MediaListSongFragment

class AdapterVPGMediaPlay(
    fragment: FragmentManager ,
    lifecycle: Lifecycle
): FragmentStateAdapter (fragment , lifecycle){
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> return MediaImageFragment()
            else -> return MediaListSongFragment()
        }
    }
}
