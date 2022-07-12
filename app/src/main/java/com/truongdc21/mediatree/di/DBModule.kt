package com.truongdc21.mediatree.di

import android.content.Context
import androidx.room.Room
import com.truongdc21.mediatree.data.database.MyDatabase
import com.truongdc21.mediatree.data.database.SongDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Singleton
    @Provides
    fun provideSongDatabase(
        @ApplicationContext context: Context
    ): MyDatabase =
        Room.databaseBuilder(context, MyDatabase::class.java, MyDatabase.NAME_DATABASE).allowMainThreadQueries().build()

    @Singleton
    @Provides
    fun provideSongDao(db : MyDatabase): SongDao = db.songDao()
}
