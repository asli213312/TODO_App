package com.example.todo_app.presentation.dashboard

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo_app.domain.model.Task
import com.example.todo_app.domain.use_case.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    val taskUseCases: TaskUseCases
) : ViewModel() {

    private val _state = mutableStateOf(DashboardState())
    val state: State<DashboardState> = _state

    private var getTasksJob: Job? = null

    private var _currentTaskId: Int = 1

    init {
        getTasks()
    }

    fun onEvent(event: TaskEvent) {
        when(event) {
            is TaskEvent.DeleteTask -> {
                viewModelScope.launch {
                    delay(1500)
                    taskUseCases.deleteTask(event.task)
                }
            }

            is TaskEvent.SaveTask -> {
                viewModelScope.launch {
                    _currentTaskId += 1
                    taskUseCases.insertTask(event.task)
                    Log.d("DashboardViewModel", "new task with id: $_currentTaskId")
                }
            }

            is TaskEvent.UpdateTaskById -> {
                viewModelScope.launch {
                    val task = taskUseCases.getTaskById(event.id)
                    task.let { it.description = _state.value.currentDesc ?: "" }
                    Log.d("DashboardViewModel", "Task to update with id: ${task.id} and desc ${task.description}")
                    taskUseCases.insertTask(task)
                }
            }
        }
    }

    private fun getTasks() {
        getTasksJob?.cancel()
        getTasksJob = taskUseCases.getTasks()
            .onEach { tasks ->
                Log.d("DashboardViewModel", "Getting tasks count: ${tasks.size}")
                _state.value = state.value.copy(
                    tasks = tasks
                )
            }.launchIn(viewModelScope)
    }
}