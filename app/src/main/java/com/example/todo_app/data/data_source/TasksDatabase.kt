package com.example.todo_app.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todo_app.domain.model.Task

@Database(
    entities = [Task::class],
    version = 1
)
abstract class TasksDatabase : RoomDatabase() {

    abstract val dao: TasksDao

    companion object {
        const val DATABASE_NAME = "tasks_db"
    }
}