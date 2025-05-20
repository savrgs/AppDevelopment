package com.example.todoappmvvm

data class Todo(
    val id: Int,
    val text: String,
    var isDone: Boolean = false
)
