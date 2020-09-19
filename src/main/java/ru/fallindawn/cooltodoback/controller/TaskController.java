package ru.fallindawn.cooltodoback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fallindawn.cooltodoback.dto.TaskDto;
import ru.fallindawn.cooltodoback.entity.Task;
import ru.fallindawn.cooltodoback.exception.TaskCreateException;
import ru.fallindawn.cooltodoback.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createTask(@RequestBody TaskDto taskDto) {
        try {
            taskService.createTask(
                    taskDto.getLogin(),
                    taskDto.getName(),
                    taskDto.getDeadline());
        } catch (TaskCreateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Task created successfully!");
    }

    @GetMapping("/{login}")
    public List<Task> getAllUserTasks(@PathVariable String login) {
        return taskService.findAllTaskByUserName(login);
    }
}
