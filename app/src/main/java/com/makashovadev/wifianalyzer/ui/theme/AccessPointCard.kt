package com.makashovadev.wifianalyzer.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.ScrollAxisRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.makashovadev.wifianalyzer.R
import com.makashovadev.wifianalyzer.domain.AccessPoint
import com.makashovadev.wifianalyzer.domain.Status

@Composable
fun AccessPointCard(
    modifier: Modifier = Modifier,
    accessPoint: AccessPoint, // точка доступа
    //onInfoClickListener: () -> Unit, // обработчик нажатия на кнопку "Инфо"
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            PointHeader(accessPoint)
            Row(
                modifier = Modifier
            )
            {
                Row(
                    modifier = Modifier
                        .weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PointLeft(accessPoint)
                }
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PointRight(accessPoint)
                }

            }

        }
    }
}


@Composable
fun PointHeader(
    accessPoint: AccessPoint
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
        )
        {
            Text(
                text = accessPoint.pointName,
                style = TextStyle(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                ),
            )
        }
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text(
                text = accessPoint.address,
                style = TextStyle(
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 16.sp
                ),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun PointLeft(accessPoint: AccessPoint, spacer_height: Dp = 16.dp) {
    Row()
    {
        Image(
            modifier = Modifier
                .height(67.dp)
                .width(60.dp)
                .clip(CircleShape),
            painter = painterResource(
                id = when (accessPoint.status) {
                    Status.ONLINE -> R.drawable.status_online
                    Status.OFLINE -> R.drawable.status_offline
                    else -> R.drawable.status_offline
                }
            ),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(spacer_height))
        Column(
            modifier = Modifier
        )
        {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = accessPoint.encryptionStandard,
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontSize = 16.sp
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = accessPoint.frequency,
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onTertiary,
                    fontSize = 16.sp
                )
            )
        }
    }
}

@Composable
fun PointRight(accessPoint: AccessPoint, spacer_height: Dp = 16.dp) {
    Row {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.End
        )
        {
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
            )
            {
                Text(
                    text = accessPoint.powerВbm,
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 16.sp
                    )
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = accessPoint.channel,
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 16.sp
                    )
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.End
            )
            {
                Row() {
                    Text(
                        text = accessPoint.remoteness,
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 16.sp
                        )
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewLight() {
    WifiAnalyzerTheme(darkTheme = false) {
        AccessPointCard(
            modifier = Modifier,
            accessPoint = AccessPoint(),
            onInfoClickListener = {}
        )
    }
}

@Preview
@Composable
fun PreviewDark() {
    WifiAnalyzerTheme(darkTheme = true) {
        AccessPointCard(
            modifier = Modifier,
            accessPoint = AccessPoint(),
            onInfoClickListener = {}
        )
    }
}