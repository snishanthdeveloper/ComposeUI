package com.nishanth.simplecomposeui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _items = MutableStateFlow(List(20) { index -> "Item ${index + 1}" })
    val items: StateFlow<List<String>> = _items.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    fun refresh() {
        // Simulate an API call and update items
        viewModelScope.launch {
            _isRefreshing.value = true
            delay(1600) // pretend network delay
            // For demo: prepend a new item showing refresh timestamp
            val newItem = "New @ ${System.currentTimeMillis()}"
            _items.value = listOf(newItem) + _items.value
            _isRefreshing.value = false
        }
    }
}
