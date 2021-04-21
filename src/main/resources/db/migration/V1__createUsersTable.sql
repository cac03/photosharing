create table users (
    id bigint constraint user_pk primary key generated always as identity,
    username varchar(255) unique not null,
    password_hash varchar(255) not null,
    constraint username_is_lowercase check (username = lower(username))
);