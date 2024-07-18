package com.makashovadev.wifianalyzer.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.makashovadev.vknewsclient.navigation.AppNavGraph
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import com.makashovadev.vknewsclient.navigation.Screen
import com.makashovadev.vknewsclient.navigation.rememberNavigationState
import com.makashovadev.wifianalyzer.domain.AccessPoint

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState()

    // для сохранения состояния при переходе на экран со списком клиентов и обратно
    val clientsState: MutableState<AccessPoint?> = remember {
        mutableStateOf(null)
    }

    Scaffold(bottomBar = {
    }) { paddingValues ->
        AppNavGraph(navHostController = navigationState.navHostController,
            accessPointsScreenContent = {
                NetworksScreen(
                    paddingValues = paddingValues,
                    onInfoClickListener = {
                        clientsState.value = it
                        navigationState.navigateToClients()
                    },
                    onSettingsClickListener = {
                        navigationState.navigateTo(Screen.Settings.route)
                    }
                )

            },
            clientsScreenContent = {
                ClientsScreen(
                    onBackPressed = {
                        //  интуитивное поведение приложения:
                        // если пользователь кликает назад - закрыть данный экран
                        navigationState.navHostController.popBackStack()
                    },
                    network = clientsState.value!!
                )
            },
            settingsScreenContent = {
                SettingsScreen(
                    onBackPressed = {
                        navigationState.navHostController.popBackStack()
                    }
                        )
            },
            permissionScreenContent = { Text(text = "Permission Screen") })
    }
}




