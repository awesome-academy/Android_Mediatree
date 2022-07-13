package com.truongdc21.mediatree.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.truongdc21.mediatree.data.model.Song

@Dao
interface SongDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSong(song: Song)

    @Update
    suspend fun updateSong(song: Song)

    @Delete
    suspend fun deleteSOng(song: Song)

    @Query("SELECT * FROM song ORDER BY Id ASC ")
    fun getSongLocal(): LiveData<List<Song>>
}
