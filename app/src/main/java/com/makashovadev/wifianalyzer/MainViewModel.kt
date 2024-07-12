package com.makashovadev.wifianalyzer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.makashovadev.wifianalyzer.domain.AccessPoint
import com.makashovadev.wifianalyzer.domain.Status
import com.makashovadev.wifianalyzer.ui.theme.HomeScreenState

class MainViewModel: ViewModel() {
    private val sourceList = mutableListOf<AccessPoint>().apply {
        repeat(10) {
            var ap = AccessPoint(
                id = it,
                pointName = if (it %2 == 0) "РОСНЕФТЬ" else "ЛУКОЙЛ",
                status = if (it %2 == 0) Status.ONLINE else Status.OFLINE,
            )
            add(ap)
        }
    }

    // исходный стейт
    private val initialState =  HomeScreenState.Networks(networks = sourceList)
    // текущий стейт экрана
    private val _screenState = MutableLiveData<HomeScreenState>(initialState)
    val screenState: LiveData<HomeScreenState> = _screenState
}