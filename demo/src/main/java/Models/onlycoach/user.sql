create table user
(
    id          int auto_increment
        primary key,
    email       varchar(180) not null,
    roles       longtext     not null comment '(DC2Type:json)',
    password    varchar(255) not null,
    nom         varchar(255) not null,
    prenom      varchar(255) not null,
    picture     varchar(255) null,
    description longtext     null,
    phone       int          null
)
    collate = utf8mb4_unicode_ci;

