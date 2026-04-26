package com.example.utppam

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    item: TodoItem?,
    onToggleDone: (TodoItem) -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Tugas") },
                navigationIcon = {
                    IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "") }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier
            .padding(padding)
            .padding(16.dp)
            .fillMaxSize()) {
            if (item != null) {
                Text(text = "Judul: ${item.title}", style = MaterialTheme.typography.titleMedium) // [cite: 37]
                Text(text = "Status: ${if (item.isDone) "Selesai" else "Belum Selesai"}") // [cite: 38]

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { onToggleDone(item) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(if (item.isDone) "Tandai Belum Selesai" else "Tandai Selesai") // [cite: 39]
                }
            } else {
                Text("Tugas tidak ditemukan")
            }
        }
    }
}