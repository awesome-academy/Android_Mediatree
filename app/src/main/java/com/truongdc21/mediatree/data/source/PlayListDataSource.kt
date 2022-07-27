package com.truongdc21.mediatree.data.source

import com.google.firebase.firestore.QuerySnapshot

interface PlayListDataSource {

    interface Remote {

        suspend fun getPlayListRemote() : QuerySnapshot
    }
}
