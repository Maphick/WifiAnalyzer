package com.makashovadev.wifianalyzer.ui.theme

import com.makashovadev.wifianalyzer.domain.AccessPoint
import com.makashovadev.wifianalyzer.domain.Client

sealed class ClientsScreenState {
    object Initial : ClientsScreenState()
    data class ClientsState(
        val network: AccessPoint,
        val clients: MutableList<Client>
    ) : ClientsScreenState()
}