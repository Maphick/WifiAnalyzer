package com.makashovadev.wifianalyzer

import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.livedata.observeAsState
import com.makashovadev.wifianalyzer.domain.AccessPoint
import com.makashovadev.wifianalyzer.ui.theme.AccessPointCard
import com.makashovadev.wifianalyzer.ui.theme.WifiAnalyzerTheme


@Composable
fun MainScreen(viewModel: MainViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp)
    ) {
        val accessPoints = viewModel.accessPoints.observeAsState(listOf())

        LazyColumn(
            contentPadding = PaddingValues(
                top = 16.dp, start = 8.dp, end = 8.dp, bottom = 8.dp // для нижней навигации
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = accessPoints.value, key = { it.id }) { accessPoint ->
                AccessPointCard(
                    //modifier = Modifier.padding(8.dp),
                    accessPoint = accessPoint,
                    onInfoClickListener = {})
            }
        }
    }
}


