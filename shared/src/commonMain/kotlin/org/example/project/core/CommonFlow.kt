package org.example.project.core

import io.ktor.utils.io.core.Closeable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.experimental.ExperimentalObjCRefinement
import kotlin.native.HiddenFromObjC

@OptIn(ExperimentalObjCRefinement::class)
@HiddenFromObjC
fun <T> SharedFlow<T>.asCommonFlow() = CommonFlow(this)

@OptIn(ExperimentalObjCRefinement::class)
@HiddenFromObjC
fun <T> StateFlow<T>.asCommonStateFlow() = CommonStateFlow(this)

class CommonFlow<T>(private val origin: Flow<T>) : Flow<T> by origin {

    fun watch(block: (T) -> Unit): Closeable = watchFlow(block)
}

class CommonStateFlow<T>(private val origin: StateFlow<T>) : StateFlow<T> by origin {
    fun watch(block: (T) -> Unit): Closeable = watchFlow(block)
}

private fun <T> Flow<T>.watchFlow(block: (T) -> Unit): Closeable {
    val context = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    onEach(block).launchIn(context)
    return object : Closeable {
        override fun close() {
            context.cancel()
        }
    }
}
