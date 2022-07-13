package com.truongdc21.mediatree.data.model

import android.net.Uri

data class User (
    val userId : Int?,
    val userEmail : String?,
    val userName : String?,
    val imageUri : Uri?
)
