package com.soundlab.soundora.util.ext

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

fun <T : NavKey> NavBackStack<T>.replaceLastWith(navKey: T) {
    this.removeLastOrNull()
    this.add(navKey)
}
