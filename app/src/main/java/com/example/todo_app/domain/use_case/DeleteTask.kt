package com.example.todo_app.domain.use_case

import com.example.todo_app.domain.model.Task
import com.example.todo_app.domain.repository.TasksRepository

class DeleteTask(
    private val repository: TasksRepository
) {
    suspend operator fun invoke(task: Task) {
        return repository.deleteTask(task)
    }
}