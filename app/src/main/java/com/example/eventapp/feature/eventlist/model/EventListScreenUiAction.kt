package com.example.eventapp.feature.eventlist.model

sealed class EventListScreenUiAction {
    data class OnOpenDetails(val name: String) : EventListScreenUiAction()
}
