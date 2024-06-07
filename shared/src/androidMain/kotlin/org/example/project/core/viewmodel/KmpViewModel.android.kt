package org.example.project.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

actual abstract class KmpViewModel : ViewModel() {

    protected actual val scope: CoroutineScope = viewModelScope
}
