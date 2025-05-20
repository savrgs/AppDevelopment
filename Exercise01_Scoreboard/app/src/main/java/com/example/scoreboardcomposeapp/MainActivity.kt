package com.example.scoreboardcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: ScoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScoreboardScreen(viewModel)
        }
    }
}
@Composable
fun ScoreboardScreen(viewModel: ScoreViewModel = viewModel()) {
    val teamAScore by viewModel.teamAScore.collectAsState()
    val teamBScore by viewModel.teamBScore.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Team A: $teamAScore", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { viewModel.addPointsToTeamA(1) }) {
            Text("+1 Team A")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Team B: $teamBScore", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { viewModel.addPointsToTeamB(1) }) {
            Text("+1 Team B")
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = { viewModel.resetScores() }) {
            Text("Reset Scores")
        }
    }
}
