package com.truongdc21.mediatree.data.source.remote

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

import com.truongdc21.mediatree.data.model.User
import com.truongdc21.mediatree.data.source.AuthDataSource

class AuthRemoteSource : AuthDataSource.Remote{

    private val auth = FirebaseAuth.getInstance()

    override suspend fun signInAuth(email: String, pass: String): Task<AuthResult>{
        return auth.signInWithEmailAndPassword(email , pass)
    }

    override suspend fun signUpAuth(email: String, pass: String): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(email, pass)
    }

    override suspend fun signInWithGoogle(account: GoogleSignInAccount): Task<AuthResult> {
        val credentials = GoogleAuthProvider.getCredential(account.idToken, null)
        return  auth.signInWithCredential(credentials)
    }

    override suspend fun getAuthProfile(): User {
        val profile = auth.currentUser
        return User(
            userId = profile?.uid,
            userEmail = profile?.email,
            userName = profile?.displayName,
            userImageUri = profile?.photoUrl
        )
    }
}
