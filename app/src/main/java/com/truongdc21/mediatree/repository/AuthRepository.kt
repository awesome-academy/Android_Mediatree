package com.truongdc21.mediatree.repository

import com.truongdc21.mediatree.base.BaseRepository
import com.truongdc21.mediatree.data.firebase.OnAuthResult
import com.truongdc21.mediatree.data.source.AuthDataSource
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val remoteAuth: AuthDataSource.Remote
) : BaseRepository() {

    suspend fun signInAuth(email: String, pass: String, onAuthResult: OnAuthResult<Unit>) =
        withContextResult {
            remoteAuth.signInAuth(email, pass, onAuthResult)
        }

    suspend fun signUpAuth(email: String, pass: String, onAuthResult: OnAuthResult<Unit>) =
        withContextResult {
            remoteAuth.signUpAuth(email, pass, onAuthResult)
        }
}
