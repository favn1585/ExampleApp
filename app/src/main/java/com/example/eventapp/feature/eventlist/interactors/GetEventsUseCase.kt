package com.example.eventapp.feature.eventlist.interactors

import com.example.eventapp.domain.repository.EventsRepository
import javax.inject.Inject

class GetEventsUseCase @Inject constructor(private val repository: EventsRepository) {
    suspend fun execute(take: Int, skip: Int) = repository.getEvents(take = take, skip = skip)
}