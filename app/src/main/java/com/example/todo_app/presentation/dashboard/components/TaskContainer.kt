package com.example.todo_app.presentation.dashboard.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo_app.domain.model.Task
import com.example.todo_app.presentation.dashboard.DashboardState
import com.example.todo_app.presentation.theme.ui.Primary
import kotlinx.coroutines.launch

@Composable
fun TaskContainer(
    paddingAfterTitle: Dp,
    label: String,
    state: DashboardState,
    onAddTask: (Task) -> Unit,
    onRemoveTask: (Task) -> Unit,
    onUpdateTaskById: (Int) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    val taskDescription = remember { mutableStateOf("") }

    var _currentTask:Task? = null

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = paddingAfterTitle)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.weight(1f))

            IconButton(
                onClick = {
                    val newTask = Task(
                        description = taskDescription.value,
                        isCompleted = false
                    )
                    onAddTask.invoke(newTask)
                    _currentTask = newTask

                    coroutineScope.launch {
                        listState.animateScrollToItem(state.tasks.size - 1)
                    }
                },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Transparent
                )
            ) {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = null,
                    tint = Color.Green,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize()
        ) {
            Log.d("TaskContainer", "tasks count: ${state.tasks.size}")
            items(state.tasks) { task ->
                TaskElement(
                    task,
                    onRemoveTask,
                    onUpdateDescription = {
                        onUpdateTaskById.invoke(task.id ?: 1)
                        state.currentDesc = task.description ?: ""
                        Log.d("TaskContainer", "Was updated task: ${task.description} with id ${task.id}")
                    }
                )
                Spacer(modifier = Modifier.height(18.dp))
            }
        }
    }
}

@Composable
private fun TaskElement(
    task: Task,
    onRemoveTask: (Task) -> Unit,
    onUpdateDescription: (String) -> Unit
) {
    val isEditing by remember { mutableStateOf(true) }
    var description by remember{ mutableStateOf(
        TextFieldValue(task.description)
    ) }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val state = remember { mutableStateOf(task.isCompleted) }

        CustomCheckbox(
            checked = state.value,
            onCheckedChange = {
                state.value = it
                task.isCompleted = it

                if (state.value) {
                    Log.d("TaskContainer", "Task will be deleted")
                    onRemoveTask(task)
                }

            },
            shape = RoundedCornerShape(0.dp),
            size = 20.dp
        )
        Spacer(modifier = Modifier.padding(start = 14.dp))
        if (isEditing) {
            BasicTextField(
                value = description,
                onValueChange = {
                    description = it
                    task.description = it.text

                    onUpdateDescription.invoke(it.text)
                },
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                ),
                modifier = Modifier
                    .weight(1f)
                    .onFocusChanged { focusState ->

                    }
            )
        } else {
            Text(
                text = task.description,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun CustomCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    shape: RoundedCornerShape,
    size: Dp
) : Unit {
    var isChecked by remember { mutableStateOf(checked) }

    Box(
        modifier = Modifier
            .size(size)
            .clip(shape)
            .background(if (isChecked) Primary else Color.Transparent)
            .border(1.dp, Color.Black, shape)
            .clickable {
                isChecked = !isChecked
                onCheckedChange.invoke(isChecked)
            }
    )
}