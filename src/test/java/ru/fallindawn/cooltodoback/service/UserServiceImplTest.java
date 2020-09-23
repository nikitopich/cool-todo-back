package ru.fallindawn.cooltodoback.service;

import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.unitils.UnitilsBlockJUnit4ClassRunner;
import org.unitils.easymock.annotation.Mock;
import ru.fallindawn.cooltodoback.entity.User;
import ru.fallindawn.cooltodoback.repository.RoleRepository;
import ru.fallindawn.cooltodoback.repository.UserRepository;
import ru.fallindawn.cooltodoback.service.impl.UserServiceImpl;

import java.util.Optional;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;

@RunWith(UnitilsBlockJUnit4ClassRunner.class)
public class UserServiceImplTest {
    public static final String USER = "user";
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
        expect(userRepository.findByLoginIgnoreCase(USER))
                .andReturn(Optional.of(user(USER)));
        replay(userRepository);

        User assertUser = userService.findByLogin(USER);

        assertEquals(USER,assertUser.getLogin());
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    private User user(String login) {
        User user = new User();
        user.setLogin(login);
        return user;
    }
}
