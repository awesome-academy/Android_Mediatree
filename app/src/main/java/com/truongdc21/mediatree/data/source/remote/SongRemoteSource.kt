package com.truongdc21.mediatree.data.source.remote

import androidx.lifecycle.LiveData
import com.truongdc21.mediatree.data.model.Song
import com.truongdc21.mediatree.data.source.SongDataSource

class SongRemoteSource : SongDataSource.Remote{

    override suspend fun insertRemoteSong(song: Song) {
        TODO("Not yet implemented")
    }

    override suspend fun getSongRemote(): LiveData<List<Song>> {
        TODO("Not yet implemented")
    }

}
