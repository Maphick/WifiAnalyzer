package com.makashovadev.wifianalyzer

import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.livedata.observeAsState
import com.makashovadev.wifianalyzer.domain.AccessPoint
import com.makashovadev.wifianalyzer.ui.theme.AccessPointCard
import com.makashovadev.wifianalyzer.ui.theme.WifiAnalyzerTheme


@Composable
fun MainScreen(viewModel: MainViewModel)
{
    val accessPoint =  viewModel.accessPoint.observeAsState(AccessPoint())

    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(8.dp)
    ) {
        AccessPointCard(
            modifier = Modifier.padding(8.dp),
            accessPoint = accessPoint.value,


            onInfoClickListener = {})
    }
}


