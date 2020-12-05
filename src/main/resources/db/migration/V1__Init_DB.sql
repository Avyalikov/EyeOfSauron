create sequence hibernate_sequence start 3 increment 1;

create table user_role (
    user_id int8 not null,
    roles varchar(255)
);

create table usr (
    id int8 not null,
    email varchar(255),
    password varchar(255),
    username varchar(255),
    family_id int8,
    full_name varchar(255),
    primary key (id)
);

create table msgs (
    id int8 not null,
    user_id int8 not null,
    message varchar(2048),
    family_id int8 not null,
    user_name varchar(255),
    message_time time,
    primary key (id)
);

create table msgs_archive (
    id int8 not null,
    user_id int8 not null,
    family_id int8 not null,
    archive_file varchar(255)
);

alter table if exists user_role
    add constraint user_role_fk
    foreign key (user_id) references usr;

alter table if exists message_user
    add constraint message_user_fk
    foreign key (user_id) references usr;

alter table if exists archive_user
    add constraint archive_user_fk
    foreign key (user_id) references usr;