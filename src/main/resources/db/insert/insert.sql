insert into role (title)
VALUES ('USER'),
       ('ADMIN');
insert into user (active, email, password, username, fk_role_id) value
    (1, '1@1', 'root', 'root', (select ID from role where title = 'USER'));
INSERT INTO cart (fk_user_id) VALUE ((select id from user where username = 'root'));
insert into author (name, surname)
values ('Лев', 'Толстой'),
       ('Федор', 'Достоевский');
insert into book (price, title_en, title_ru, fk_author_id)
VALUES (100, 'War and Peace', 'Война и Мир', (select id from author where surname = 'Толстой' and name = 'Лев')),
       (200, 'Crime and Punishment', 'Преступление и Наказание',
        (select id from author where surname = 'Достоевский' and name = 'Федор'))
