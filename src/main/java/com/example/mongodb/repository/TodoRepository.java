package com.example.mongodb.repository;

import com.example.mongodb.models.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodoRepository extends MongoRepository<Todo,String>{

    
}
