package com.truongdc21.mediatree.data.database

import android.os.Build
import androidx.room.Database
import androidx.room.RoomDatabase
import com.truongdc21.mediatree.data.model.Song

@Database(entities = [Song::class], version = MyDatabase.VESION_DATABASE, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    abstract fun songDao() : SongDao
    companion object {
        const val NAME_DATABASE = "songdb"
        const val VESION_DATABASE = 1
    }
}
