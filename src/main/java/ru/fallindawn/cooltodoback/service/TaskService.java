package ru.fallindawn.cooltodoback.service;

import ru.fallindawn.cooltodoback.entity.Task;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskService {
    void createTask(String login, String name, LocalDateTime deadline);

    List<Task> findAllTaskByUserName(String userName);
}
