package com.makashovadev.wifianalyzer.domain

import com.makashovadev.wifianalyzer.R

data class AccessPoint(
    val id: Int = 0,
    val pointName: String = "РОСНЕФТЬ", // R.string.access_point_name.toString(), // имя точки досткпа
    val address: String = "fe:a7:be:c7:79:57", // R.string.address.toString(), // адрес
    val status: Status = Status.ONLINE, // статус активности
    val encryptionStandard: String = "[WPA2] [PSK]", // R.string.encryption_standard.toString(), // опция безопасности
    val frequency: String = "2427 - 2447 (20MHz)", //R.string.frequency.toString(), // частота
    val powerВbm: String= "-63dBM", // R.string.powerВbm.toString(),// уровень мощности
    val channel: String = "CH 6", // R.string.channel.toString(), // канал
    val remoteness: String = "~ 13,8m" //R.string.remoteness.toString(), // удаленность
)

