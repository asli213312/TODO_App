package com.example.todo_app.data.repository

import com.example.todo_app.data.data_source.TasksDao
import com.example.todo_app.domain.model.Task
import com.example.todo_app.domain.repository.TasksRepository
import kotlinx.coroutines.flow.Flow

class TasksRepositoryImpl(
    private val dao: TasksDao
) : TasksRepository {

    override fun getTasks(): Flow<List<Task>> {
        return dao.getTasks()
    }

    override suspend fun getTaskById(id: Int): Task {
        return dao.getTaskById(id)
    }

    override suspend fun insertTask(task: Task) {
        dao.insertTask(task)
    }

    override suspend fun deleteTask(task: Task) {
        dao.deleteTask(task)
    }
}