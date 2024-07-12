package com.makashovadev.wifianalyzer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.makashovadev.wifianalyzer.ui.theme.HomeScreen
import com.makashovadev.wifianalyzer.ui.theme.WifiAnalyzerTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val paddingValues: PaddingValues = PaddingValues(
            top = 16.dp, start = 8.dp, end = 8.dp, bottom = 72.dp // для нижней навигации
        )
        setContent {
            WifiAnalyzerTheme {
                HomeScreen(
                    viewModel = viewModel,
                    paddingValues = paddingValues
                )
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
            HomeScreen(
                viewModel = viewModel,
                paddingValues = paddingValues
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
            HomeScreen(
                viewModel = viewModel,
                paddingValues = paddingValues
            )
        }
    }

}


