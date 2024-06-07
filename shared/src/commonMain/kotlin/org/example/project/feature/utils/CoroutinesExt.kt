package org.example.project.feature.utils

import kotlinx.coroutines.CancellationException

inline fun <T> runSuspendCatching(block: () -> T) = try {
    Result.success(block())
} catch (e: CancellationException) {
    throw e
} catch (e: Exception) {
    Result.failure(e)
}
