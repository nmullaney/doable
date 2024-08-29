package com.nmullaney.doable.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nmullaney.doable.R
import com.nmullaney.doable.data.Task
import java.time.format.DateTimeFormatter

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
        Column(modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth()
            .fillMaxWidth()
            ) {
            val dateTimeFormatter = DateTimeFormatter.ofPattern("EEE LLL dd, yyyy")
            val dateString = dateTimeFormatter.format(uiState.value.date)
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .align(CenterHorizontally),
                text = dateString,
                textAlign = TextAlign.Center,
                fontSize = 18.sp)
            TodoSection(uiState.value.todoTasks, onTaskClick = {
                viewModel.completeTask(it)
            })
            DoneSection(uiState.value.doneTasks, onTaskClick = {
                viewModel.undoTask(it)
            })
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
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.LightGray)
            .padding(8.dp),
        text = stringResource(id = R.string.todo),
        fontSize = 18.sp)
    todoTasks.forEach { task ->
        TaskCard(task = task, onCheckedChange = { onTaskClick(task) })
    }
}

@Composable
fun DoneSection(doneTasks: List<Task>, onTaskClick: (Task) -> Unit = {}) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.LightGray)
            .padding(8.dp),
        text = stringResource(id = R.string.done),
        fontSize = 18.sp)
    doneTasks.forEach { task ->
        TaskCard(task = task, onCheckedChange = { onTaskClick(task) })
    }
}

@Composable
fun TaskCard(task: Task, onCheckedChange: (Boolean) -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color.White)
        .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = task.isCompleted, onCheckedChange = onCheckedChange)
        Text(task.title)
    }
}