--liquibase formatted sql

--changeset author:2
create table USUARIO
(
    ID    int auto_increment,
    NOME  VARCHAR              not null,
    LOGIN VARCHAR              not null,
    SENHA VARCHAR              not null,
    EMAIL VARCHAR,
    ATIVO BOOLEAN default True not null,
    constraint USUARIO_PK
        primary key (ID)
);


