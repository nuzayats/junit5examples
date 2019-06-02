CREATE TABLE employees
(
    id       bigint primary key,
    name     varchar(256) not null,
    hired_at datetime(3)  not null
);

insert into employees (id, name, hired_at) values (1, 'Jane Doe', '2019-04-01 09:00:00');
insert into employees (id, name, hired_at) values (2, 'John Doe', '2019-04-02 09:00:00');
