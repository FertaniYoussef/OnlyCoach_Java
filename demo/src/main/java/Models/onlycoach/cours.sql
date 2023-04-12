create table cours
(
    id            int auto_increment
        primary key,
    titre         varchar(255) null,
    description   varchar(255) null,
    date_creation date         null,
    nb_vues       int          null,
    cours_photo   varchar(255) null,
    id_coach_id   int          null,
    constraint FK_FDCA8C9C6CCBBA04
        foreign key (id_coach_id) references coach (id)
)
    collate = utf8mb4_unicode_ci;

create index IDX_FDCA8C9C6CCBBA04
    on cours (id_coach_id);

