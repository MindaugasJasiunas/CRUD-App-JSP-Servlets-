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

INSERT INTO public.students (id, firstname, lastname, universitygroup, email) VALUES (1, 'Tim', 'Testy', 'IT4', 'tim@example.com');
INSERT INTO public.students (id, firstname, lastname, universitygroup, email) VALUES (33, 'Jane', 'Doe', 'KF5', 'janedoe@example.com');
INSERT INTO public.students (id, firstname, lastname, universitygroup, email) VALUES (32, 'Test', 'Testy', 'T1', 'test@example.com');
INSERT INTO public.students (id, firstname, lastname, universitygroup, email) VALUES (5, 'John', 'Doe', 'IT5', 'johndoe@example.com');