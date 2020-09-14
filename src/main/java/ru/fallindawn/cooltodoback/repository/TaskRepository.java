package ru.fallindawn.cooltodoback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fallindawn.cooltodoback.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
