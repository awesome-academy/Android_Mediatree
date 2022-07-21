package com.truongdc21.mediatree.data.model

import android.net.Uri

data class User (
    val userId : String?,
    val userEmail : String?,
    val userName : String?,
    val userImageUri : Uri?
)
