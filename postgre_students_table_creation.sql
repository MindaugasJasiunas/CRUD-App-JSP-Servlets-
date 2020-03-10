create table students
(
    id              serial       not null
        constraint students_pk
            primary key,
    firstname       varchar(50)  not null,
    lastname        varchar(50)  not null,
    universitygroup varchar(10)  not null,
    email           varchar(100) not null
);

alter table students
    owner to root;

create unique index students_id_uindex
    on students (id);

