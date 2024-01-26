package com.example.crudrestapiapp.repository;

import com.example.crudrestapiapp.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    List<Task> findAll();
    Optional<Task> findById(Long id);
    Task save(Task task);
    Task update(Long id, Task task);
    void deleteById(Long id);
}
