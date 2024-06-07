package org.example.project.core.viewmodel

import org.example.project.core.CommonStateFlow
import org.example.project.core.asCommonStateFlow
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.core.CommonFlow
import org.example.project.core.asCommonFlow

abstract class BaseViewModel<State : Any, Event, Action>(
    initState: State
) : KmpViewModel() {

    private val _action = MutableSharedFlow<Action>()
    private val _state = MutableStateFlow(initState)

    protected var state: State
        get() = _state.value
        set(value) {
            _state.value = value
        }

    protected var action: Action?
        get() = null
        set(value) {
            value?.let { scope.launch { _action.emit(it) }  }
        }

    val states: CommonStateFlow<State>
        get() = _state.asStateFlow().asCommonStateFlow()

    val actions: CommonFlow<Action>
        get() = _action.asSharedFlow().asCommonFlow()

    abstract fun obtainEvent(event: Event)
}
