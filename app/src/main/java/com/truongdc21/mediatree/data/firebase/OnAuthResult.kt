package com.truongdc21.mediatree.data.firebase

import android.accounts.AuthenticatorException

interface OnAuthResult<T> {
    fun onSuccessful(data: T)
    fun onError(exception: AuthenticatorException?)
}
