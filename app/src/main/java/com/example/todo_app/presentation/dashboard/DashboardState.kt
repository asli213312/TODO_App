package com.example.todo_app.presentation.dashboard

import com.example.todo_app.domain.model.Task

data class DashboardState(
    val tasks: List<Task> = emptyList(),
    var currentDesc: String? = ""
)
