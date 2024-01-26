package com.example.crudrestapiapp.repository.mapper;

import com.example.crudrestapiapp.model.Task;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskMapper implements RowMapper<Task> {
    @Override
    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
        Task task = new Task();
        task.setId(rs.getLong("id"));
        task.setName(rs.getString("name"));
        task.setCompleted(rs.getBoolean("completed"));

        return task;
    }
}
