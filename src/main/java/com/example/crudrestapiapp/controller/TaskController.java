package com.example.crudrestapiapp.controller;

import com.example.crudrestapiapp.model.Task;
import com.example.crudrestapiapp.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @Operation(
            description = "Endpoint for get task list",
            summary = "Request for get tasks",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<Task>> get() {
        return ResponseEntity.ok(taskService.get());
    }

    @Operation(
            description = "Endpoint for get task by id",
            summary = "Request for get task by id",
            parameters = {
                    @Parameter(name = "id", description = "Id of Task", example = "2"),
            },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.findById(id));
    }

    @Operation(
            description = "Endpoint for save a new Task",
            summary = "Request for create a new Task",
            parameters = {
                    @Parameter(name = "name", description = "Name of Task", example = "Learn Kafka"),
            },
            responses = {
                    @ApiResponse(
                            description = "Success if body is valid",
                            responseCode = "201"
                    )
            }
    )
    @PostMapping
    public ResponseEntity<Task> save(@RequestBody String name) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.save(name));
    }

    @Operation(
            description = "Endpoint for update Task",
            summary = "Request for update Task",
            parameters = {
                    @Parameter(name = "id", description = "Id of Task", example = "2"),
            },
            responses = {
                    @ApiResponse(
                            description = "Success if body of task is valid",
                            responseCode = "200"
                    )
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task task) {
        return ResponseEntity.ok(taskService.update(id, task));
    }

    @Operation(
            description = "Endpoint for remove Task",
            summary = "Request for remove Task",
            parameters = {
                    @Parameter(name = "id", description = "Id of Task", example = "2"),
            },
            responses = {
                    @ApiResponse(
                            description = "Success if parameter id is valid",
                            responseCode = "200"
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Task> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
