package com.nishanth.simplecomposeui

import kotlinx.coroutines.delay

// Repository class for API calls
class ListRepository {
    suspend fun fetchListData(): List<ListItem> {
        // Simulate API call with delay
        delay(1000)
        return listOf(
            ListItem("1", "Item 1", "Description 1", "https://picsum.photos/200/150?random=1"),
            ListItem("2", "Item 2", "Description 2", "https://picsum.photos/200/150?random=2"),
            ListItem("3", "Item 3", "Description 3", "https://picsum.photos/200/150?random=3"),
            ListItem("4", "Item 4", "Description 4", "https://picsum.photos/200/150?random=4"),
            ListItem("5", "Item 5", "Description 5", "https://picsum.photos/200/150?random=5")
        )
    }
}