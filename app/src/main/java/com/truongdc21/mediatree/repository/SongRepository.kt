package com.truongdc21.mediatree.repository

import com.truongdc21.mediatree.base.BaseRepository
import com.truongdc21.mediatree.data.model.Song
import com.truongdc21.mediatree.data.source.SongDataSource
import javax.inject.Inject

class SongRepository @Inject constructor(
    private val songLocal: SongDataSource.Local,
    private val songRemote: SongDataSource.Remote
) : BaseRepository() {

    suspend fun insertLocalSong(song: Song) = withContextResult {
        songLocal.insertLocalSong(song)
    }

    suspend fun updateLocalSong(song: Song) = withContextResult {
        songLocal.updateLocalSong(song)
    }

    suspend fun deleteLocalSong(song: Song) = withContextResult {
        songLocal.deleteLocalSong(song)
    }

    suspend fun getLocalSong() = withContextResult {
        songLocal.getSongLocal()
    }

    suspend fun insertRemoteSong(song: Song) = withContextResult {
        songRemote.insertRemoteSong(song)
    }

    suspend fun getRemoteSong() = withContextResult {
        songRemote.getSongRemote()
    }
}
