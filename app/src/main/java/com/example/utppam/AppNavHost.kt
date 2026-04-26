package com.example.utppam

import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavHost(
    navController: NavHostController,
    todoList: SnapshotStateList<TodoItem>
) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                todoList = todoList,
                onAddTodo = { title ->
                    val newId = if (todoList.isEmpty()) 1 else todoList.maxOf { it.id } + 1
                    todoList.add(TodoItem(newId, title))
                },
                onDeleteTodo = { todoList.remove(it) },
                onNavigateToDetail = { id ->
                    navController.navigate("detail/$id")
                }
            )
        }
        composable("detail/{todoId}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("todoId")?.toIntOrNull()
            val item = todoList.find { it.id == id }

            DetailScreen(
                item = item,
                onToggleDone = { updatedItem ->
                    val index = todoList.indexOfFirst { it.id == updatedItem.id }
                    if (index != -1) {
                        todoList[index] = updatedItem.copy(isDone = !updatedItem.isDone)
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }
    }
}