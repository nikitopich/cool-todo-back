package ru.fallindawn.cooltodoback.dto;

import lombok.Data;

@Data
public class TaskDto {
    private String login;
    private String name;
    private String deadline;
}
