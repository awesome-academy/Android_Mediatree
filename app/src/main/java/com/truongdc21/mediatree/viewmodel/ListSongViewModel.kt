package com.truongdc21.mediatree.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.toObject
import com.truongdc21.mediatree.base.BaseViewModel
import com.truongdc21.mediatree.data.model.Song
import com.truongdc21.mediatree.repository.SongRepository
import com.truongdc21.mediatree.utils.DataResult
import com.truongdc21.mediatree.utils.livedata.SingleLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListSongViewModel @Inject constructor(
    private val songRepository: SongRepository
) : BaseViewModel() {

    val listSongLiveData = MutableLiveData<List<Song>>()
    val loadListSong = SingleLiveData<Boolean>()
    val mListSong = mutableListOf<Song>()

    fun getSongFromArtists(listArtist : MutableList<String>)
    = viewModelScope.launch {
        loadListSong.value = true
        val listSong = mutableListOf<Song>()
        for (i in listArtist) {
            songRepository.getRemoteSongDocument(i).let { dataResult ->
                when(dataResult){
                    is DataResult.Success -> {
                        dataResult.data.toObject<Song>()?.let { listSong.add(it) }
                    }
                    is DataResult.Error -> {
                        loadListSong.value = false
                    }
                }
            }
        }
        mListSong.apply {
            clear()
            addAll(listSong)
            listSongLiveData.value = this
        }
        loadListSong.value = false
    }

    fun getSongFromPlayList(listPlayList : MutableList<String>)
            = viewModelScope.launch {
        loadListSong.value = true
        val listSong = mutableListOf<Song>()
        for (i in listPlayList) {
            songRepository.getRemoteSongDocument(i).let { dataResult ->
                when(dataResult){
                    is DataResult.Success -> {
                        dataResult.data.toObject<Song>()?.let { listSong.add(it) }
                    }
                    is DataResult.Error -> {
                        loadListSong.value = false
                    }
                }
            }
        }
        mListSong.apply {
            clear()
            addAll(listSong)
            listSongLiveData.value = this
        }
        loadListSong.value = false
    }
}
