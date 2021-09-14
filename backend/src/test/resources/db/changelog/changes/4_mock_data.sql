--liquibase formatted sql

--changeset author:4 context:test
INSERT INTO PUBLIC.PESSOA (NOME, SEXO, EMAIL, DATA_NASCIMENTO, NATURALIDADE, NACIONALIDADE, CPF, DATA_CADASTRO, DATA_ATUALIZACAO)
VALUES ('Joao da Silva', 'MASCULINO', 'joaodasilva@softplan.com.br', '2021-09-14', 'Florianopolitano', 'Brasileiro', '96773550076', '2021-09-14', '2021-09-14');
INSERT INTO PUBLIC.PESSOA (NOME, SEXO, EMAIL, DATA_NASCIMENTO, NATURALIDADE, NACIONALIDADE, CPF, DATA_CADASTRO, DATA_ATUALIZACAO)
VALUES ('Maria das Rosas', 'FEMININO', 'mariadasrosas@softplan.com.br', '2021-09-14', 'Florianopolitano', 'Brasileiro', '65810612040', '2021-09-14', '2021-09-14');
INSERT INTO PUBLIC.PESSOA (NOME, SEXO, EMAIL, DATA_NASCIMENTO, NATURALIDADE, NACIONALIDADE, CPF, DATA_CADASTRO, DATA_ATUALIZACAO)
VALUES ('Prefiro não informar', 'NAO_INFORMAR', 'prefironaoinformar@softplan.com.br', '2021-09-14', 'Florianopolitano', 'Brasileiro', '86780356093', '2021-09-14',
        '2021-09-14');