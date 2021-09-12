--liquibase formatted sql

--changeset author:3
insert into Usuario (id, nome, login, senha, ativo)
values (1, 'Admin', 'admin', '$2a$10$ss15XovK3uhWCtu.TCI.gutmRg6EJIgVKAd84X/U/6rMH2bQCbFUa', true);