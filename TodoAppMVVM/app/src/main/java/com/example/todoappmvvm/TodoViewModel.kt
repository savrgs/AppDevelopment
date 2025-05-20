package com.example.todoappmvvm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TodoViewModel : ViewModel() {
    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos: StateFlow<List<Todo>> = _todos

    private var nextId = 0

    fun addTodo(text: String) {
        if (text.isBlank()) return
        val newTodo = Todo(id = nextId++, text = text)
        _todos.value = _todos.value + newTodo
    }

    fun toggleDone(id: Int) {
        _todos.value = _todos.value.map { todo ->
            if (todo.id == id) todo.copy(isDone = !todo.isDone) else todo
        }
    }
}
