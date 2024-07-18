package com.makashovadev.vknewsclient.navigation

sealed class Screen(
    val route: String
)
{
    object Home : Screen(ROUTE_HOME)
    object Clients : Screen(ROUTE_CLIENTS )
    object AccessPoints : Screen(ROUTE_ACCESS_POINTS)
    object Settings : Screen(ROUTE_SETTINGS)
    object Permission : Screen(ROUTE_PERMISSION)

    private companion object {
        const val ROUTE_HOME = "home"
        const val ROUTE_CLIENTS = "clients"
        const val ROUTE_ACCESS_POINTS = "access_points"
        const val ROUTE_SETTINGS = "settings"
        const val ROUTE_PERMISSION = "permission"
    }
}