package com.talhakasikci.odev7.data.repository

import androidx.lifecycle.LiveData
import com.talhakasikci.odev7.data.dao.TodoDao
import com.talhakasikci.odev7.data.entity.Todo

class TodoRepository(private val todoDao: TodoDao) {
    val allTodos: LiveData<List<Todo>> = todoDao.getAllTodos()

    fun searchTodos(query: String): LiveData<List<Todo>> {
        return todoDao.searchTodos(query)
    }

    suspend fun insertTodo(todo: Todo) {
        todoDao.insertTodo(todo)
    }

    suspend fun updateTodo(todo: Todo) {
        todoDao.updateTodo(todo)
    }

    suspend fun deleteTodo(todo: Todo) {
        todoDao.deleteTodo(todo)
    }

    fun getTodoById(id: Int): LiveData<Todo> {
        return todoDao.getTodoById(id)
    }
} 