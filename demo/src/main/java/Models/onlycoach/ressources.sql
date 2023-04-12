create table ressources
(
    id               int auto_increment
        primary key,
    sections_id      int          null,
    lien             varchar(255) null,
    index_ressources int          null,
    description      varchar(255) null,
    constraint FK_6A2CD5C7577906E4
        foreign key (sections_id) references sections (id)
)
    collate = utf8mb4_unicode_ci;

create index IDX_6A2CD5C7577906E4
    on ressources (sections_id);

