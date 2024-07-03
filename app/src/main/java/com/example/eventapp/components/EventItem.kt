package com.example.eventapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.eventapp.domain.entity.Event
import com.example.eventapp.domain.entity.Organizer
import com.example.eventapp.domain.entity.Poster
import com.example.eventapp.domain.entity.PosterLocation
import com.example.eventapp.domain.entity.PosterSize
import com.example.eventapp.theme.EventAppTheme

@Composable
fun EventItem(
    event: Event,
    onClick: (Event) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(event) },
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column {
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(event.posters?.firstOrNull()?.sizes?.small?.location)
                    .crossfade(true)
                    .build()
            )
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .height(200.dp)
                    .padding(top = 4.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = event.title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp)
            )
            event.organizer?.companyName?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EventItemPreview() {
    EventAppTheme {
        EventItem(
            Event(
                id = "1",
                title = "Sample Event",
                priceRangeStart = 0,
                currencyKey = "USD",
                countryKey = "US",
                organizer = Organizer("Sample Organizer"),
                posters = listOf(
                    Poster(
                        "1",
                        PosterSize(PosterLocation("https://us-east1-testing-cnt.cinewav.com/image/2022/46/resized/114947/small-0b388fd8-f402-46f0-aed7-909c96a101e5.jpg"))
                    )
                ),
                performances = listOf()
            )
        ) {}
    }
}