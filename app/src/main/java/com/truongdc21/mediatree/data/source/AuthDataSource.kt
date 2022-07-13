package com.truongdc21.mediatree.data.source

import com.truongdc21.mediatree.data.firebase.OnAuthResult

interface AuthDataSource {

    interface Remote {

        suspend fun signInAuth(email : String, pass : String , onAuthResult: OnAuthResult<Unit>)

        suspend fun signUpAuth(email: String, pass: String, onAuthResult: OnAuthResult<Unit>)

    }

    interface Local {
    }
}
