package com.androaid.mytodolistapp.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.androaid.mytodolistapp.datamodel.Task
import com.androaid.mytodolistapp.viewmodel.ToDoViewModel
import java.util.UUID

@Composable
fun FullScreenDialog(
    openDialog: MutableState<Boolean>,
   viewModel: ToDoViewModel
) {

    var title by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }

    AlertDialog(
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = false),

        shape = RoundedCornerShape(16.dp),
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back

            openDialog.value = false
        },
        title = {
            Text(
                text = "Add Task", modifier = Modifier.padding(20.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        },

        text = {
            Column(
                modifier = Modifier.padding(10.dp),
            ) {

                TextField(
                    value = title,
                    onValueChange = { newText ->
                        title = newText
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    shape = RoundedCornerShape(10.dp),
//                    colors = TextFieldDefaults.textFieldColors(
//                        focusedIndicatorColor = Color.Transparent,
//                        unfocusedIndicatorColor = Color.Transparent,
//                        disabledIndicatorColor = Color.Transparent
//                    )
                )
                TextField(
                    value = description,
                    onValueChange = { newText ->
                        description = newText
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    shape = RoundedCornerShape(10.dp),
//                    colors = TextFieldDefaults.textFieldColors(
//                        focusedIndicatorColor = Color.Transparent,
//                        unfocusedIndicatorColor = Color.Transparent,
//                        disabledIndicatorColor = Color.Transparent
//                    )
                )
            }

        },
        confirmButton = {
            Button(

                onClick = {
                    if (title.text.isNotEmpty()) {

//add the the todoItem on the todoList
                        viewModel.addItem(
                            Task(
                                id = UUID.randomUUID().toString(), //generate unique id
                                title = title.text,// text of the text field
                                description = description.text,
                                isComplete =  false
                            )
                        )
                        openDialog.value = false // for closing the dialog
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
            ) {
                Text(
                    "Add", color = Color.White,
                )
            }
        },
        dismissButton = {
            Button(

                onClick = {
                    openDialog.value =
                        false //close the dialog to assign the value false when Cancel button is clicked
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            ) {
                Text(
                    "Cancel", color = Color.White,
                )
            }
        }

    )

}


