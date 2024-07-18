package com.makashovadev.wifianalyzer.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsSelectionComp(
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





  /*          languages.forEach { language ->
                DropdownMenuItem(
                    onClick = {
                        selectedLanguage = language
                        expanded = false
                    },
                    text = {
                        Text(text = language)
                    }
                )
            }
*/