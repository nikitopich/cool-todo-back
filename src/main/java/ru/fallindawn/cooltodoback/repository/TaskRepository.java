package ru.fallindawn.cooltodoback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.fallindawn.cooltodoback.entity.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "select t from Task t join fetch t.user u where u.login = ?1")
    List<Task> findAllByUserLogin(String login);
}
