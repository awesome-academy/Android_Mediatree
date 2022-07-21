package com.truongdc21.mediatree.repository

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.truongdc21.mediatree.base.BaseRepository
import com.truongdc21.mediatree.data.source.AuthDataSource
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val remoteAuth: AuthDataSource.Remote
) : BaseRepository() {

    suspend fun signInAuth(email: String, pass: String) =
        withContextResult {
            remoteAuth.signInAuth(email, pass)
        }

    suspend fun signUpAuth(email: String, pass: String) =
        withContextResult {
            remoteAuth.signUpAuth(email, pass)
        }
    suspend fun signInWithGoogle(account: GoogleSignInAccount) =
        withContextResult {
            remoteAuth.signInWithGoogle(account)
        }

    suspend fun getAuthProfile() =
        withContextResult {
            remoteAuth.getAuthProfile()
        }
}
