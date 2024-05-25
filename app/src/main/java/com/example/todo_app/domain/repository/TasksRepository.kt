package com.example.todo_app.domain.repository

import com.example.todo_app.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TasksRepository {

    fun getTasks(): Flow<List<Task>>

    suspend fun getTaskById(id: Int): Task

    suspend fun insertTask(task: Task)

    suspend fun deleteTask(task: Task)
}