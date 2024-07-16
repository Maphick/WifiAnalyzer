package com.makashovadev.wifianalyzer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.makashovadev.wifianalyzer.domain.AccessPoint
import com.makashovadev.wifianalyzer.domain.Client
import com.makashovadev.wifianalyzer.ui.theme.ClientsScreenState
import kotlin.random.Random

class ClientsViewModel(
    network: AccessPoint
) : ViewModel() {
    private val _screenState = MutableLiveData<ClientsScreenState>(ClientsScreenState.Initial)
    val screenState: LiveData<ClientsScreenState> = _screenState

    init {
        LoadComments(network)
    }

    fun LoadComments(network: AccessPoint) {
        val clients = mutableListOf<Client>().apply {
            repeat(20) {
                var adr = Random.nextInt(200)
                var names = arrayOf(
                    "Asus",
                    "iPhone",
                    "iPhone XR",
                    "iPhone 15",
                    "iPhone 5S",
                    "Unknown",
                    "Nexus",
                    "Xiaomi Poco X6",
                    "realme C51",
                    "realme C67",
                    "Xiaomi Readme Note 13"
                )
                var name = names[Random.nextInt(names.size - 1)]
                add(
                    Client(
                        id = it, clientAddress = "10.0.2." + adr, clientName = name
                    )
                )
            }
        }
        _screenState.value = ClientsScreenState.ClientsState(
            network = network, clients = clients
        )
    }

}