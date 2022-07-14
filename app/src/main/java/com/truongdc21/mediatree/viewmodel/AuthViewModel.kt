package com.truongdc21.mediatree.viewmodel

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.truongdc21.mediatree.base.BaseViewModel
import com.truongdc21.mediatree.repository.AuthRepository
import com.truongdc21.mediatree.utils.livedata.SingleLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
   private val authRepository: AuthRepository
) : BaseViewModel() {

   val signInAuthLiveData = SingleLiveData<Boolean>()
   val signUpAuthLiveData = SingleLiveData<Boolean>()
   val signInWithGoogleLiveData = SingleLiveData<Boolean>()

   fun signInAuth(email : String, pass : String) {
      launchTaskSyncAuthFirebase(
         onRequest = {
            authRepository.signInAuth(email , pass)
         },
         onSuccess = {
            signInAuthLiveData.value = true
         }
      )
   }

   fun signUpAuth(email: String , pass: String) {
      launchTaskSyncAuthFirebase(
         onRequest = {
            authRepository.signUpAuth(email , pass)
         },
         onSuccess = {
            signUpAuthLiveData.value = true
         }
      )
   }

   fun googleAuthForFirebase(account: GoogleSignInAccount) {
      launchTaskSyncAuthFirebase(
         onRequest = {
            authRepository.signInWithGoogle(account)
         },
         onSuccess = {
            signInWithGoogleLiveData.value = true
         }
      )
   }
}
