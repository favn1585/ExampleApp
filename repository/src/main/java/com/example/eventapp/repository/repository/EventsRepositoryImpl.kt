package com.example.eventapp.repository.repository

import com.example.eventapp.domain.repository.EventsRepository
import com.example.eventapp.network.repository.NetworkRepository
import javax.inject.Inject

class EventsRepositoryImpl @Inject constructor(
    private val networkRepository: NetworkRepository
) : EventsRepository {

    override suspend fun getEvents(take: Int, skip: Int) =
        networkRepository.getNetworkEvents(take = take, skip = skip)
}