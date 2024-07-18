package com.makashovadev.wifianalyzer.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.makashovadev.wifianalyzer.R

@Composable
fun SplashScreen() {
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Image(
                painter = painterResource(id = R.drawable.status_online),
                contentDescription = "Splash Screen",
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "ВотТакВот",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                color = colorScheme.onBackground
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Загружаем...",
            fontSize = 16.sp,
            color = colorScheme.onBackground
        )
    }
}


@Preview
@Composable
fun SplashScreenWhitePrev() {
   WifiAnalyzerTheme(
        darkTheme = false)
    {
        SplashScreen()
    }
}

@Preview
@Composable
fun SplashScreenBlackPrev() {
    WifiAnalyzerTheme(
        darkTheme = true)
    {
        SplashScreen()
    }
}
