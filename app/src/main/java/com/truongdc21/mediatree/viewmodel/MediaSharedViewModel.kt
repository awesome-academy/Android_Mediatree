package com.truongdc21.mediatree.viewmodel
import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.ServiceConnection
import android.icu.text.Transliterator
import android.os.IBinder
import android.support.v4.media.MediaBrowserCompat
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.truongdc21.mediatree.base.BaseViewModel
import com.truongdc21.mediatree.data.model.Song
import com.truongdc21.mediatree.service.MediaPlayService
import com.truongdc21.mediatree.utils.dispatcher.DefaultDispatcher
import com.truongdc21.mediatree.utils.dispatcher.IoDispatcher
import com.truongdc21.mediatree.utils.dispatcher.MainDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MediaSharedViewModel @Inject constructor(): BaseViewModel() {

    private val mPosition = 0
    val mListSong = mutableListOf<Song>()
    val listSongLiveData = MutableLiveData<List<Song>>()
    val positionLiveData = MutableLiveData<Int>()
    val titleTop = MutableLiveData<String>()


    @SuppressLint("StaticFieldLeak")
    lateinit var mMediaService : MediaPlayService
    var isConnectBoundService = false

    val mMediaServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, iBinder: IBinder?) {
            val myBinder = iBinder as MediaPlayService.MyBinder
            mMediaService = myBinder.getBoundService()
            isConnectBoundService = true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            isConnectBoundService = false
        }
    }

    @Inject @DefaultDispatcher
    lateinit var dispatcherDefault : CoroutineDispatcher

    @Inject  @IoDispatcher
    lateinit var dispatcherIo : CoroutineDispatcher

    @Inject @MainDispatcher
    lateinit var dispatcherMain: CoroutineDispatcher

    fun setTitleTop(text : String) {
        titleTop.value = text
    }


    fun addAllSongToList(listSong: List<Song>) =
        viewModelScope.launch(dispatcherDefault) {
            mListSong.apply {
                clear()
                addAll(listSong)
                withContext(dispatcherMain){
                    listSongLiveData.value = mListSong
                }
            }
    }

    fun addSongToList(song: Song) =
        viewModelScope.launch(dispatcherDefault) {
            if (mListSong.isEmpty()) {
                addSong(song)
            }else {
                for (i in mListSong) {
                    if (song.songURL == i.songURL){
                        mListSong.removeAt(mListSong.indexOf(i))
                        break
                    }
                }
                addSong(song)
            }
    }

    private suspend fun addSong(song: Song) {
        mListSong.apply {
            add(song)
            withContext(dispatcherMain){
                listSongLiveData.value = mListSong
            }
        }
    }

    fun startMusic(position: Int) {
           mMediaService.startMusic(position)
    }

    fun setMlistSong() {
        mMediaService.setMlistSong(mListSong)
    }

}