delete
from user;
delete
from basket;
delete
from role;
insert into role(id, title)
VALUES (1, 'ADMIN'),
       (2, 'USER');
insert into basket(id)
values (1),
       (2);
insert into user(id, active, email, password, username, fk_basket_id, fk_role_id)
values (1, true, '1@1', 'admin', 'admin', 1, 1),
       (2, true, '1@1', 'user', 'user', 2, 2);


