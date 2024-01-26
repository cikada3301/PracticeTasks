package com.example.crudrestapiapp;

import com.example.crudrestapiapp.controller.TaskController;
import com.example.crudrestapiapp.model.Task;
import com.example.crudrestapiapp.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
class CrudRestApiAppApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService ticketService;

    @Test
    void testGetTasks() throws Exception {
        mockMvc.perform(get("/tasks")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("[]"));

        assertEquals(ticketService.get(), List.of());
    }

    @Test
    void testSaveTask() throws Exception {
        mockMvc.perform(post("/tasks")
                        .content("Learn Java")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated());
    }
}
