package com.example.eventapp.network.utils

import com.example.eventapp.domain.entity.Event
import com.example.eventapp.domain.entity.Organizer
import com.example.eventapp.domain.entity.Performance
import com.example.eventapp.domain.entity.Poster
import com.example.eventapp.domain.entity.PosterLocation
import com.example.eventapp.domain.entity.PosterSize
import com.example.eventapp.network.GetEventsQuery

fun GetEventsQuery.Item.toEvent() = Event(
    id = id,
    title = "$title - $priceRangeStart $currencyKey",
    priceRangeStart = priceRangeStart?.toInt(),
    currencyKey = currencyKey.rawValue,
    countryKey = countryKey?.rawValue,
    organizer = organizer.toOrganizer(),
    posters = posters?.map { it.toPoster() },
    performances = performances?.map { it.toPerformance() }
)


fun GetEventsQuery.Organizer.toOrganizer() = Organizer(
    companyName = companyName
)

fun GetEventsQuery.Poster.toPoster() = Poster(
    id = id,
    sizes = sizes?.toSize()
)

fun GetEventsQuery.Sizes.toSize() = PosterSize(
    small = PosterLocation(small?.location)
)

fun GetEventsQuery.Performance.toPerformance() = Performance(
    title = title,
    startDate = 0,
    endDate = 0,
    timezone = timezone
)