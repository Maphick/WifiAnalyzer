package com.makashovadev.wifianalyzer.ui.theme

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.makashovadev.wifianalyzer.NetworkViewModel
import com.makashovadev.wifianalyzer.R
import com.makashovadev.wifianalyzer.domain.AccessPoint


@Composable
fun NetworksScreen(
    paddingValues: PaddingValues,
    onInfoClickListener: (AccessPoint) -> Unit,
    onSettingsClickListener: () -> Unit,
) {
    val viewModel: NetworkViewModel = viewModel()
    // стейт экрана: список сетей или список устройств
    val screenState = viewModel.screenState.observeAsState(NetworScreenState.Initial)
    when (val currentState = screenState.value) {

        is NetworScreenState.NetworksState -> {
            Networks(
                viewModel = viewModel,
                paddingValues = paddingValues,
                networks = currentState.networks,
                onInfoClickListener = onInfoClickListener,
                onSettingsClickListener = onSettingsClickListener
            )
        }

        is NetworScreenState.Initial -> {

        }
    }
}


//  отображение экрана со списком сетей
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Networks(
    viewModel: NetworkViewModel,
    paddingValues: PaddingValues,
    networks: List<AccessPoint>,
    onInfoClickListener: (AccessPoint) -> Unit,
    onSettingsClickListener: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    // containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                title = {
                    Text(
                        text = stringResource(R.string.wifi_analyzer),
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 26.sp
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                actions =   {
                    IconButton(onClick = {
                        onSettingsClickListener()
                    }) {
                        Icon(
                            modifier = Modifier
                                .size(56.dp),
                            imageVector = Icons.Filled.Settings,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    )
    { paddingsValues ->
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
                    onInfoClickListener = {
                        onInfoClickListener(accessPoint)
                        // открывает экран со списком усьройств, подключенных к этой сети
                        //viewModel.showClients(accessPoint)
                    })
            }
        }
    }
}
