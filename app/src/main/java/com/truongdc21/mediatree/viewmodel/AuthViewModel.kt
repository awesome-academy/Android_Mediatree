package com.truongdc21.mediatree.viewmodel

import com.truongdc21.mediatree.base.BaseViewModel
import com.truongdc21.mediatree.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
   private val authRepository: AuthRepository
) : BaseViewModel() {

}
