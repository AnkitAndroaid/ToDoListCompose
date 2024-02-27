package com.androaid.mytodolistapp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.androaid.mytodolistapp.datamodel.Task

class ToDoViewModel : ViewModel() {
    private var _todoList = mutableStateListOf<Task>()
    var todoList: SnapshotStateList<Task> = _todoList


    fun addItem(task: Task) {
        _todoList.add(task)
    }

    fun removeItem(task: Task) {
        _todoList.remove(task)
    }

    fun getAllToDoList(): List<Task> {
        return _todoList;
    }

    fun markAsComplete(todoItem: Task, value: Boolean) {


        val index = _todoList.indexOf(todoItem);

        _todoList[index] = _todoList[index].let {
            it.copy(
                id = it.id,
                title = it.title,
                isComplete = value
            )

        }

    }

}