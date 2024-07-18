package com.makashovadev.wifianalyzer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.makashovadev.vknewsclient.navigation.Screen

// граф навигации домашнего экрана
// домашний экран = список сетей + список устройств
fun NavGraphBuilder.HomeScreenNavGraph(
    accessPointsScreenContent: @Composable () -> Unit,
    clientsScreenContent: @Composable () -> Unit,
) {
    navigation(
        startDestination = Screen.AccessPoints.route,
        route = Screen.Home.route
    )
    {
        composable(Screen.AccessPoints.route)
        {
            accessPointsScreenContent()
        }
        composable(Screen.Clients.route)
        {
            clientsScreenContent()
        }
    }
}