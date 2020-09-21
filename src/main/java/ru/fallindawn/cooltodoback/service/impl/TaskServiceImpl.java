package ru.fallindawn.cooltodoback.service.impl;

import org.springframework.stereotype.Service;
import ru.fallindawn.cooltodoback.entity.Task;
import ru.fallindawn.cooltodoback.exception.TaskCreateException;
import ru.fallindawn.cooltodoback.repository.TaskRepository;
import ru.fallindawn.cooltodoback.service.TaskService;
import ru.fallindawn.cooltodoback.service.UserService;

import javax.transaction.Transactional;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;
    private UserService userService;

    public TaskServiceImpl(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    @Transactional
    @Override
    public void createTask(String login, String name, String deadline) {
            Task task = new Task(userService.findByLogin(login), name, dateFromString(deadline), false);
            taskRepository.save(task);
    }

    @Transactional
    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Transactional
    @Override
    public List<Task> findAllTaskByUserName(String login) {
        return taskRepository.findAllByUserLogin(login);
    }


    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    public LocalDateTime dateFromString(String date) {
        LocalDateTime localDateTime;
        try {
            DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            localDateTime = LocalDateTime.parse(date, formatter);
        } catch (DateTimeException e) {
            throw new TaskCreateException("Wrong date format");
        }
        return localDateTime;
    }
}
