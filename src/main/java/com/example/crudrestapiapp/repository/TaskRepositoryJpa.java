package com.example.crudrestapiapp.repository;

import com.example.crudrestapiapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepositoryJpa extends JpaRepository<Task, Long> {
}
