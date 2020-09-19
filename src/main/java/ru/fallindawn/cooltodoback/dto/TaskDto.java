package ru.fallindawn.cooltodoback.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDto {
    private String login;
    private String name;
    private LocalDateTime deadline;
}
