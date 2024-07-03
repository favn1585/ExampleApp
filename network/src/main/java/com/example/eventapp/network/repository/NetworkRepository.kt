package com.example.eventapp.network.repository

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.exception.ApolloException
import com.example.eventapp.domain.entity.Event
import com.example.eventapp.network.GetEventsQuery
import com.example.eventapp.network.type.GetPublicEventListInput
import com.example.eventapp.network.utils.toEvent
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    private val apolloClient: ApolloClient
) {
    suspend fun getNetworkEvents(take: Int, skip: Int): Result<List<Event>> {
        return apolloClient.query(
            query = GetEventsQuery(
                input = GetPublicEventListInput(
                    take = Optional.present(take),
                    skip = Optional.present(skip)
                )
            )
        ).execute().run {
            if (hasErrors()) {
                Result.failure(ApolloException("Some error"))
            } else {
                Result.success(data?.getPublicEvents?.items?.map { it.toEvent() } ?: emptyList())
            }
        }
    }
}