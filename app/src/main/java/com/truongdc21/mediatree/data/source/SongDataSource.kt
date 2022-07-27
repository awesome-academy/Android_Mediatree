package com.truongdc21.mediatree.data.source

import androidx.lifecycle.LiveData
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.truongdc21.mediatree.data.model.Song
import org.w3c.dom.Document

interface SongDataSource {

    interface Local {

        suspend fun insertLocalSong(song: Song)

        suspend fun updateLocalSong(song: Song)

        suspend fun deleteLocalSong(song: Song)

        suspend fun getSongLocal(): LiveData<List<Song>>
    }

    interface Remote {

        suspend fun insertRemoteSong(song: Song)

        suspend fun getSongRemote(): QuerySnapshot

        suspend fun getSongRemoteDocument(document: String): DocumentSnapshot
    }
}
