package com.example.todo_app.domain.use_case

import com.example.todo_app.domain.model.Task
import com.example.todo_app.domain.repository.TasksRepository
import kotlinx.coroutines.flow.Flow

class GetTasks(
    private val repository: TasksRepository
) {

    operator fun invoke(): Flow<List<Task>> {
        return repository.getTasks()
    }
}