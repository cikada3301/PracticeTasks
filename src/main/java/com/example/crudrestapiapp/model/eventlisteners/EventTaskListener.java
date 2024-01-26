package com.example.crudrestapiapp.model.eventlisteners;

import com.example.crudrestapiapp.model.Task;
import jakarta.persistence.PrePersist;

public class EventTaskListener {

    @PrePersist
    private void beforeCreate(Task task) {
        task.setCompleted(false);
    }
}
