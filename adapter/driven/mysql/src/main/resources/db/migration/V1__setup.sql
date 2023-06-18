create table product(
    id bigint auto_increment not null primary key,
    uuid varchar(36) not null unique,
    name varchar(200) not null,
    description text,
    price numeric(12,2) not null,
    image text not null,
    category enum('COMBO', 'SANDWICH', 'BEVERAGE', 'DESSERT', 'SIDE_DISH') not null,
    created datetime not null,
    last_updated datetime not null,
    version integer not null
);