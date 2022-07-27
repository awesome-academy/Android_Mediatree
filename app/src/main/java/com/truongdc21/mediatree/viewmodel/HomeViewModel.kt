package com.truongdc21.mediatree.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.PrimaryKey
import com.google.firebase.firestore.ktx.toObject
import com.truongdc21.mediatree.base.BaseViewModel
import com.truongdc21.mediatree.data.model.Artists
import com.truongdc21.mediatree.data.model.Playlist
import com.truongdc21.mediatree.data.model.Song
import com.truongdc21.mediatree.repository.ArtistsRepository
import com.truongdc21.mediatree.repository.PlayListRepository
import com.truongdc21.mediatree.repository.SongRepository
import com.truongdc21.mediatree.utils.DataResult
import com.truongdc21.mediatree.utils.livedata.SingleLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val songRepository: SongRepository,
    private val playListRepository: PlayListRepository,
    private val artistsRepository: ArtistsRepository,
) : BaseViewModel() {

    val songFromRemoteLiveData = MutableLiveData<List<Song>>()
    val playlistFromRemoteLiveData = MutableLiveData<List<Playlist>>()
    val artistFromRemoteLiveData = MutableLiveData<List<Artists>>()

    fun getSongFirebase() {
        launchTaskSync(
            onRequest = {
                songRepository.getRemoteSong()
            },
            onSuccess = {
                val listSong = mutableListOf<Song>()
                for (i in it.documents){
                    val song = i.toObject<Song>()
                    song?.let {
                       listSong.add(it)
                    }
                }
                songFromRemoteLiveData.value = listSong
            }
        )
    }

    fun getPlayListRemote() {
        launchTaskSync(
            onRequest = {
                playListRepository.getPlayListRemote()
            },
            onSuccess = {
                val listPlayList = mutableListOf<Playlist>()
                for (i in it.documents){
                    val playlist = i.toObject<Playlist>()
                    playlist?.let {
                        listPlayList.add(it)
                    }
                }
                playlistFromRemoteLiveData.value = listPlayList
            }
        )
    }

    fun getArtistRemote() {
        launchTaskSync(
            onRequest = {
                artistsRepository.getPlayListRemote()
            },
            onSuccess = {
                val listArtists = mutableListOf<Artists>()
                for (i in it.documents){
                    val playlist = i.toObject<Artists>()
                    playlist?.let {
                        listArtists.add(it)
                    }
                }
                artistFromRemoteLiveData.value = listArtists
            }
        )
    }
}
