package com.example.oquefazer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.oquefazer.ui.theme.OQueFazerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OQueFazerTheme {
                TaskListScreen()
            }
        }
    }
}

@Composable
fun TaskListScreen() {
    var tasks by remember { mutableStateOf(listOf("Comprar leite", "Estudar Kotlin", "Fazer exercícios")) }
    var newTask by remember { mutableStateOf("") }

    Scaffold(
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                Text(
                    text = "O Que Fazer",
                    style = MaterialTheme.typography.headlineMedium, // Atualize a tipografia aqui
                    modifier = Modifier.padding(8.dp)
                )
                TaskInput(
                    newTask = newTask,
                    onNewTaskChange = { newTask = it },
                    onAddTask = {
                        if (newTask.isNotBlank()) {
                            tasks = tasks + newTask
                            newTask = ""
                        }
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                TaskList(tasks = tasks)
            }
        }
    )
}

@Composable
fun TaskInput(
    newTask: String,
    onNewTaskChange: (String) -> Unit,
    onAddTask: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            value = newTask,
            onValueChange = onNewTaskChange,
            modifier = Modifier.weight(1f),
            placeholder = { Text("Nova tarefa") }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(onClick = onAddTask) {
            Text("Adicionar")
        }
    }
}

@Composable
fun TaskList(tasks: List<String>) {
    Column {
        tasks.forEach { task ->
            TaskItem(task)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun TaskItem(task: String) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = task)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OQueFazerTheme {
        TaskListScreen()
    }
}
