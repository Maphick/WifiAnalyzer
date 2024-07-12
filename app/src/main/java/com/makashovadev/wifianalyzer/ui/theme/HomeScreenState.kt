package com.makashovadev.wifianalyzer.ui.theme
import com.makashovadev.wifianalyzer.domain.AccessPoint
import com.makashovadev.wifianalyzer.domain.Client

sealed class HomeScreenState {
    // экран по дефолту
    object Initial:HomeScreenState()
    // экран со списком сетей
    data class Networks(val networks: List<AccessPoint>) : HomeScreenState()
    // экран со списком устройств
    data class Clients(val network: AccessPoint, val clients: List<Client>) : HomeScreenState()
}