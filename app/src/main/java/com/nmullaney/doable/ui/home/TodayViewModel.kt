package com.nmullaney.doable.ui.home

import androidx.lifecycle.ViewModel
import com.nmullaney.doable.data.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TodayViewModel: ViewModel() {
    private val todoTasks = mutableListOf<Task>()
    private val doneTasks = mutableListOf<Task>()

    private val _uiState = MutableStateFlow(TodayUiState(todoTasks, doneTasks))
    val uiState = _uiState.asStateFlow()

    fun addTask(task: Task) {
        todoTasks.add(task)
        _uiState.value = TodayUiState(todoTasks, doneTasks)
    }

    fun removeTask(task: Task) {
        todoTasks.remove(task)
        _uiState.value = TodayUiState(todoTasks, doneTasks)
    }

    fun completeTask(task: Task) {
        todoTasks.remove(task)
        doneTasks.add(task)
        _uiState.value = TodayUiState(todoTasks, doneTasks)
    }
}

class TodayUiState(
    val todoTasks: List<Task>,
    val doneTasks: List<Task>
)