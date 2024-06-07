package org.example.project.core.viewmodel

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

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

    val states: StateFlow<State>
        get() = _state.asStateFlow()

    val actions: SharedFlow<Action>
        get() = _action.asSharedFlow()

    abstract fun obtainEvent(event: Event)
}
