package com.example.todo_app.presentation.dashboard

import com.example.todo_app.domain.model.Task

sealed class TaskEvent {
    data class DeleteTask(val task: Task): TaskEvent()
    data class SaveTask(val task: Task): TaskEvent()
    data class UpdateTaskById(val id: Int): TaskEvent()
}