package com.truongdc21.mediatree.di

import com.google.firebase.auth.FirebaseAuth
import com.truongdc21.mediatree.data.source.AuthDataSource
import com.truongdc21.mediatree.data.source.remote.AuthRemoteSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthDataSourceModule {

    @Singleton
    @Provides
    fun provideAuthDataSource(): AuthDataSource.Remote = AuthRemoteSource()
}
