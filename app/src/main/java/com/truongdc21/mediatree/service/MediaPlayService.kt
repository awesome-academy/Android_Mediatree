package com.truongdc21.mediatree.service
import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.IBinder
import com.truongdc21.mediatree.data.model.Song
import com.truongdc21.mediatree.utils.ConstantMedia
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MediaPlayService : Service() {

    val myBinder = MyBinder()

    inner class MyBinder : Binder() {
        fun getBoundService(): MediaPlayService = this@MediaPlayService

    }

    lateinit var mMediaPlayer : MediaPlayer
    private var mListSong = mutableListOf<Song>()
    private var isPlaying : Boolean = false
    var mPosition =  0 // live data
    private set

    override fun onCreate() {
        super.onCreate()
        mMediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
        }

    }

    override fun onBind(intent: Intent?): IBinder? = myBinder

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val bundle = intent?.extras
        bundle?.let {
            mListSong.apply {
                clear()
                addAll(it.get(ConstantMedia.KEY_INTENT_LIST_SONG) as MutableList<Song>)
            }
            startMusic(mPosition)
        }
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mMediaPlayer.release()
    }

    fun setMlistSong (mList : List<Song>) {
        mListSong.apply {
            clear()
            addAll(mList)
        }
        startMusic(mList.size-1)
    }

    fun startMusic(position: Int) = CoroutineScope(Dispatchers.Default).launch{
        if (isPlaying == true) mMediaPlayer.reset()
        mMediaPlayer.apply {
            setDataSource(this@MediaPlayService , Uri.parse(mListSong[position].songURL))
            prepare()
            start()
        }
        mPosition = position
        isPlaying = true
    }


}