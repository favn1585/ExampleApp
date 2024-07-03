package com.example.eventapp.feature.eventlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.eventapp.domain.entity.Event
import com.example.eventapp.feature.eventlist.data.EventsPagingSource
import com.example.eventapp.feature.eventlist.data.EventsPagingSource.Companion.PAGE_SIZE
import com.example.eventapp.feature.eventlist.model.EventListScreenUiAction
import com.example.eventapp.navigation.NavigationCommand
import com.example.eventapp.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class EventListViewModel @Inject constructor(
    private val navigator: Navigator,
    private val pagingSource: EventsPagingSource
) : ViewModel() {
    val eventsData: Flow<PagingData<Event>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE),
        pagingSourceFactory = { pagingSource }
    ).flow.cachedIn(viewModelScope)

    fun onUiAction(intent: EventListScreenUiAction) {
        when (intent) {
            is EventListScreenUiAction.OnOpenDetails -> navigator.navigate(
                navigationCommand = NavigationCommand.EventDetails,
                argument = intent.name
            )
        }
    }
}
