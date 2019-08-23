create schema if not exists greetings_test;
use greetings_test;

create table if not exists greeting (
	id int not null auto_increment primary key,
    greeting varchar(50) not null
);