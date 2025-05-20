package com.example.scoreboardcomposeapp
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ScoreViewModel : ViewModel() {
    private val _teamAScore = MutableStateFlow(0)
    val teamAScore: StateFlow<Int> = _teamAScore

    private val _teamBScore = MutableStateFlow(0)
    val teamBScore: StateFlow<Int> = _teamBScore

    fun addPointsToTeamA(points: Int) {
        _teamAScore.value += points
    }
    fun addPointsToTeamB(points: Int) {
        _teamBScore.value += points
    }
    fun resetScores() {
        _teamAScore.value = 0
        _teamBScore.value = 0
    }
}
