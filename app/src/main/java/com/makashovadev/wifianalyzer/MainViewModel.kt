package com.makashovadev.wifianalyzer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.makashovadev.wifianalyzer.domain.AccessPoint
import com.makashovadev.wifianalyzer.domain.Status

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

    private val _accessPoints = MutableLiveData<List<AccessPoint>>(sourceList)
    val accessPoints: LiveData<List<AccessPoint>> = _accessPoints
}