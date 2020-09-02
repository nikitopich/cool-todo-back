package ru.fallindawn.cooltodoback.controller.security;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.fallindawn.cooltodoback.dto.LoginFormDto;
import ru.fallindawn.cooltodoback.dto.SignUpFormDto;
import ru.fallindawn.cooltodoback.exception.UserRegistretionException;
import ru.fallindawn.cooltodoback.security.JwtProvider;
import ru.fallindawn.cooltodoback.security.JwtResponse;
import ru.fallindawn.cooltodoback.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
public class SecurityController {
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final UserService userService;

    public SecurityController(AuthenticationManager authenticationManager, JwtProvider jwtProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody SignUpFormDto signUpFormDto) {
        try {
            userService.createUser(
                    signUpFormDto.getLogin(),
                    signUpFormDto.getPassword(),
                    signUpFormDto.getEmail(),
                    signUpFormDto.getRoles());
        } catch (UserRegistretionException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/auth")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginFormDto loginFormDto) {
        String trimmedLoginLowerCase = loginFormDto.getLogin().trim().toLowerCase();

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(trimmedLoginLowerCase, loginFormDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = jwtProvider.generateToken(authentication);

        return ResponseEntity.ok(new JwtResponse(jwtToken));
    }
}
