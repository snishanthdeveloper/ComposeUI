package com.nishanth.simplecomposeui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DemoViewModel : ViewModel() {

    private val _items = MutableStateFlow((1..20).map { "Item $it" })
    val items = _items.asStateFlow()

    var isLoading by mutableStateOf(false)
        private set

    fun loadMore() {
        viewModelScope.launch {
            isLoading = true
            delay(1200) // simulate API
            val next = _items.value.size + 1

            _items.update { old ->
                old + (next until next + 20).map { "Item $it" }
            }

            isLoading = false
        }
    }
}

