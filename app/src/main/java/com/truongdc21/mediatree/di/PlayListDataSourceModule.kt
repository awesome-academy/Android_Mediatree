package com.truongdc21.mediatree.di

import com.truongdc21.mediatree.data.source.PlayListDataSource
import com.truongdc21.mediatree.data.source.remote.PlayListRemoteSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PlayListDataSourceModule {

    @Provides
    @Singleton
    fun providerPlayListDataSource(): PlayListDataSource.Remote = PlayListRemoteSource()
}
