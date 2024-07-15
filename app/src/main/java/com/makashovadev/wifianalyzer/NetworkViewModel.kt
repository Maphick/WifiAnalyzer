package com.makashovadev.wifianalyzer

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.makashovadev.wifianalyzer.domain.AccessPoint
import com.makashovadev.wifianalyzer.domain.Status
import com.makashovadev.wifianalyzer.ui.theme.NetworScreenState
import kotlin.random.Random

class NetworkViewModel : ViewModel() {
    private val sourceList = mutableListOf<AccessPoint>().apply {
        repeat(10) {
            var names = arrayOf(
                "РОСНЕФТЬ",
                "ЛУКОЙЛ",
                "ТатНефть",
                "SHELL",
                "BP",
                "ГАЗПРОМНЕФТЬ",
                "Подсолнух",
                "Пропан24",
                "Эверон",
                "АГЗС",
                "LPG"
            )
            var name = names[Random.nextInt(names.size - 1)]
            var status = Random.nextBoolean()
            var ap = AccessPoint(
                id = it,
                pointName = name,
                status = if (status) Status.ONLINE else Status.OFLINE,
            )
            add(ap)
        }
    }

    // исходный стейт
    private val initialState = NetworScreenState.NetworksState(networks = sourceList)

    // текущий стейт экрана
    private val _screenState = MutableLiveData<NetworScreenState>(initialState)
    val screenState: LiveData<NetworScreenState> = _screenState

}