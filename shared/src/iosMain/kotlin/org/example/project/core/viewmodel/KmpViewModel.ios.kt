package org.example.project.core.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

actual abstract class KmpViewModel actual constructor() {

    protected actual val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    fun onCleared() {
        scope.cancel()
    }
}
