package ru.fallindawn.cooltodoback.service;

import org.unitils.easymock.annotation.Mock;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsBlockJUnit4ClassRunner;
import ru.fallindawn.cooltodoback.entity.Task;
import ru.fallindawn.cooltodoback.entity.User;
import ru.fallindawn.cooltodoback.repository.TaskRepository;
import ru.fallindawn.cooltodoback.service.impl.TaskServiceImpl;

import java.util.List;

import static java.util.Arrays.asList;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;

@RunWith(UnitilsBlockJUnit4ClassRunner.class)
public class TaskServiceImplTest {
    @Mock
    TaskRepository taskRepository;
    @Mock
    UserService userService;

    @TestSubject
    TaskService taskService;

    @Before
    public void setUp() {
        taskService = new TaskServiceImpl(taskRepository, userService);
    }

    @Test
    public void findAllTaskByUserName() {
        User user = user("user");
        expect(taskRepository.findAllByUserLogin("user"))
                .andReturn(asList(
                        new Task(user,"1"),
                        new Task(user,"2")
                ));
        replay(taskRepository);

        List<Task> assertTasks = taskService.findAllTaskByUserName("user");

        assertEquals(asList("1","2"),asList(
                assertTasks.get(0).getName(),assertTasks.get(1).getName())
        );
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
