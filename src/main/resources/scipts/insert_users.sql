INSERT INTO ROLE VALUES (nextval('sq_role_id'), 'ROLE_ADMIN');
INSERT INTO ROLE VALUES (nextval('sq_role_id'), 'ROLE_USER');

INSERT INTO USERS (id, login, hash_password, email, first_name, last_name)
VALUES (nextval('sq_users_id'), 'admin', '$2a$04$l6jf/IelD8EcKEx0z5LJFur01DtdBcTLUxfiq79X1GF2hgJdmIeEW', 'admin@fallindawn.ru','Admin', 'Admin'),
       (nextval('sq_users_id'), 'user', '$2a$04$uUTMuVyvusd6gIkxLdrF5ufDQ0K359C0Pjq6yBtbctOo3y6mhpwiy', 'user@fallindawn.ru','User', 'User');

INSERT INTO users_role
VALUES (1,1), (2,2);