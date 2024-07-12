package com.makashovadev.wifianalyzer.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.livedata.observeAsState
import com.makashovadev.wifianalyzer.MainViewModel
import com.makashovadev.wifianalyzer.domain.AccessPoint
import com.makashovadev.wifianalyzer.domain.Client


@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    paddingValues: PaddingValues
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp)
    ) {
        // стейт экрана: список сетей или список устройств
        val screenState = viewModel.screenState.observeAsState(HomeScreenState.Initial)
        val currentState = screenState.value
        when (currentState) {
            is HomeScreenState.Initial -> TODO()
            is HomeScreenState.Networks -> {
                Networks(
                    viewModel = viewModel,
                    paddingValues = paddingValues,
                    networks = currentState.networks
                )
            }

            is HomeScreenState.Clients -> {
                ClientsScreen(
                    accessPoint = currentState.network,
                    clients = currentState.clients
                )
            }

            null -> TODO()
        }
        /*
        // список точек доступа
        val accessPoints = viewModel.accessPoints.observeAsState(listOf())
        if (accessPoints.value.isNotEmpty()) {
            // список клиентов для сети
            val clients = mutableListOf<Client>().apply {
                repeat(20)
                {
                    add(
                        Client(id = it)
                    )
                }
            }

            ClientsScreen(accessPoint = accessPoints.value.get(0), clients = clients )
        }
*/
    }
}

//  отображение экрана со списком сетей
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Networks(
    viewModel: MainViewModel,
    paddingValues: PaddingValues,
    networks: List<AccessPoint>
) {
    LazyColumn(
        contentPadding = PaddingValues(
            top = 16.dp, start = 8.dp, end = 8.dp, bottom = 8.dp // для нижней навигации
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = networks, key = { it.id }) { accessPoint ->
            AccessPointCard(
                //modifier = Modifier.padding(8.dp),
                accessPoint = accessPoint,
                onInfoClickListener = {})
        }
    }
}


