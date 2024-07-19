package com.makashovadev.wifianalyzer.ui.theme

import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.wallet.button.ButtonConstants
import com.makashovadev.wifianalyzer.NetworkViewModel
import com.makashovadev.wifianalyzer.R
import com.makashovadev.wifianalyzer.SettingsViewModel
import com.makashovadev.wifianalyzer.domain.AccessPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun NetworksScreen(
    settingsViewModel: SettingsViewModel,
    onInfoClickListener: (AccessPoint) -> Unit,
    onSettingsClickListener: () -> Unit,
    nextScanAction: () -> Unit,
) {
    val viewModel: NetworkViewModel = viewModel()
    // стейт экрана: список сетей или список устройств
    val screenState = viewModel.screenState.observeAsState(NetworScreenState.Initial)
    when (val currentState = screenState.value) {

        is NetworScreenState.NetworksState -> {
            Networks(
                settingsViewModel = settingsViewModel,
                viewModel = viewModel,
                networks = currentState.networks,
                onInfoClickListener = onInfoClickListener,
                onSettingsClickListener = onSettingsClickListener,
                nextScanAction = nextScanAction
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
    settingsViewModel: SettingsViewModel,
    viewModel: NetworkViewModel,
    networks: List<AccessPoint>,
    onInfoClickListener: (AccessPoint) -> Unit,
    onSettingsClickListener: () -> Unit,
    nextScanAction: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    titleContentColor = Color.Red,
                    containerColor = MaterialTheme.colorScheme.background,
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
                actions = {
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
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            // идет ли процесс обновления списка сетей
            var isRefreshing by remember {
                mutableStateOf(false)
            }
            //  идет ли сканирование по таймеру в целом
            var isPaused by remember {
                mutableStateOf(true)
            }
            // состояние кнопки старт/стоп
            var isStartButton by remember {
                mutableStateOf(false)
            }

            val scope = rememberCoroutineScope()

            val nextScanTime: Int =
                settingsViewModel.scanningIntervalPreference.value.toIntOrNull() ?: 0

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                CountdownTimer(
                    // состояние кнопки: является кнопка Start или Stop кнопкой
                    isStartButton = isStartButton,
                    // при нажатии на кнопку страт/стоп
                    onStopScanning = {
                        if (!isRefreshing) {
                            isStartButton = !isStartButton
                            isPaused = !isPaused
                            if (!isPaused)
                                isRefreshing = true
                        }
                    },
                    // происходит ли сейчас обновление списка сетей
                    isRefreshing = isRefreshing,
                    // остановлен ли процесс сканирования
                    paused = isPaused,
                    // таймер
                    maxCount = nextScanTime,
                    // что происходит при обнулении таймера
                    nextScanAction = {
                        // идёт процесс обновления списка сетей
                        isRefreshing = true
                    }
                )



                PullToRefreshLazyColumn(
                    networks = networks,
                    isRefreshing = isRefreshing,
                    onRefresh = {
                        scope.launch {
                            isPaused = true
                            isRefreshing = true
                            isStartButton = true
                            // вызов функции для обновления списка сетей
                            nextScanAction()
                            delay(3000L)
                            isPaused = false
                            isRefreshing = false
                        }
                    },
                    onInfoClickListener = {
                        // открывает экран со списком устройств, подключенных к этой сети
                        onInfoClickListener(it)
                    })
            }
        }
    }
}


//
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CountdownTimer(
    isRefreshing: Boolean,
    paused: Boolean,
    isStartButton: Boolean,
    onStopScanning: () -> Unit,
    maxCount: Int = 60,
    nextScanAction: () -> Unit
) {

    // остаточное время
    var timeLeft by remember(maxCount) { mutableStateOf(maxCount) }
    // отображение оставшегося времени
    var scanText by remember { mutableStateOf("") }

    fun resetTimer() {
        timeLeft = maxCount
    }

    LaunchedEffect(key1 = timeLeft, key2 = paused) {
        while (timeLeft > 0 && !paused && !isRefreshing) {
            scanText = "NEXT SCAN: $timeLeft seconds"
            delay(1000L)
            timeLeft--
        }
        // когда время истекло
        if (timeLeft == 0) {
            // колбек наверх
            nextScanAction()
        }
        //  обнуление таймера при остановки сканирования
        if (paused)
            resetTimer()
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(
                top = 50.dp,
            ),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Row(
            modifier = Modifier
                .weight(1f)
        )
        {
            StopScanning(
                isStartButton = isStartButton,
                onClick = {
                    scanText = ""
                    onStopScanning()
                }
            )
        }
        Row(
            modifier = Modifier
                .padding(
                    top = 16.dp,
                    end = 32.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            if (!paused)
                Text(
                    text = scanText,
                    textAlign = TextAlign.Center
                )
        }

    }
}

@ExperimentalAnimationApi
@Composable
fun StopScanning(
    isStartButton: Boolean,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .width(200.dp)
            .height(60.dp)
            .padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp,
            ),
        onClick =
        {
            onClick(

            )
        },
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.error,
            contentColor = MaterialTheme.colorScheme.background
        )
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = if (isStartButton) "Stop Scanning" else "Start Scanning",
            fontWeight = FontWeight.Bold,
            color = colorScheme.background,
            textAlign = TextAlign.Center,  // horizontal center of the text
        )
    }
}