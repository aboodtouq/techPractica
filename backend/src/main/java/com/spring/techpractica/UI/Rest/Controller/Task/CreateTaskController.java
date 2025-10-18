package com.spring.techpractica.UI.Rest.Controller.Task;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tasks")
@AllArgsConstructor
public class CreateTaskController {

    @PostMapping("/")
    public ResponseEntity<?> createTask(@RequestBody CreateTaskRequest request){

    }
}