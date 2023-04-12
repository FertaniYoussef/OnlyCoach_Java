create table abonnement
(
    id       int auto_increment
        primary key,
    coach_id int        null,
    user_id  int        null,
    date_deb date       null,
    date_fin date       not null,
    prix     double     null,
    is_fav   tinyint(1) not null,
    constraint FK_351268BB3C105691
        foreign key (coach_id) references coach (id),
    constraint FK_351268BBA76ED395
        foreign key (user_id) references user (id)
)
    collate = utf8mb4_unicode_ci;

create index IDX_351268BB3C105691
    on abonnement (coach_id);

create index IDX_351268BBA76ED395
    on abonnement (user_id);

