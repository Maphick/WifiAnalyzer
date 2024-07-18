package com.makashovadev.wifianalyzer.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.makashovadev.wifianalyzer.ClientsViewModel
import com.makashovadev.wifianalyzer.ClientsViewModelFactory
import com.makashovadev.wifianalyzer.domain.AccessPoint
import com.makashovadev.wifianalyzer.domain.Client

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientsScreen(
    onBackPressed: () -> Unit,
    network: AccessPoint
) {
    val viewModel: ClientsViewModel = viewModel(
        factory = ClientsViewModelFactory(network)
    )
    val screenState = viewModel.screenState.observeAsState(ClientsScreenState.Initial)
    val currentState = screenState.value
    if (currentState is ClientsScreenState.ClientsState) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        titleContentColor = Color.Red,
                        containerColor = MaterialTheme.colorScheme.background,
                    ),
                    title = {
                        Text(
                            text = "${currentState.network.pointName}",
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
        )
        { paddingsValues ->
            LazyColumn(
                modifier = Modifier.padding(paddingsValues),
                contentPadding = PaddingValues(
                    top = 16.dp,
                    start = 8.dp,
                    end = 8.dp,
                    bottom = 16.dp
                )
            ) {
                items(
                    items = currentState.clients,
                    key = { it.id }
                )
                { client ->
                    ClientItem(client = client)
                }
            }
        }
    }
}

// отображение одного клиента
@Composable
private fun ClientItem(
    client: Client
) {
    Card(
        modifier = Modifier
            .padding(
                vertical = 4.dp
            ),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.secondary)
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(
                    //  top = 8.dp
                )
                .padding(
                    top = 16.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
        ) {
            Row(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                )
                {
                    Text(
                        text = client.clientAddress,
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 26.sp
                        ),
                    )
                }
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Text(
                        text = client.networkAddress,
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.secondary,
                            fontSize = 22.sp
                        ),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = client.clientName,
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            )
        }
    }
}

@Preview
@Composable
private fun PreviewComment() {
    WifiAnalyzerTheme {
        ClientItem(client = Client(id = 0))
    }
}