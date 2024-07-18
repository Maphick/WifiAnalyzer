package com.makashovadev.wifianalyzer.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun SettingsTextComp(
    name: Int,
    state: State<String>, // current value
    onSave: (String) -> Unit, // method to save the new value
    onCheck: (String) -> Boolean // check if new value is valid to save
) {

    // if the dialog is visible
    var isDialogShown by remember {
        mutableStateOf(false)
    }

    // conditional visibility in dependence to state
    if (isDialogShown) {
        Dialog(onDismissRequest = {
            // dismiss the dialog on touch outside
            isDialogShown = false
        }) {
            TextEditDialog(name, state, onSave, onCheck) {
                // to dismiss dialog from within
                isDialogShown = false
            }
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        onClick = {
            // clicking on the preference, will show the dialog
            isDialogShown = true
        },
    ) {
        Column()
        {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    // setting text title
                    Text(
                        text = stringResource(id = name),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Start,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    // current value shown
                    Text(
                        text = state.value,
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Start,
                    )
                }
            }
        }
    }
}



@Composable
fun SettingsNumberComp(
    name: Int,
    state: State<String>,
    onSave: (String) -> Unit,
    inputFilter: (String) -> String, // input filter for the preference
    onCheck: (String) -> Boolean
) {

    var isDialogShown by remember {
        mutableStateOf(false)
    }

    if (isDialogShown) {
        Dialog(onDismissRequest = { isDialogShown = isDialogShown.not() }) {
            /*TextEditDialog(name, state, onSave, onCheck) {
                // to dismiss dialog from within
                isDialogShown = false
            }*/

            TextEditNumberDialog(
                name,
                state,
                inputFilter,
                onSave ,
                onCheck,
                onDismiss = {
                    isDialogShown = isDialogShown.not()
                },
            )
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        onClick = {
            isDialogShown = isDialogShown.not()
        },
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(
                    horizontal = 16.dp
                )
        )
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                   // .background(MaterialTheme.colorScheme.background)
                ,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                //Column(modifier = Modifier.padding(8.dp)) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                )
                {
                    Text(
                        text = stringResource(id = name),
                        fontSize = 15.sp,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Start,
                    )
                }
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Text(
                        text = state.value,
                        fontSize = 15.sp,
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Start,
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Icon(
                        modifier = Modifier.size(15.dp),
                        imageVector = Icons.Default.ArrowForwardIos,
                        contentDescription = null
                    )
                }
                }
        }
    }
}


@Composable
fun SettingsSwitchComp(
    name: Int,
    state: Boolean,
    onClick: () -> Unit
) {
    Surface(
        color = Color.Transparent,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        onClick = onClick,
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(id = name),
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Start,
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Switch(
                    checked = state,
                        //.value,
                    onCheckedChange = { onClick() },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = MaterialTheme.colorScheme.secondary,
                        checkedTrackColor = MaterialTheme.colorScheme.onPrimary,
                        uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
                        uncheckedTrackColor = MaterialTheme.colorScheme.primary,
                    ),
                )
            }
        }
    }
}


@Composable
fun SettingsCheckmarkComp(
    name: Int,
    state: Boolean, // current value
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        onClick = {
        },
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(
                    horizontal = 16.dp
                )
        )
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                // .background(MaterialTheme.colorScheme.background)
                ,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                //Column(modifier = Modifier.padding(8.dp)) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                )
                {
                    Text(
                        text = stringResource(id = name),
                        fontSize = 15.sp,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Start,
                    )
                }
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Spacer(modifier = Modifier.width(20.dp))
                    CircleCheckBox(
                        isChecked = state,
                        color = Color.Black,
                    )
                }
            }
        }

    }
}

@Composable
fun SettingsSelectionComp_(
    onContinue: () -> Unit // Function to call when the user clicks "Continue"
) {
    var selectedTheme by remember { mutableStateOf("Light") } // Initial theme is "Light"
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Choose your theme:", style = MaterialTheme.typography.headlineLarge)

        Row {
            RadioButton(
                selected = selectedTheme == "Light",
                onClick = { selectedTheme = "Light" }
            )
            Text("Light")
        }
        Row {
            RadioButton(
                selected = selectedTheme == "Dark",
                onClick = { selectedTheme = "Dark" }
            )
            Text("Dark")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onContinue() }
        ) {
            Text("Continue")
        }
    }
}

@Composable
fun CircleCheckBox(
    isChecked: Boolean,
    modifier: Modifier = Modifier,
    //onChecked: () -> Unit = {},
    color: Color,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(CircleShape)
            .clickable {

            }
    ) {
        Icon(
            imageVector = if (isChecked) Icons.Default.CheckCircle else Icons.Outlined.Circle,
            contentDescription = null,
            tint = color
        )
    }
}