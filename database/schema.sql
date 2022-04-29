-- drop database if exists
drop schema if exists base;

create schema base;

use base;

create table user(
    user_id char(8) not null,
    username varchar(64) not null unique,
    name varchar(64), 

    primary key(user_id)
);

create table task(
    task_id int not null auto_increment,
    description varchar(255),
    priority int, 
    due_date date,

	user_id char(8),
    
    primary key(task_id),

    constraint fk_user_id
        foreign key (user_id)
        references user(user_id)
        on delete cascade
);

