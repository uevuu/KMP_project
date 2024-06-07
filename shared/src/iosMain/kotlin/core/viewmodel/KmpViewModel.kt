package org.example.project.core.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

actual abstract class KmpViewModel {

    protected actual val scope: CoroutineScope
        get() = CoroutineScope(SupervisorJob() + Dispatchers.Main)
}
