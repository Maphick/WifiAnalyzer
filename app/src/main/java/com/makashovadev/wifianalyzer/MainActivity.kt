package com.makashovadev.wifianalyzer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.makashovadev.wifianalyzer.ui.theme.WifiAnalyzerTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WifiAnalyzerTheme {
                    MainScreen(viewModel)
                }
            }
        }

    /*
    @Preview
    @Composable
    fun PreviewLight() {
        WifiAnalyzerTheme(darkTheme = false) {
            val viewModel by viewModels<MainViewModel>()
            MainScreen(viewModel)
        }
    }

    @Preview
    @Composable
    fun PreviewDark() {
        WifiAnalyzerTheme(darkTheme = true) {
            MainScreen(viewModel)
        }
    }
    */
    }

