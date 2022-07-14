package com.truongdc21.mediatree.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.first

suspend fun DataStore<Preferences>.savePreferencesFirstInstall(value: Boolean){
    val dataStoreKey = booleanPreferencesKey(ConstantPreferences.fisrtInstallKey)
    this.edit { data ->
        data[dataStoreKey] = value
    }
}

suspend fun DataStore<Preferences>.readPreferencesFirstInstall(): Boolean? {
    val dataStoreKey = booleanPreferencesKey(ConstantPreferences.fisrtInstallKey)
    val preferences = this.data.first()
    return preferences[dataStoreKey]
}
