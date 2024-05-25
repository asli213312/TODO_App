package com.example.todo_app.presentation.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todo_app.R
import com.example.todo_app.domain.model.Task
import com.example.todo_app.presentation.components.Circles
import com.example.todo_app.presentation.dashboard.components.TaskContainer
import com.example.todo_app.presentation.theme.ui.Background
import com.example.todo_app.presentation.theme.ui.Primary

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        Column(
            modifier = Modifier
                .background(Primary)
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(250.dp))
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
            Text(
                text = "Welcome Mary!",
                modifier = Modifier.padding(bottom = 24.dp),
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                fontSize = 25.sp
            )

            Spacer(modifier = Modifier.height(50.dp))
            Image(
                painter = painterResource(R.drawable.dashboard_clock),
                contentDescription = null,
                modifier = Modifier.size(127.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))

            Column(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(20.dp)
            ) {
                Text(
                    text = "Tasks List",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .padding(bottom = 30.dp)
            ) {
                TaskContainer(
                    paddingAfterTitle = 15.dp,
                    label = "Container",
                    state = state,
                    onAddTask = { task ->
                        viewModel.onEvent(TaskEvent.SaveTask(task))
                    },
                    onRemoveTask = {task ->
                        viewModel.onEvent(TaskEvent.DeleteTask(task))
                    },
                    onUpdateTaskById = { id ->
                        viewModel.onEvent(TaskEvent.UpdateTaskById(id))
                    }
                )
            }
        }

        Circles(0.7f, 0.5f, Color(0xFF9DF5EB))
    }
}