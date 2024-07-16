package com.makashovadev.wifianalyzer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.makashovadev.wifianalyzer.domain.AccessPoint

class ClientsViewModelFactory(
    private val network: AccessPoint
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ClientsViewModel(network) as T
    }

}