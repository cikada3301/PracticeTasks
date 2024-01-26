package com.example.crudrestapiapp.repository.impl;

import com.example.crudrestapiapp.model.Task;
import com.example.crudrestapiapp.repository.TaskRepository;
import com.example.crudrestapiapp.repository.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Task> findAll() {
        return jdbcTemplate.query("SELECT * FROM Tasks", new TaskMapper());
    }

    @Override
    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM Tasks where id = ?", new TaskMapper(), id));
    }

    @Override
    public Task save(Task task) {
        Map<String, Object> params = Map.of(
                "name", task.getName(),
                "completed", task.getCompleted()
        );

        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("tasks")
                .usingGeneratedKeyColumns("id");

        return findById(simpleJdbcInsert.executeAndReturnKey(params).longValue()).get();
    }

    @Override
    public Task update(Long id, Task task) {
        jdbcTemplate.update("UPDATE Tasks set name = ?, completed = ? WHERE id = ?", task.getName(), task.getCompleted(), id);

        return findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM Tasks WHERE id = ?", id);
    }
}
