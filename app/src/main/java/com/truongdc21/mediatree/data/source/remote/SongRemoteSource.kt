package com.truongdc21.mediatree.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firestore.v1.Document
import com.truongdc21.mediatree.data.model.Song
import com.truongdc21.mediatree.data.source.SongDataSource
import com.truongdc21.mediatree.utils.ConstantFirebase
import kotlinx.coroutines.tasks.await

class SongRemoteSource : SongDataSource.Remote{

    private val songDB = Firebase.firestore.collection(ConstantFirebase.SONG_COLLECTION)

    override suspend fun insertRemoteSong(song: Song) {
        TODO("Not yet implemented")
    }

    override suspend fun getSongRemote(): QuerySnapshot {
        return songDB.get().await()
    }

    override suspend fun getSongRemoteDocument(document: String): DocumentSnapshot {
        return songDB.document(document).get().await()
    }
}
