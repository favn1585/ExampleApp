package com.example.eventapp.navigation

import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

class Navigator {

    private var navController: NavController? = null

    fun bindNavController(navController: NavController) {
        this.navController = navController
    }

    fun navigate(
        navigationCommand: NavigationCommand.NoArgNavigationCommand,
        popBackStack: Boolean = false,
        clearBackStack: Boolean = false
    ) = navigateInternal(navigationCommand, null, popBackStack, clearBackStack)

    fun <T> navigate(
        navigationCommand: NavigationCommand<T>,
        argument: T,
        popBackStack: Boolean = false,
        clearBackStack: Boolean = false
    ) = navigateInternal(
        navigationCommand,
        argument,
        popBackStack,
        clearBackStack
    )

    private fun <T> navigateInternal(
        navigationCommand: NavigationCommand<T>,
        argument: T?,
        popBackStack: Boolean,
        clearBackStack: Boolean
    ) {
        if (popBackStack) navController?.popBackStack()
        val destination = navigationCommand.path(argument)
        navController?.navigate(
            route = destination
        ) {
            if (clearBackStack) {
                navController?.graph?.findStartDestination()?.id?.let {
                    popUpTo(it) {
                        inclusive = true
                    }
                }
            }
        }
    }

    fun goBack() {
        navController?.popBackStack()
    }

    fun <T> goBackWithResult(key: String, value: T) {

        navController
            ?.previousBackStackEntry
            ?.savedStateHandle
            ?.set(key, value)
        navController?.popBackStack()
    }

    fun <T> observerResult(key: String): MutableLiveData<T>? {
        return navController
            ?.currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData(key)
    }
}