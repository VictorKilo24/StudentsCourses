--liquibase formatted sql
--changeset vladimir_kushhov:insertValues

insert INTO persons (first_name, last_name)
values ('Vladimir', 'Ivanov'),
       ('Petr', 'Kalinin'),
       ('Sergey', 'Vasiliev'),
       ('Maria', 'Mironova'),
       ('Victoria', 'Kuznetsova');

insert INTO courses (name, available_space)
values ('Курс по высшей математике', 4),
       ('Курс по гражданскому праву', 9),
       ('Курс по общей биологии', 5),
       ('Курс по веб-дизайну', 10),
       ('Курс по органической химии', 7);


