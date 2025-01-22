create table t_user
(
    id       int auto_increment
        primary key,
    username varchar(255) null,
    password varchar(50)  not null
);
