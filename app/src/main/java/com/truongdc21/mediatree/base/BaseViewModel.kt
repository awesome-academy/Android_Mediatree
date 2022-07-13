package com.truongdc21.mediatree.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuthException
import com.truongdc21.mediatree.utils.Constant
import com.truongdc21.mediatree.utils.DataResult
import com.truongdc21.mediatree.utils.exception.convertAuthExceptionToString
import com.truongdc21.mediatree.utils.livedata.SingleLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    val isLoading = SingleLiveData<Boolean>()
    val errorExceptionFirebase = SingleLiveData<String>()
    private var loadingCount = 0

    protected fun <T> launchTaskSync(
        onRequest: suspend CoroutineScope.() -> DataResult<T>,
        onSuccess: (T) -> Unit = {},
        onError: (Exception) -> Unit = {},
        isShowLoading: Boolean = true
    ) = viewModelScope.launch() {
        showLoading(isShowLoading)
        when (val asynchronousTasks = onRequest(this)) {
            is DataResult.Success -> onSuccess(asynchronousTasks.data)
            is DataResult.Error -> {
                onError(asynchronousTasks.exception)
            }
        }
        hideLoading(isShowLoading)
    }

    protected fun <T> launchTaskSyncAuthFirebase(
        onRequest: suspend CoroutineScope.() -> DataResult<T>,
        onSuccess: () -> Unit = {},
        onError: (Exception) -> Unit = {},
        isShowLoading: Boolean = true
    ) = viewModelScope.launch {
        showLoading(isShowLoading)
        when (val asynchronousTasks = onRequest(this)) {
            is DataResult.Success -> {
                val authResult = asynchronousTasks.data as Task<AuthResult>
                authResult.addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        onSuccess()
                    }else{
                        task.exception?.let { exceptionAuth ->
                            onError(exceptionAuth)
                            if (exceptionAuth.message == Constant.EXCEPTION_NETWORK){
                                errorExceptionFirebase.value = Constant.INTERNET_DISCONECTED
                            }else {
                                val errorCode = (exceptionAuth as FirebaseAuthException?)?.errorCode
                                errorExceptionFirebase.value = errorCode?.convertAuthExceptionToString()
                            }
                        }
                    }
                }
            }
        }
        hideLoading(isShowLoading)
    }

    private fun showLoading(isShowLoading: Boolean) {
        if (!isShowLoading) return
        loadingCount++
        if (isLoading.value != true) isLoading.value = true
    }

    private fun hideLoading(isShowLoading: Boolean) {
        if (!isShowLoading) return
        loadingCount--
        if (loadingCount <= 0) {
            loadingCount = 0
            isLoading.value = false
        }
    }
}
