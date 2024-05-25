package com.example.todo_app.di

import android.app.Application
import androidx.room.Room
import com.example.todo_app.data.data_source.TasksDatabase
import com.example.todo_app.data.repository.TasksRepositoryImpl
import com.example.todo_app.domain.repository.TasksRepository
import com.example.todo_app.domain.use_case.DeleteTask
import com.example.todo_app.domain.use_case.GetTaskById
import com.example.todo_app.domain.use_case.GetTasks
import com.example.todo_app.domain.use_case.InsertTask
import com.example.todo_app.domain.use_case.TaskUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesTasksDatabase(app: Application) : TasksDatabase {
        return Room.databaseBuilder(
            app,
            TasksDatabase::class.java,
            TasksDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesTaskRepository(db: TasksDatabase): TasksRepository {
        return TasksRepositoryImpl(db.dao)
    }

    @Provides
    @Singleton
    fun providesTaskUseCases(repository: TasksRepository): TaskUseCases {
        return TaskUseCases(
            getTaskById = GetTaskById(repository),
            insertTask = InsertTask(repository),
            deleteTask = DeleteTask(repository),
            getTasks = GetTasks(repository)
        )
    }
}