--liquibase formatted sql

--changeset author:1
create table PESSOA
(
    NOME             VARCHAR     not null,
    SEXO             VARCHAR,
    EMAIL            VARCHAR,
    DATA_NASCIMENTO  DATE        not null,
    NATURALIDADE     VARCHAR,
    NACIONALIDADE    VARCHAR,
    CPF              VARCHAR(11) not null,
    DATA_CADASTRO    DATE        not null,
    DATA_ATUALIZACAO DATE        not null,
    constraint PESSOA_PK
        primary key (CPF)
);


--changeset author:2
create table PESSOA
(
    NOME             VARCHAR     not null,
    SEXO             VARCHAR,
    EMAIL            VARCHAR,
    DATA_NASCIMENTO  DATE        not null,
    NATURALIDADE     VARCHAR,
    NACIONALIDADE    VARCHAR,
    CPF              VARCHAR(11) not null,
    DATA_CADASTRO    DATE        not null,
    DATA_ATUALIZACAO DATE        not null,
    constraint PESSOA_PK
        primary key (CPF)
);
