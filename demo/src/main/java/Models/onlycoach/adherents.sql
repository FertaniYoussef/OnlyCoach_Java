create table adherents
(
    id       int auto_increment
        primary key,
    user_id  int  null,
    cours_id int  null,
    date     date null,
    constraint FK_562C7DA37ECF78B0
        foreign key (cours_id) references cours (id),
    constraint FK_562C7DA3A76ED395
        foreign key (user_id) references user (id)
)
    collate = utf8mb4_unicode_ci;

create index IDX_562C7DA37ECF78B0
    on adherents (cours_id);

create index IDX_562C7DA3A76ED395
    on adherents (user_id);

