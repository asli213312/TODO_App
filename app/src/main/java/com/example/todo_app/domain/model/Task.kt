package com.example.todo_app.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    var description: String = "",
    var isCompleted: Boolean = false,
    @PrimaryKey val id: Int? = null
)
