package ru.fallindawn.cooltodoback.service;

import ru.fallindawn.cooltodoback.entity.Task;

import java.util.List;

public interface TaskService {
    void createTask(String login, String name, String deadline);

    void deleteTask(Long id);

    List<Task> findAllTaskByUserName(String userName);
}
