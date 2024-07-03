package com.example.eventapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import java.net.URLDecoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import java.net.URLEncoder

sealed class NavigationCommand<T>(
    private val destination: String,
    private val serializer: KSerializer<T>
) {
    object Events : NoArgNavigationCommand("events")

    data object EventDetails : NavigationCommand<String>("event_details", String.serializer())

    fun path(argument: T? = null): String {
        return with(StringBuilder(destination)) {
            append("/")
            argument?.let {
                append(encodeArgument(it))
            } ?: append("{$ARG_KEY}")
            toString()
        }
    }

    private fun encodeArgument(argument: T): String {
        return URLEncoder.encode(
            NavigatorJson.encodeToString(serializer, argument),
            Charsets.UTF_8.name()
        )
    }

    private fun hasArgument() = this !is NoArgNavigationCommand

    abstract class NoArgNavigationCommand(destination: String) :
        NavigationCommand<Unit>(destination, Unit.serializer())

    fun configure(builder: NavGraphBuilder, ui: @Composable () -> Unit) {
        builder.composable(
            route = path()
        ) {
            ui()
        }
    }

    fun configure(
        builder: NavGraphBuilder,
        serializer: KSerializer<T>,
        ui: @Composable (T) -> Unit
    ) {
        builder.composable(path()) {
            requireNotNull(
                URLDecoder.decode(it.arguments?.getString(ARG_KEY), Charsets.UTF_8.name())
            ).let { decodedString ->
                requireNotNull(
                    NavigatorJson.decodeFromString(serializer, decodedString)
                ).let { argument ->
                    ui(argument)
                }
            }
        }
    }

    companion object {
        private const val ARG_KEY = "arg"
        private val NavigatorJson = Json {
            isLenient = true
        }
    }
}