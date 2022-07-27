package com.truongdc21.mediatree.repository

import com.truongdc21.mediatree.base.BaseRepository
import com.truongdc21.mediatree.data.source.ArtistsDataSource
import com.truongdc21.mediatree.data.source.PlayListDataSource
import javax.inject.Inject

class ArtistsRepository @Inject constructor(
    private val remote: ArtistsDataSource.Remote
): BaseRepository(){

    suspend fun getPlayListRemote() = withContextResult {
        remote.getArtistsRemote()
    }
}
