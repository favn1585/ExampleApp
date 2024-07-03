package com.example.eventapp.domain.repository

import com.example.eventapp.domain.entity.Event
import kotlinx.coroutines.flow.Flow

interface EventsRepository {
    suspend fun getEvents(take: Int, skip: Int): Result<List<Event>>
}