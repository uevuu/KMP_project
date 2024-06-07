package org.example.project.core.viewmodel

import kotlinx.coroutines.CoroutineScope

expect abstract class KmpViewModel() {

    protected val scope: CoroutineScope
}
