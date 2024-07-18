package com.makashovadev.wifianalyzer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.makashovadev.wifianalyzer.ui.theme.MainScreen
import com.makashovadev.wifianalyzer.ui.theme.WifiAnalyzerTheme
import com.makashovadev.wifianalyzer.utils.RootUtils

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isDeviceRooted = RootUtils.hasRootAccess(this)
        setContent {
            WifiAnalyzerTheme {
                MainScreen(applicationContext)
            }
        }
    }

}


