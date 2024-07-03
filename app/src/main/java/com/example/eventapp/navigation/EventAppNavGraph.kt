
package com.example.eventapp.navigation

import androidx.navigation.NavGraphBuilder
import com.example.eventapp.feature.eventdetails.EventDetailScreen
import com.example.eventapp.feature.eventlist.EventListScreen
import kotlinx.serialization.builtins.serializer

object EventAppNavGraph : (NavGraphBuilder) -> Unit {

    override fun invoke(builder: NavGraphBuilder) {

        NavigationCommand.Events.configure(builder) {
            EventListScreen()
        }

        NavigationCommand.EventDetails.configure(builder, String.serializer()) {
            EventDetailScreen(it)
        }
    }
}