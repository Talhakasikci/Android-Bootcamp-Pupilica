package com.talhakasikci.odev7.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.talhakasikci.odev7.data.entity.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM todos")
    fun getAllTodos(): LiveData<List<Todo>>
    
    @Query("SELECT * FROM todos WHERE name LIKE '%' || :searchQuery || '%'")
    fun searchTodos(searchQuery: String): LiveData<List<Todo>>
    
    @Insert
    suspend fun insertTodo(todo: Todo)
    
    @Update
    suspend fun updateTodo(todo: Todo)
    
    @Delete
    suspend fun deleteTodo(todo: Todo)
    
    @Query("SELECT * FROM todos WHERE id = :todoId")
    fun getTodoById(todoId: Int): LiveData<Todo>
} 