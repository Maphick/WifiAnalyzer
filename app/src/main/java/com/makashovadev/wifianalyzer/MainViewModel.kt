package com.makashovadev.wifianalyzer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.makashovadev.wifianalyzer.domain.AccessPoint

class MainViewModel: ViewModel() {
    private val _accessPoint = MutableLiveData(AccessPoint())
    val accessPoint: LiveData<AccessPoint> = _accessPoint
}