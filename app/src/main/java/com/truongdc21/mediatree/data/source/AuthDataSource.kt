package com.truongdc21.mediatree.data.source

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface AuthDataSource {

    interface Remote {

        suspend fun signInAuth(email : String, pass : String ): Task<AuthResult>

        suspend fun signUpAuth(email: String, pass: String): Task<AuthResult>

        suspend fun signInWithGoogle(account: GoogleSignInAccount): Task<AuthResult>
    }

    interface Local {
    }
}
