package com.truongdc21.mediatree.data.source

import com.google.firebase.firestore.QuerySnapshot

interface ArtistsDataSource {
    interface Remote {
        suspend fun getArtistsRemote(): QuerySnapshot
    }
}