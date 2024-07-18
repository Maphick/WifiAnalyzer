package com.makashovadev.vknewsclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.makashovadev.wifianalyzer.navigation.HomeScreenNavGraph

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    accessPointsScreenContent: @Composable () -> Unit,
    clientsScreenContent: @Composable () -> Unit,
    settingsScreenContent: @Composable () -> Unit,
    permissionScreenContent: @Composable () -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route
    )
    {
        HomeScreenNavGraph(
            accessPointsScreenContent = accessPointsScreenContent,
            clientsScreenContent =  clientsScreenContent
        )
        composable(Screen.Settings.route)
        {
            settingsScreenContent()
        }
        composable(Screen.Permission.route)
        {
            permissionScreenContent()
        }

    }

}