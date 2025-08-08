package com.example.mongodb.service;

import com.example.mongodb.models.TodoDTO;

import java.util.List;
import java.util.Optional;

public interface TodoService {
    List<TodoDTO> getAllTodos();
    Optional<TodoDTO> getTodoById(String id);
    TodoDTO saveTodo(TodoDTO todoDTO);
    TodoDTO updateTodo(String id, TodoDTO todoDTO);
    void deleteTodo(String id);
}
