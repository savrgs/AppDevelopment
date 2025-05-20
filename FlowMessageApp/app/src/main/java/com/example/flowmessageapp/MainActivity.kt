package com.example.flowmessageapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    private val viewModel: MessageViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MessageScreen(viewModel)
        }
    }
}
@Composable
fun MessageScreen(viewModel: MessageViewModel) {
    val message by viewModel.message.collectAsState()
    val isStreaming by viewModel.isStreaming.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = message, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { viewModel.toggleStream() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7A4A2A))
        ) {
            Text(if (isStreaming) "Pause" else "Resume")
        }
    }
}
