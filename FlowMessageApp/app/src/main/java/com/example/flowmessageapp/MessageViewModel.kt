package com.example.flowmessageapp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MessageViewModel : ViewModel() {

    private val repository = MessageRepository()
    private val _message = MutableStateFlow("Waiting for messages...")
    val message: StateFlow<String> = _message
    private val _isStreaming = MutableStateFlow(true)
    val isStreaming: StateFlow<Boolean> = _isStreaming

    init {
        startCollecting()
    }
    private fun startCollecting() {
        viewModelScope.launch {
            repository.messageStream()
                .collect { msg ->
                    if (_isStreaming.value) {
                        _message.value = msg
                    }
                }
        }
    }
    fun toggleStream() {
        _isStreaming.value = !_isStreaming.value
        if (_isStreaming.value) {
            _message.value = "Resumed stream..."
        } else {
            _message.value = "Paused stream..."
        }
    }
}
