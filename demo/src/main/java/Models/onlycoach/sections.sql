create table sections
(
    id            int auto_increment
        primary key,
    cours_id      int          null,
    index_section int          null,
    titre         varchar(255) null,
    nbresources   int          null,
    constraint FK_2B9643987ECF78B0
        foreign key (cours_id) references cours (id)
)
    collate = utf8mb4_unicode_ci;

create index IDX_2B9643987ECF78B0
    on sections (cours_id);

