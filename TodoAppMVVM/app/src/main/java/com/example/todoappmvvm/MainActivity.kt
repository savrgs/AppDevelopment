package com.example.todoappmvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    private val viewModel: TodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoScreen(viewModel)
        }
    }
}

@Composable
fun TodoScreen(viewModel: TodoViewModel) {
    val todos by viewModel.todos.collectAsState()
    var inputText by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("My TODOs", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Row {
            BasicTextField(
                value = inputText,
                onValueChange = { inputText = it },
                textStyle = TextStyle(fontSize = 18.sp),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
                    .border(1.dp, MaterialTheme.colorScheme.onSurface, MaterialTheme.shapes.small)
                    .padding(8.dp)
            )
            Button(onClick = {
                viewModel.addTodo(inputText)
                inputText = ""
            }) {
                Text("Add")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        todos.forEach { todo ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.toggleDone(todo.id) }
                    .padding(8.dp)
            ) {
                Text(
                    text = if (todo.isDone) "${todo.text}" else "⬜ ${todo.text}",
                    fontSize = 18.sp
                )
            }
        }
    }
}
