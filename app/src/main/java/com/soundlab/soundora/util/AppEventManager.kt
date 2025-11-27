package com.soundlab.soundora.util

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

object AppEventManager {
    private val _events = MutableSharedFlow<AppEvent>()
    val events: SharedFlow<AppEvent> = _events

    suspend fun sendEvent(event: AppEvent) {
        _events.emit(event)
    }
}
