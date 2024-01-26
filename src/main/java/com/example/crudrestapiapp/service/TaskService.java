package com.example.crudrestapiapp.service;

import com.example.crudrestapiapp.model.Task;

import java.util.List;

public interface TaskService {
    List<Task> get();
    Task findById(Long id);
    Task save(String name);
    Task update(Long id, Task task);
    void delete(Long id);
}
