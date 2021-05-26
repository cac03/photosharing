create table posts (
    id bigint constraint post_pk primary key generated always as identity,
    posted_at timestamp not null,
    posted_by_user_id bigint constraint posted_by_used_id_fk references users(id),
    filename varchar(255) not null
);