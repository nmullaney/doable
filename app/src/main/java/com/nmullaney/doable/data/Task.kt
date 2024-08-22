package com.nmullaney.doable.data

data class Task(
    val title: String,
    val description: String,
    val isCompleted: Boolean = false
)
