package com.example.crudrestapiapp;

import com.example.crudrestapiapp.model.Task;
import com.example.crudrestapiapp.repository.TaskRepositoryJpa;
import com.example.crudrestapiapp.service.TaskService;
import com.example.crudrestapiapp.service.impl.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = TaskServiceImpl.class)
public class TaskServiceTest {

    @MockBean
    TaskRepositoryJpa taskRepository;

    @Autowired
    TaskServiceImpl taskService;

    @Test
    public void testGet() {
        when(taskService.get()).thenReturn(List.of());
    }

    @Test
    public void testSave() {

        Task task = new Task(1L, "Learn React", false);

        when(taskService.save("Learn React")).thenReturn(new Task());
    }
}
