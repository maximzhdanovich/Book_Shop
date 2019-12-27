delete
from book;
delete
from author;
insert into author (id, name, surname)
values (1, '1', '1'),
       (2, '2', '2');
insert
into book (id, price, title_en, title_ru, fk_author_id, description)
values (1, 1, '1', '1', 1, '1'),
       (2, 2, '2', '2', 1, '2'),
       (3, 3, '3', '3', 1, '3'),
       (4, 4, '4', '4', 1, '4');

