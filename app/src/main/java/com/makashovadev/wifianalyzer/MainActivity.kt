package com.makashovadev.wifianalyzer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.makashovadev.wifianalyzer.domain.AccessPoint
import com.makashovadev.wifianalyzer.domain.Client
import com.makashovadev.wifianalyzer.ui.theme.ClientsScreen
import com.makashovadev.wifianalyzer.ui.theme.HomeScreen
import com.makashovadev.wifianalyzer.ui.theme.WifiAnalyzerTheme
import com.makashovadev.wifianalyzer.utils.RootUtils

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val paddingValues: PaddingValues = PaddingValues(
            top = 16.dp, start = 8.dp, end = 8.dp, bottom = 72.dp // для нижней навигации
        )
        val isDeviceRooted = RootUtils.hasRootAccess(this)

        setContent {
            WifiAnalyzerTheme {
                val clientsState: MutableState<AccessPoint?> = remember {
                    mutableStateOf(null)
                }
                if (clientsState.value == null) {
                    HomeScreen(
                        paddingValues = paddingValues,
                        onInfoClickListener = {
                            clientsState.value = it
                        }
                    )
                } else {
                    ClientsScreen(
                        onBackPressed = {
                            clientsState.value = null
                        },
                        network = clientsState.value!!
                    )
                }
            }
        }
    }


    @Preview
    @Composable
    fun PreviewLight() {
        WifiAnalyzerTheme(darkTheme = false) {
            val paddingValues: PaddingValues = PaddingValues(
                top = 16.dp, start = 8.dp, end = 8.dp, bottom = 72.dp // для нижней навигации
            )

        }
    }

    @Preview
    @Composable
    fun PreviewDark() {
        WifiAnalyzerTheme(darkTheme = true) {
            val paddingValues: PaddingValues = PaddingValues(
                top = 16.dp, start = 8.dp, end = 8.dp, bottom = 72.dp // для нижней навигации
            )

        }
    }

}


