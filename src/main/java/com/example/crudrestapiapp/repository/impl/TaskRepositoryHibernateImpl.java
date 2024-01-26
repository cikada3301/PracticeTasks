package com.example.crudrestapiapp.repository.impl;

import com.example.crudrestapiapp.model.Task;
import com.example.crudrestapiapp.repository.TaskRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
@RequiredArgsConstructor
public class TaskRepositoryHibernateImpl implements TaskRepository {

    @PersistenceContext
    private final EntityManager entityManager;


    @Override
    public List<Task> findAll() {
        return entityManager.createQuery("from Task t", Task.class)
                .getResultList();
    }

    @Override
    public Optional<Task> findById(Long id) {
        return Optional.ofNullable((Task) entityManager.createQuery("from Task t where t.id=:id")
                .setParameter("id", id)
                .getSingleResult());
    }

    @Override
    public Task save(Task task) {
        return entityManager.merge(task);
    }

    @Override
    public Task update(Long id, Task task) {
        task.setId(id);
        return entityManager.merge(task);
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(entityManager.find(Task.class, id));
    }
}
