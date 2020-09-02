package ru.fallindawn.cooltodoback.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class SignUpFormDto {
    private String login;
    private String email;
    private Set<String> roles;
    private String password;

}
