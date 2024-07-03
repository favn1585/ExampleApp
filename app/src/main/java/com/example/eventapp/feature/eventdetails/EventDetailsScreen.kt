package com.example.eventapp.feature.eventdetails

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventDetailScreen(name: String) {

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                text = "Event details", style = MaterialTheme.typography.displayMedium
            )
        })
    }) { innerPadding ->
        Text(modifier = Modifier.padding(innerPadding), text = name)
    }
}
