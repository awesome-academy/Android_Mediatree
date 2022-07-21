package com.truongdc21.mediatree.data.source.remote

import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.truongdc21.mediatree.data.source.ArtistsDataSource
import com.truongdc21.mediatree.utils.ConstantFirebase
import kotlinx.coroutines.tasks.await

class ArtistsRemoteSource : ArtistsDataSource.Remote {

    private val artistsFirebase = Firebase.firestore.collection(ConstantFirebase.ARTIST_COLLECTION)

    override suspend fun getArtistsRemote(): QuerySnapshot {
        return artistsFirebase.get().await()
    }
}
