package com.talhakasikci.odev7.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.talhakasikci.odev7.data.database.TodoDatabase
import com.talhakasikci.odev7.data.entity.Todo
import com.talhakasikci.odev7.data.repository.TodoRepository
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TodoRepository
    val allTodos: LiveData<List<Todo>>

    init {
        val todoDao = TodoDatabase.getDatabase(application).todoDao()
        repository = TodoRepository(todoDao)
        allTodos = repository.allTodos
    }

    fun searchTodos(query: String): LiveData<List<Todo>> {
        return repository.searchTodos(query)
    }

    fun insertTodo(todo: Todo) = viewModelScope.launch {
        repository.insertTodo(todo)
    }

    fun updateTodo(todo: Todo) = viewModelScope.launch {
        repository.updateTodo(todo)
    }

    fun deleteTodo(todo: Todo) = viewModelScope.launch {
        repository.deleteTodo(todo)
    }

    fun getTodoById(id: Int): LiveData<Todo> {
        return repository.getTodoById(id)
    }
} 