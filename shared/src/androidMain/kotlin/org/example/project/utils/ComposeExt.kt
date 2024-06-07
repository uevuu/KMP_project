package org.example.project.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisallowComposableCalls
import androidx.compose.runtime.remember

/**
 * @sample `val onViewClick = rememberClick { showView = false }`
 */
@Composable
inline fun rememberClick(crossinline block: @DisallowComposableCalls () -> Unit): () -> Unit = remember {
    {
        block()
    }
}

/**
 * @sample `val onViewClick = rememberClick { showView = it }`
 */
@Composable
inline fun <T> rememberClick(crossinline block: @DisallowComposableCalls (T) -> Unit): (T) -> Unit = remember {
    { arg1 ->
        block(arg1)
    }
}
