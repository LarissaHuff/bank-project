create table movement(
    id bigint not null auto_increment,
    type varchar(30) not null,
    amount double not null,
    movement_date date not null,
    account_id bigint not null,

    primary key(id)
);