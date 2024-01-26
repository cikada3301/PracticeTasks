package com.example.crudrestapiapp.service.impl;

import com.example.crudrestapiapp.model.Task;
import com.example.crudrestapiapp.repository.TaskRepository;
import com.example.crudrestapiapp.repository.TaskRepositoryJpa;
import com.example.crudrestapiapp.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@CacheConfig(cacheNames = "accounts", cacheManager = "redisCacheManager")
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    // Hiber or jdctTemplate injected
    //private final TaskRepository taskRepository;

    //Spring data repo
    private final TaskRepositoryJpa taskRepository;

    @Override
    @Transactional
    public List<Task> get() {
        return taskRepository.findAll();
    }

    @Override
    @Transactional
    @Cacheable(key = "#id")
    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id"));
    }

    @Override
    @Transactional
    public Task save(String name) {
        Task task = new Task();
        task.setName(name);
        return taskRepository.save(task);
    }

    @Override
    @Transactional
    public Task update(Long id, Task task) {
        // Spring data solution
        Task updatedTask = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id"));
        if (task.getName() != null) {
            updatedTask.setName(task.getName());
        }
        if (task.getCompleted() != null) {
            updatedTask.setCompleted(true);
        }

        return taskRepository.save(updatedTask);

        //Hibernate or jdbcTemplate
        //return taskRepository.update(id, task);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
