package com.example.eventapp.domain.entity


data class Event(
    val id: String,
    val title: String,
    val priceRangeStart: Int?,
    val currencyKey: String?,
    val countryKey: String?,
    val organizer: Organizer?,
    val posters: List<Poster>?,
    val performances: List<Performance>?
)
