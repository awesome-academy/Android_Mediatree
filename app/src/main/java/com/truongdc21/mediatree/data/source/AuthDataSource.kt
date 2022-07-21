package com.truongdc21.mediatree.data.source

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.truongdc21.mediatree.data.model.User

interface AuthDataSource {

    interface Remote {

        suspend fun signInAuth(email : String, pass : String): Task<AuthResult>

        suspend fun signUpAuth(email: String, pass: String): Task<AuthResult>

        suspend fun signInWithGoogle(account: GoogleSignInAccount): Task<AuthResult>

        suspend fun getAuthProfile(): User

    }

    interface Local {
    }
}
