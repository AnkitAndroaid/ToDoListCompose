package com.androaid.mytodolistapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.androaid.mytodolistapp.composables.MainScreen
import com.androaid.mytodolistapp.ui.theme.MyTodoListAppTheme
import com.androaid.mytodolistapp.viewmodel.ToDoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTodoListAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}


@Composable
fun TodoListScreen(todoViewModel: ToDoViewModel) {


    if (todoViewModel.getAllToDoList().isEmpty())


        Text(
            text = "Nothing Here ", textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxHeight()
        )
    else {
        LazyColumn(modifier = Modifier.fillMaxHeight().padding(32.dp)) {


            items(todoViewModel.getAllToDoList()) {

                Log.e("_todoList", "" + it.isComplete)


                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Gray,
                    ),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    modifier = Modifier.padding(5.dp)
                ) {

                    Row(
                        modifier = Modifier.padding(20.dp),
                    ) {
                        Column {
                            Text(
                                it.title,
                                textDecoration =

                                if (it.isComplete) {   // check if todo item is complete then task will be show as line throgh .
                                    TextDecoration.LineThrough
                                } else {
                                    TextDecoration.None //other wise none
                                }


                            )

                            Text(
                                it.description,
                                textDecoration =

                                if (it.isComplete) {   // check if todo item is complete then task will be show as line throgh .
                                    TextDecoration.LineThrough
                                } else {
                                    TextDecoration.None //other wise none
                                }


                            )
                        }

                        Spacer(modifier = Modifier.fillMaxSize(0.7f))

                        Checkbox(
                            checked = it.isComplete, //value of the checkbox return from todo list


                            onCheckedChange = { value ->

                                todoViewModel.markAsComplete(
                                    todoItem = it,
                                    value = !value, // reverse the value because if todo is complete then false (incomplete) else true (complete).

                                )

                            },
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(

                            imageVector = Icons.Filled.Delete, contentDescription = "Delete",

                            modifier = Modifier.clickable {
                                todoViewModel.removeItem(it) // remove the todo item from list
                            },
                            tint = Color.Red
                        )


                    }
                }
            }


        }

    }
}