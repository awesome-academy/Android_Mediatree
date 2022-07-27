package com.truongdc21.mediatree.data.source.remote

import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.truongdc21.mediatree.data.source.PlayListDataSource
import com.truongdc21.mediatree.utils.ConstantFirebase
import kotlinx.coroutines.tasks.await

class PlayListRemoteSource : PlayListDataSource.Remote {

    private val playlistFirebase = Firebase.firestore.collection(ConstantFirebase.PLAYLIST_COLLECTION)

    override suspend fun getPlayListRemote(): QuerySnapshot {
        return playlistFirebase.get().await()
    }
}