create table coach
(
    id           int auto_increment
        primary key,
    nom          varchar(255) null,
    prenom       varchar(255) not null,
    id_user_id   int          null,
    categorie_id int          null,
    picture      varchar(255) null,
    description  varchar(255) null,
    prix         double       null,
    rating       double       null,
    constraint UNIQ_3F596DCC79F37AE5
        unique (id_user_id),
    constraint FK_3F596DCC79F37AE5
        foreign key (id_user_id) references user (id),
    constraint FK_3F596DCCBCF5E72D
        foreign key (categorie_id) references categorie (id)
)
    collate = utf8mb4_unicode_ci;

create index IDX_3F596DCCBCF5E72D
    on coach (categorie_id);

