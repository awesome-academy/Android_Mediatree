package com.truongdc21.mediatree.di

import com.truongdc21.mediatree.data.database.SongDao
import com.truongdc21.mediatree.data.source.ArtistsDataSource
import com.truongdc21.mediatree.data.source.SongDataSource
import com.truongdc21.mediatree.data.source.local.SonglocalSource
import com.truongdc21.mediatree.data.source.remote.ArtistsRemoteSource
import com.truongdc21.mediatree.data.source.remote.SongRemoteSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ArtistsDataSourceModule {

    @Provides
    @Singleton
    fun providerArtistsRemoteSource(): ArtistsDataSource.Remote = ArtistsRemoteSource()
}
