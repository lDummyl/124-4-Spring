create table if not exists u_roles
(
    id serial not null
    constraint roles_pk
    primary key,
    name varchar(25) not null
    );


create table if not exists users
(
    id bigserial not null
    constraint users_pk
    primary key,
    firstname varchar(20),
    lastname varchar(30),
    username varchar(30),
    password varchar(100),
    user_status integer
    );

create unique index if not exists users_id_uindex
	on users (id);

create table if not exists users_roles
(
    user_id bigint not null
    constraint users_roles_users_id_fk
    references users
    on update cascade on delete cascade,
    roles_id integer not null
    constraint users_roles_u_roles_id_fk
    references u_roles
);

create table if not exists driver
(
    id bigserial not null
    constraint driver_pk
    primary key,
    first_name varchar(25) not null,
    last_name varchar(30) not null,
    driver_license varchar(20) not null,
    create_date timestamp,
    update_date timestamp
);

create table if not exists car
(
    id bigserial not null
    constraint car_pk
    primary key,
    brand varchar(25) not null,
    model varchar(25) not null,
    create_date timestamp,
    driver_id bigint not null
    constraint car_driver_id_fk
    references driver
    on update cascade on delete cascade,
    category char
);


