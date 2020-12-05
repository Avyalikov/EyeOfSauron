insert into usr (id, username, password, family_id, full_name)
    values (1, '123456', '123', 0, 'Иванов Петр Сергеевич');

insert into user_role (user_id, roles)
    values (1, 'USER'), (1, 'ADMIN');