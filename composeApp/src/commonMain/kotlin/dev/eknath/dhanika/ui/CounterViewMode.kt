package dev.eknath.dhanika.ui

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    val count = mutableIntStateOf(1)

    fun reset() {
        count.value = 0
    }
}