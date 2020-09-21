INSERT INTO ROLE VALUES (nextval('sq_role_id'), 'ROLE_ADMIN');
INSERT INTO ROLE VALUES (nextval('sq_role_id'), 'ROLE_USER');

INSERT INTO USERS (id, login, hash_password, email)
VALUES (nextval('sq_users_id'), 'admin', '$2a$04$l6jf/IelD8EcKEx0z5LJFur01DtdBcTLUxfiq79X1GF2hgJdmIeEW', 'admin@fallindawn.ru'),
       (nextval('sq_users_id'), 'user', '$2a$04$uUTMuVyvusd6gIkxLdrF5ufDQ0K359C0Pjq6yBtbctOo3y6mhpwiy', 'user@fallindawn.ru');

INSERT INTO users_role
VALUES (1,1), (2,2);

INSERT INTO TASKS (id, deadline, name, users_id, ready)
VALUES (nextval('sq_tasks_id'), '2020-01-10 00:00:00', 'admin most-important task-1', 1, false),
       (nextval('sq_tasks_id'), '2021-01-01 00:00:00', 'admin most-important task-2', 1, false);
