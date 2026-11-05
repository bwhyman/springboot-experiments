create table if not exists user_x
(
    id bigint primary key ,
    account varchar not null ,
    password varchar not null ,
    name varchar not null ,
    role varchar not null ,

    create_time timestamp not null default current_timestamp,
    update_time timestamp not null default current_timestamp,

    unique (account)
);

create table if not exists address
(
    id bigint primary key ,
    detail varchar not null ,
    user_id bigint not null ,

    create_time timestamp not null default current_timestamp,
    update_time timestamp not null default current_timestamp
);
create index if not exists address_user_id_idx on address (user_id);