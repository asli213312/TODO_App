package com.example.todo_app.domain.use_case

data class TaskUseCases(
    val getTasks: GetTasks,
    val getTaskById: GetTaskById,
    val insertTask: InsertTask,
    val deleteTask: DeleteTask
)
