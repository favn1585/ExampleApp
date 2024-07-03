package com.example.eventapp.feature.eventlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.eventapp.components.EventItem
import com.example.eventapp.domain.entity.Event
import com.example.eventapp.feature.eventlist.model.EventListScreenUiAction

@Composable
fun EventListScreen() {
    val viewModel: EventListViewModel = hiltViewModel()
    val eventItems = viewModel.eventsData.collectAsLazyPagingItems()

    EventListScreenContent(
        eventItems = eventItems,
        onUiAction = viewModel::onUiAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventListScreenContent(
    eventItems: LazyPagingItems<Event>,
    onUiAction: (EventListScreenUiAction) -> Unit
) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                text = "Events", style = MaterialTheme.typography.displayMedium
            )
        })
    }) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(eventItems.itemCount, key = {
                eventItems.peek(it)?.id.orEmpty()
            }) { index ->
                eventItems[index]?.let { event ->
                    EventItem(event = event) {
                        onUiAction(EventListScreenUiAction.OnOpenDetails(event.title))
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            when (eventItems.loadState.refresh) {
                is LoadState.Error -> {
                    item {
                        Text(
                            modifier = Modifier.padding(innerPadding),
                            text = "ERROR!",
                            color = Color.Red
                        )
                    }
                }

                is LoadState.Loading -> {
                    item {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            CircularProgressIndicator(color = Color.Black)
                        }
                    }
                }

                else -> {}
            }
        }
    }
}
