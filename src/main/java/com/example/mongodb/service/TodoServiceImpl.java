package com.example.mongodb.service;



import com.example.mongodb.models.Todo;
import com.example.mongodb.models.TodoDTO;
import com.example.mongodb.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<TodoDTO> getAllTodos() {
    return (List<TodoDTO>) todoRepository.findAll().stream()
    .map(todo -> this.convertToDTO((Todo) todo))
    .collect(Collectors.toList());
    }

    @Override
    public Optional<TodoDTO> getTodoById(String id) {
        return todoRepository.findById(id)
                .map(todo->this.convertToDTO((Todo) todo));
    }

    @Override
    public TodoDTO saveTodo(TodoDTO todoDTO) {
        Todo todo = convertToEntity(todoDTO);
        Todo savedTodo = (Todo) todoRepository.save(todo);
        return convertToDTO(savedTodo);
    }

    @Override
    public TodoDTO updateTodo(String id, TodoDTO todoDTO) {
        Todo existingTodo = (Todo) todoRepository.findById(id).orElseThrow();

        existingTodo.setTitle(todoDTO.title());
        existingTodo.setDescription(todoDTO.description());
        existingTodo.setStatus(todoDTO.status());
        existingTodo.setCreateDate(todoDTO.createDate());

        Todo updatedTodo = (Todo) todoRepository.save(existingTodo);
        return convertToDTO(updatedTodo);
    }

    @Override
    public void deleteTodo(String id) {
        todoRepository.deleteById(id);
    }

    // Helper method to convert Entity to DTO
    private TodoDTO convertToDTO(Todo todo) {
        return new TodoDTO(
                todo.getId(),
                todo.getTitle(),
                todo.getDescription(),
                todo.getStatus(),
                todo.getCreateDate()
        );
    }

    // Helper method to convert DTO to Entity
    private Todo convertToEntity(TodoDTO dto) {
        Todo todo = new Todo();
        todo.setId(dto.id()); // only if your MongoDB collection allows setting custom IDs
        todo.setTitle(dto.title());
        todo.setDescription(dto.description());
        todo.setStatus(dto.status());
        todo.setCreateDate(dto.createDate());
        return todo;
    }
}
