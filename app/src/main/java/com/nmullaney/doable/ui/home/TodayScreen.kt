package com.nmullaney.doable.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nmullaney.doable.R
import com.nmullaney.doable.data.Task

@Composable
fun TodayScreen(
    viewModel: TodayViewModel = viewModel()
) {
    val uiState = viewModel.uiState.collectAsState()
    Scaffold(
        floatingActionButton = { AddTaskButton {
            viewModel.addTask(Task("This is a task", "This is a description", isCompleted = false))
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Text("This is the Today Screen")
            TodoSection(uiState.value.todoTasks, onTaskClick = {
                viewModel.completeTask(it)
            })
            DoneSection(uiState.value.doneTasks)
        }
    }

}

@Composable
fun AddTaskButton(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick) {
        Icon(Icons.Filled.Add, stringResource(id = R.string.add_task))
    }
}

@Composable
fun TodoSection(todoTasks: List<Task>, onTaskClick: (Task) -> Unit = {}) {
    Text("TODO")
    todoTasks.forEach { task ->
        Text(modifier = Modifier.clickable { onTaskClick(task) }, text = task.title)
    }
}

@Composable
fun DoneSection(doneTasks: List<Task>) {
    Text("Done")
    doneTasks.forEach { task ->
        Text(task.title)
    }

}