package com.truongdc21.mediatree.data.source.local

import androidx.lifecycle.LiveData
import com.truongdc21.mediatree.data.database.SongDao
import com.truongdc21.mediatree.data.model.Song
import com.truongdc21.mediatree.data.source.SongDataSource

class SonglocalSource(
    private val songDao : SongDao
): SongDataSource.Local {

    override suspend fun insertLocalSong(song: Song) {
        songDao.insertSong(song)
    }

    override suspend fun updateLocalSong(song: Song) {
        songDao.updateSong(song)
    }

    override suspend fun deleteLocalSong(song: Song) {
        songDao.deleteSOng(song)
    }

    override suspend fun getSongLocal(): LiveData<List<Song>> {
        return songDao.getSongLocal()
    }
}
