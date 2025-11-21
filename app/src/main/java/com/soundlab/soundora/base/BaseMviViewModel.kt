package com.soundlab.soundora.base

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseMviViewModel<I: MviIntent, S: MviViewState, E: MviSingleEvent> : ViewModel() {
    private val _viewState: MutableStateFlow<S> = MutableStateFlow(initState())
    val viewState: StateFlow<S> = _viewState.asStateFlow()

    private val _event: MutableSharedFlow<E> = MutableSharedFlow()
    val event: SharedFlow<E> = _event.asSharedFlow()

    abstract fun initState(): S

    /**
     * Process user intentions
     * All business logic should be handled here
     */
    abstract fun processIntent(intent: I)

    /**
     * Update the current state
     * Should be called from processIntent implementations
     */
    protected fun updateState(newState: S) {
        _viewState.value = newState
    }

    /**
     * Update state using a reducer function
     */
    protected fun updateState(reducer: S.() -> S) {
        _viewState.value = _viewState.value.reducer()
    }

    /**
     * Send a one-time event
     */
    protected fun sendEvent(event: E) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }

    /**
     * Get current state value
     */
    protected val currentState: S
        get() = _viewState.value

    @CallSuper
    override fun onCleared() {
        super.onCleared()
    }
}