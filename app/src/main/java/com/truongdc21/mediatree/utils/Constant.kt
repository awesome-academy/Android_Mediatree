package com.truongdc21.mediatree.utils

object Constant {
    const val TIME_DELAY_SPLASH = 500L
    const val REQUEST_CODE_SIGN_IN = 0
    const val EXCEPTION_NETWORK = "A network error (such as timeout, interrupted connection or unreachable host) has occurred."
    const val INTERNET_DISCONECTED = "Internet disconnected!"
}

object ConstantPreferences {

    const val dataStore = "dataStore"
    const val fisrtInstallKey = "fisrtInstalApp"
}

object ConstantDatabase {
    const val NAME_DATABASE = "songDB"
}

object ConstantFirebase {
    const val SONG_COLLECTION = "song"
    const val PLAYLIST_COLLECTION = "playlist"
    const val ARTIST_COLLECTION = "artists"
}

object ConstantMedia {
    const val NOTIFICATION_CHANNEL_ID = "music"
    const val NOTIFICATION_ID = 1
    const val KEY_INTENT_SONG = "songs"
    const val KEY_INTENT_LIST_SONG = "list_songs"
}
