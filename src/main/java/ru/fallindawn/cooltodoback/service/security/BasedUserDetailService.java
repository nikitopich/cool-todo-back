package ru.fallindawn.cooltodoback.service.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.fallindawn.cooltodoback.entity.User;
import ru.fallindawn.cooltodoback.security.UserPrinciple;
import ru.fallindawn.cooltodoback.service.UserService;

import javax.persistence.EntityNotFoundException;

@Service
public class BasedUserDetailService implements UserDetailsService {
    private final UserService userService;

    public BasedUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        try {
            User user = userService.findByLogin(username);
            return UserPrinciple.build(user);
        } catch (EntityNotFoundException e) {
            throw new UsernameNotFoundException("User not found", e);
        }
    }
}
