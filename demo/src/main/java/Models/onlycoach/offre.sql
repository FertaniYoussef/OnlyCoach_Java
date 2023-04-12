create table offre
(
    id          int auto_increment
        primary key,
    id_coach_id int          null,
    nom         varchar(255) null,
    prix        double       null,
    discount    double       null,
    date_deb    date         null,
    date_fin    date         null,
    constraint UNIQ_AF86866F6CCBBA04
        unique (id_coach_id),
    constraint FK_AF86866F6CCBBA04
        foreign key (id_coach_id) references coach (id)
)
    collate = utf8mb4_unicode_ci;

