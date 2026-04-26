package com.example.utppam

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    todoList: List<TodoItem>,
    onAddTodo: (String) -> Unit,
    onDeleteTodo: (TodoItem) -> Unit,
    onNavigateToDetail: (Int) -> Unit
) {
    var textState by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .systemBarsPadding()
        .padding(16.dp)) {
        Text(text = "My To-Do List", style = MaterialTheme.typography.headlineMedium)

        Row(Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
            TextField(
                value = textState,
                onValueChange = { textState = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Tambah tugas baru...") }
            )
            Button(onClick = {
                if (textState.isNotBlank()) {
                    onAddTodo(textState)
                    textState = ""
                }
            }, modifier = Modifier.padding(start = 8.dp)) {
                Text("Tambah")
            }
        }

        // List Section [cite: 24]
        LazyColumn {
            items(todoList) { item ->
                TodoRow(
                    item = item,
                    onDelete = { onDeleteTodo(item) },
                    onClick = { onNavigateToDetail(item.id) }
                )
            }
        }
    }
}

@Composable
fun TodoRow(item: TodoItem, onDelete: () -> Unit, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp).clickable { onClick() }
    ) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text(text = item.title, style = MaterialTheme.typography.bodyLarge) // [cite: 26]
                    Text(
                        text = if (item.isDone) "Selesai" else "Belum Selesai", // Tambahkan ':' di sini jika tadi tertinggal
                        style = MaterialTheme.typography.labelSmall,
                        color = if (item.isDone) Color.Green else Color.Red
                    )
            }
            IconButton(onClick = onDelete) { // [cite: 33, 34]
                Icon(Icons.Default.Delete, contentDescription = "Hapus")
            }
        }
    }
}