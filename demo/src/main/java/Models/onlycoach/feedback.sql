create table feedback
(
    id            int auto_increment
        primary key,
    user_id       int          null,
    sujet         varchar(255) not null,
    email         varchar(255) not null,
    description   varchar(255) not null,
    date_feedback date         null,
    status        int          null,
    constraint FK_D2294458A76ED395
        foreign key (user_id) references user (id)
)
    collate = utf8mb4_unicode_ci;

create index IDX_D2294458A76ED395
    on feedback (user_id);

