package com.makashovadev.wifianalyzer.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.makashovadev.wifianalyzer.R
import com.makashovadev.wifianalyzer.SettingsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onBackPressed: () -> Unit,
    settingsViewModel: SettingsViewModel
) {
    Settings(
        onBackPressed = onBackPressed,
        settingsViewModel = settingsViewModel
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Settings(
    onBackPressed: () -> Unit,
    settingsViewModel: SettingsViewModel
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
                        text = stringResource(id = R.string.settings),
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 26.sp
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(
                            modifier = Modifier
                                .size(56.dp),
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(it)
                //.padding(16.dp)
        ) {

            allSettings(settingsViewModel)
        }
    }
}




@Composable
fun allSettings(vm : SettingsViewModel)
{
    wifiSettings(vm)
    displaySettings(vm)
    rootSettings(vm)
}

@Composable
fun wifiSettings(
    vm : SettingsViewModel
)
{
    SettingsGroup(
        name = R.string.settings_wifi
    ) {
        SettingsNumberComp(
            name = R.string.scanning_interval,
            state = vm.scanningIntervalPreference.collectAsState() ,
            onSave =  { finalScanningInterval ->
                vm.saveScanningInterval(finalScanningInterval)
                      },
            inputFilter = { number ->
            vm.filterNumbers(number)
            },
            onCheck = {
                text ->
                vm.checkNumber(text)
            }
        )
        Divider()
        SettingsNumberComp(
            name = R.string.sorting,
            state = vm.scanningIntervalPreference.collectAsState() ,
            onSave =  { finalScanningInterval ->
                vm.saveScanningInterval(finalScanningInterval)
            },
            inputFilter = { number ->
                vm.filterNumbers(number)
            },
            onCheck = {
                    text ->
                vm.checkNumber(text)
            }
        )
    }
}

@Composable
fun displaySettings(vm : SettingsViewModel)
{
    val displaySettingsState = vm.isDisplaingSettings.observeAsState()
    SettingsGroup(name = R.string.settings_display) {
        SettingsSwitchComp(
            name = R.string.wifi_analyzer,
            state = displaySettingsState.value!!
        ) {
            vm.changeDisplaySettings()
        }
    }
}

@Composable
fun rootSettings(vm : SettingsViewModel)
{
    var isRootStatus = true
    SettingsGroup(name = R.string.settings_root) {
        SettingsCheckmarkComp(
            name = R.string.root_status,
            state = isRootStatus
        )
    }
}

@Composable
fun SettingsGroup(
    name: Int,
    // to accept only composables compatible with column
    content: @Composable ColumnScope.() -> Unit )
{
    Column(
        modifier = Modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(MaterialTheme.colorScheme.surface),
            verticalAlignment = Alignment.CenterVertically
        )
        {
        Text(
            modifier = Modifier
            .padding(
                start = 32.dp
                ),
            text = stringResource(id = name),
            textAlign =  TextAlign.Center
        )
        }
        Spacer(modifier = Modifier.height(0.dp))
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(4),
        ) {
            Column {
                content()
            }
        }
    }
}









