create table reponse
(
    id             int auto_increment
        primary key,
    id_feedback_id int          null,
    texte          varchar(255) not null,
    constraint UNIQ_5FB6DEC7CA5B6570
        unique (id_feedback_id),
    constraint FK_5FB6DEC7CA5B6570
        foreign key (id_feedback_id) references feedback (id)
)
    collate = utf8mb4_unicode_ci;

