create table rating
(
    id       int auto_increment
        primary key,
    user_id  int null,
    cours_id int null,
    note     int null,
    constraint FK_D88926227ECF78B0
        foreign key (cours_id) references cours (id),
    constraint FK_D8892622A76ED395
        foreign key (user_id) references user (id)
)
    collate = utf8mb4_unicode_ci;

create index IDX_D88926227ECF78B0
    on rating (cours_id);

create index IDX_D8892622A76ED395
    on rating (user_id);

