package com.makashovadev.wifianalyzer.ui.theme

import com.makashovadev.wifianalyzer.domain.AccessPoint
import com.makashovadev.wifianalyzer.domain.Client

sealed class NetworScreenState {
    // экран по дефолту
    object Initial : NetworScreenState()

    // экран со списком сетей
    data class NetworksState(
        val networks: List<AccessPoint>
    ) : NetworScreenState()
}