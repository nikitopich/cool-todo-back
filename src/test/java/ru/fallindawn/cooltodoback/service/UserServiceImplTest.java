package ru.fallindawn.cooltodoback.service;

import org.easymock.EasyMockRule;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.unitils.UnitilsBlockJUnit4ClassRunner;
import org.unitils.easymock.annotation.Mock;
import ru.fallindawn.cooltodoback.entity.User;
import ru.fallindawn.cooltodoback.repository.RoleRepository;
import ru.fallindawn.cooltodoback.repository.UserRepository;
import ru.fallindawn.cooltodoback.service.impl.UserServiceImpl;

import java.util.HashSet;

import static java.util.Arrays.asList;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;

@RunWith(UnitilsBlockJUnit4ClassRunner.class)
public class UserServiceImplTest {
    @Mock
    UserRepository userRepository;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    RoleRepository roleRepository;

    @TestSubject
    UserService userService;

    @Before
    public void setUp() {
        userService = new UserServiceImpl(userRepository, roleRepository, passwordEncoder);
    }

    @Test
    public void findByLogin() {
        expect(userRepository.findAll())
                .andReturn(asList(
                        user("alive"),
                        user("death"),
                        user("suicide")
                ));
        replay();

        User assertUser = userService.findByLogin("death");

        assertEquals(user("death").getLogin(),assertUser.getLogin());
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    public User user(String login) {
        User user = new User(login);
        user.setRoles(new HashSet<>());
        return user;
    }

}
