package ru.fallindawn.cooltodoback.controller;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fallindawn.cooltodoback.dto.UserDto;
import ru.fallindawn.cooltodoback.exception.ValidationException;
import ru.fallindawn.cooltodoback.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Log
public class UserController {
    private final UserService userService;

    @PostMapping("/save")
    public UserDto saveUser(@RequestBody UserDto userDto) throws ValidationException {
        log.info("Handling save user: " + userDto);
        return userService.saveUser(userDto);
    }

    @GetMapping("/findAll")
    public List<UserDto> findAllUsers() {
        log.info("Handling find all users request");
        return userService.findAll();
    }

    @GetMapping("/findByLogin")
    public UserDto findByLogin(@RequestParam String login) {
        log.info("Handling find by login: " + login);
        return userService.findByLogin(login);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.info("Handling delete user request: " + id);
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
