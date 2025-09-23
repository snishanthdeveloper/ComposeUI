package com.nishanth.simplecomposeui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

// ViewModel to handle data and business logic
class HorizontalListViewModel(private val repository: ListRepository = ListRepository()) : ViewModel() {
    private val _listItems = MutableLiveData<List<ListItem>>()
    val listItems: LiveData<List<ListItem>> = _listItems

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun loadData() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val items = repository.fetchListData()
                _listItems.value = items
            } catch (e: Exception) {
                // Handle error
            } finally {
                _isLoading.value = false
            }
        }
    }
}