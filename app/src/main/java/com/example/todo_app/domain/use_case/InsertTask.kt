package com.example.todo_app.domain.use_case

import android.util.Log
import com.example.todo_app.domain.model.Task
import com.example.todo_app.domain.repository.TasksRepository

class InsertTask(
    private val repository: TasksRepository
) {
    suspend operator fun invoke(task: Task) {
        Log.d("DashboardViewModel", "Inserting task: $task")
        repository.insertTask(task)
    }
}