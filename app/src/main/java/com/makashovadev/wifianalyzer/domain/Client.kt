package com.makashovadev.wifianalyzer.domain

data class Client(
    val id: Int,
    val clientName: String = "Asus",
    val clientAddress: String = "10.0.2.115",
    val networkAddress: String = "fe:a7:be:c7:79:57"
)
