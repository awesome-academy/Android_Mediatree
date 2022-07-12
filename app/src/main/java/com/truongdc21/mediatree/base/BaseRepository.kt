package com.truongdc21.mediatree.base

import com.truongdc21.mediatree.utils.DataResult
import com.truongdc21.mediatree.utils.dispatcher.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

abstract class BaseRepository  {

     @Inject
     @IoDispatcher
     lateinit var dispatcherIo : CoroutineDispatcher

    protected suspend fun <R> withContextResult(
        dispatcherContextIO: CoroutineContext = dispatcherIo,
        requestBlock: suspend CoroutineScope.() -> R
    ): DataResult<R> = withContext(dispatcherContextIO) {
        return@withContext try {
            val response = requestBlock()
            DataResult.Success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            DataResult.Error(e)
        }
    }
}
