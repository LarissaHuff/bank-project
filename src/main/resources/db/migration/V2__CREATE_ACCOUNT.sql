create table account(
    number bigint not null auto_increment,
    create_date date not null,
    balance double not null,
    type varchar(50) not null,
    status varchar(50) not null,
    person_id bigint not null,

    primary key(number)
);






