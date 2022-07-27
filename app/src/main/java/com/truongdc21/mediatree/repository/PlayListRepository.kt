package com.truongdc21.mediatree.repository

import com.truongdc21.mediatree.base.BaseRepository
import com.truongdc21.mediatree.data.source.PlayListDataSource
import javax.inject.Inject

class PlayListRepository @Inject constructor(
    private val remote: PlayListDataSource.Remote
): BaseRepository(){

    suspend fun getPlayListRemote() = withContextResult {
        remote.getPlayListRemote()
    }
}
