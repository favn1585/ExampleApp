package com.example.eventapp.feature.eventlist.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.eventapp.domain.entity.Event
import com.example.eventapp.feature.eventlist.interactors.GetEventsUseCase
import javax.inject.Inject

class EventsPagingSource @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase,
) : PagingSource<Int, Event>() {

    override fun getRefreshKey(state: PagingState<Int, Event>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Event> {
        val size = params.key ?: 0

        val data = try {
            getEventsUseCase.execute(
                skip = size,
                take = PAGE_SIZE
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }

        return if (data.isSuccess) {
            LoadResult.Page(
                data = data.getOrNull() ?: emptyList(),
                prevKey = size - PAGE_SIZE,
                nextKey = size + PAGE_SIZE,
            )
        } else {
            LoadResult.Error(IllegalStateException())
        }
    }

    companion object {
        const val PAGE_SIZE = 2
    }
}