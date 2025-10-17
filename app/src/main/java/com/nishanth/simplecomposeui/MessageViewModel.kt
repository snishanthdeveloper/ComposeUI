package com.nishanth.simplecomposeui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.runtime.State


class MessageViewModel : ViewModel() {

    private val _messages = mutableStateOf(generateMessages())
    val messages: State<Map<String, List<Message>>> = _messages

    fun refresh() {
        viewModelScope.launch {
            delay(1000) // simulate network call
            val newMessage = Message(
                id = (0..9999).random(),
                text = "New message added at ${System.currentTimeMillis()}",
                date = "Today"
            )

            val updated = _messages.value.toMutableMap()
            val todayList = updated["Today"]?.toMutableList() ?: mutableListOf()
            todayList.add(0, newMessage)
            updated["Today"] = todayList

            _messages.value = updated
        }
    }

    fun generateMessages(): Map<String, List<Message>> {
        return mapOf(
            "Today" to listOf(
                Message(1, "Compose makes UI fun!", "Today"),
                Message(2, "Sticky headers rock 🚀", "Today"),
            ),
            "Yesterday" to listOf(
                Message(3, "Learning animations", "Yesterday"),
                Message(4, "Pull-to-refresh added ✅", "Yesterday")
            ),
            "Last Week" to listOf(
                Message(5, "Exploring MotionLayout", "Last Week"),
                Message(6, "Dark mode support", "Last Week")
            )
        )
    }

}




