package com.nmullaney.doable.ui.home

import androidx.lifecycle.ViewModel
import com.nmullaney.doable.data.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate

class TodayViewModel: ViewModel() {
    private val todoTasks = mutableListOf<Task>()
    private val doneTasks = mutableListOf<Task>()
    private val today = LocalDate.now()

    private val _uiState = MutableStateFlow(TodayUiState(today, todoTasks, doneTasks))
    val uiState = _uiState.asStateFlow()

    fun addTask(task: Task) {
        todoTasks.add(task)
        _uiState.value = TodayUiState(today, todoTasks, doneTasks)
    }

    fun removeTask(task: Task) {
        todoTasks.remove(task)
        _uiState.value = TodayUiState(today, todoTasks, doneTasks)
    }

    fun undoTask(task: Task) {
        doneTasks.remove(task)
        todoTasks.add(task.copy(isCompleted = false))
        _uiState.value = TodayUiState(today, todoTasks, doneTasks)
    }

    fun completeTask(task: Task) {
        todoTasks.remove(task)
        doneTasks.add(task.copy(isCompleted = true))
        _uiState.value = TodayUiState(today, todoTasks, doneTasks)
    }
}

class TodayUiState(
    val date: LocalDate,
    val todoTasks: List<Task>,
    val doneTasks: List<Task>
)