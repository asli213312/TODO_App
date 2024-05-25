package com.example.todo_app.domain.use_case

import com.example.todo_app.domain.model.Task
import com.example.todo_app.domain.repository.TasksRepository

class GetTaskById(
    private val repository: TasksRepository
) {

    suspend operator fun invoke(id: Int): Task {
        return repository.getTaskById(id)
    }
}