package com.truongdc21.mediatree.data.source.remote

import android.accounts.AuthenticatorException
import com.google.firebase.auth.FirebaseAuth
import com.truongdc21.mediatree.data.firebase.OnAuthResult
import com.truongdc21.mediatree.data.source.AuthDataSource

class AuthRemoteSource : AuthDataSource.Remote{

    private val auth = FirebaseAuth.getInstance()

    override suspend fun signInAuth(email: String, pass: String, onAuthResult: OnAuthResult<Unit>) {
        auth.signInWithEmailAndPassword(email , pass).addOnCompleteListener { task ->
            if (task.isSuccessful){
                onAuthResult.onSuccessful(Unit)
            }else{
                onAuthResult.onError(task.exception as AuthenticatorException)
            }
        }
    }

    override suspend fun signUpAuth(email: String, pass: String , onAuthResult : OnAuthResult<Unit>) {
        auth.createUserWithEmailAndPassword(email, pass)
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
            if (task.isSuccessful){
                onAuthResult.onSuccessful(Unit)
            }else{
                onAuthResult.onError(task.exception as AuthenticatorException)
            }
        }
    }
}
