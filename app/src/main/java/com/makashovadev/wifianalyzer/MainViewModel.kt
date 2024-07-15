package com.makashovadev.wifianalyzer

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.makashovadev.wifianalyzer.domain.AccessPoint
import com.makashovadev.wifianalyzer.domain.Client
import com.makashovadev.wifianalyzer.domain.Status
import com.makashovadev.wifianalyzer.ui.theme.HomeScreenState
import kotlin.random.Random

class MainViewModel: ViewModel() {
    private  val clients = mutableListOf<Client>().apply {
        repeat(20){
            var adr = Random.nextInt(200)
            var names = arrayOf("Asus", "iPhone", "iPhone XR","iPhone 15","iPhone 5S","Unknown",
                "Nexus", "Xiaomi Poco X6", "realme C51", "realme C67", "Xiaomi Readme Note 13")
            var name = names[Random.nextInt(names.size-1)]
            add(
                Client(
                    id = it,
                    clientAddress = "10.0.2." + adr,
                    clientName = name
                ))
        }
    }

    private val sourceList = mutableListOf<AccessPoint>().apply {
        repeat(10) {
            var names = arrayOf("РОСНЕФТЬ", "ЛУКОЙЛ", "ТатНефть","SHELL","BP","ГАЗПРОМНЕФТЬ",
                "Подсолнух", "Пропан24", "Эверон", "АГЗС", "LPG")
            var name = names[Random.nextInt(names.size-1)]
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
    private val initialState =  HomeScreenState.Networks(networks = sourceList)
    // текущий стейт экрана
    private val _screenState = MutableLiveData<HomeScreenState>(initialState)
    val screenState: LiveData<HomeScreenState> = _screenState

    // хранит состояние экрана
    private  var savedState: HomeScreenState? = initialState
    // функция открытия экрана с подкюченными устройствами
    fun showClients(accessPoint: AccessPoint)
    {
        // сохраняет состояние списка сетей до перехода на экран с клиентами
        savedState = _screenState.value
        _screenState.value = HomeScreenState.Clients(network = accessPoint, clients = clients)
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun closeComments()
    {
        // восстанавливает состояние экрана при возврате на экран со списком сетей
        _screenState.value = savedState
    }


}