package ru.fallindawn.cooltodoback.controller.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.fallindawn.cooltodoback.dto.LoginFormDto;
import ru.fallindawn.cooltodoback.dto.SignUpFormDto;
import ru.fallindawn.cooltodoback.exception.UserRegistrationException;
import ru.fallindawn.cooltodoback.security.JwtProvider;
import ru.fallindawn.cooltodoback.security.JwtResponse;
import ru.fallindawn.cooltodoback.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
public class SecurityController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityController.class);

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
        } catch (UserRegistrationException e) {
            ResponseEntity<String> responseEntity = ResponseEntity.badRequest().body(e.getMessage());
            LOGGER.debug(responseEntity.toString());
            return responseEntity;
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
