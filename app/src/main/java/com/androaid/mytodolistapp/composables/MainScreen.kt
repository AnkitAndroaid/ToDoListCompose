package com.androaid.mytodolistapp.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.androaid.mytodolistapp.MainActivity
import com.androaid.mytodolistapp.TodoListScreen
import com.androaid.mytodolistapp.viewmodel.ToDoViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val openDialog =
        remember { mutableStateOf(false) }// for remembering the value of dialog is open or not
    val todoViewModel by remember { mutableStateOf(ToDoViewModel()) }

    Scaffold(


        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    openDialog.value = true
                },
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "ToDo Item Add")
                if (openDialog.value) {
                    FullScreenDialog(
                        openDialog = openDialog,
                        viewModel = todoViewModel,
                    )
                }

            }

        },
        topBar = { TopAppBar(title = { "MyToDo App" }) },
    ) { padding ->
        {

        }
        //body of the scafold it contains the Todo list to display

        Column(

            modifier = Modifier
                .padding(20.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TodoListScreen(todoViewModel)
        }

    }

}



