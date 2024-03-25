create table person(
    id bigint not null auto_increment,
    name varchar(100) not null,
    birth_date date not null,
    document_number varchar(20) not null,
    document_type varchar(20) not null,
    address_id bigint not null,
    email varchar(50) not null,
    birth_city varchar(20) not null,

    primary key(id)
);

create table address(
    id bigint not null auto_increment,
    street varchar(100) not null,
    number bigint not null,
    neighbourhood varchar(100) not null,
    city varchar(100) not null,
    country varchar(100) not null,
    postal_code varchar(40) not null,

    primary key(id)
);





