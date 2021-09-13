import React, { useEffect, useState } from 'react';
import { Button, Container, Icon, Message, Table, TableCell } from 'semantic-ui-react';
import { listarPessoas } from './listaPessoasAPI';
import { PessoaDTO } from '../PessoaDTO';
import { useAppDispatch, useAppSelector } from '../../../app/hooks';
import { selectLogin } from '../../login/loginSlice';
import { setPessoaEditar, setRemoveModal, setStep, Step } from '../reducer/pessoaSlice';
import DeletaPessoa from '../crudpessoa/DeletaPessoa/DeletaPessoa';

export default function ListaPessoas() {
  const [pessoas, setPessoas] = useState([]);
  const [success, setSuccess] = useState('');
  const [error, setError] = useState('');
  const login = useAppSelector(selectLogin);
  const dispatch = useAppDispatch();
  useEffect(() => {
    listarPessoas(login).then((value) => setPessoas(value));
  }, []);

  console.log(pessoas);

  return (
    <Container>
      <Table celled padded>
        <Table.Header>
          <Table.Row textAlign={'center'}>
            <Table.HeaderCell>Nome</Table.HeaderCell>
            <Table.HeaderCell>Sexo</Table.HeaderCell>
            <Table.HeaderCell>E-mail</Table.HeaderCell>
            <Table.HeaderCell>Data de Nascimento</Table.HeaderCell>
            <Table.HeaderCell>Naturalidade</Table.HeaderCell>
            <Table.HeaderCell>Nacionalidade</Table.HeaderCell>
            <Table.HeaderCell>CPF</Table.HeaderCell>
            <Table.HeaderCell>Acão</Table.HeaderCell>
          </Table.Row>
        </Table.Header>

        <Table.Body>
          {pessoas.length === 0 && (
            <Table.Row textAlign='center'>
              <TableCell
                colSpan='8'
                textAlign='center'
                key={0}
                content={'Nenhuma Pessoa adicionada'}
                singleLine
              />
            </Table.Row>
          )}
          {pessoas.length !== 0 &&
            pessoas.map((value: PessoaDTO, index) => (
              <Table.Row key={index} textAlign={'center'}>
                <Table.Cell>{value.nome}</Table.Cell>
                <Table.Cell>{value.sexo}</Table.Cell>
                <Table.Cell>{value.email}</Table.Cell>
                <Table.Cell>{new Date(value.dataNascimento).toLocaleDateString()}</Table.Cell>
                <Table.Cell>{value.naturalidade}</Table.Cell>
                <Table.Cell>{value.nacionalidade}</Table.Cell>
                <Table.Cell>{value.cpf}</Table.Cell>
                <Table.Cell>
                  <Button.Group fluid>
                    <Button
                      animated='fade'
                      onClick={() => {
                        dispatch(setPessoaEditar(value));
                        dispatch(setStep(Step.EDITAR));
                      }}
                    >
                      <Button.Content hidden>Editar</Button.Content>
                      <Button.Content visible>
                        <Icon name='edit' />
                      </Button.Content>
                    </Button>
                    <Button.Or text='ou' />
                    <Button
                      animated='vertical'
                      negative
                      onClick={() => {
                        dispatch(setPessoaEditar(value));
                        dispatch(setRemoveModal(true));
                      }}
                    >
                      <Button.Content hidden>Excluir</Button.Content>
                      <Button.Content visible>
                        <Icon name='trash' />
                      </Button.Content>
                    </Button>
                  </Button.Group>
                </Table.Cell>
              </Table.Row>
            ))}
        </Table.Body>

        <Table.Footer fullWidth>
          <Table.Row>
            <Table.HeaderCell colSpan='8'>
              <Button
                floated='right'
                icon
                labelPosition='left'
                primary
                size='small'
                onClick={() => dispatch(setStep(Step.ADICIONAR))}
              >
                <Icon name='user' /> Adicionar Pessoa
              </Button>
            </Table.HeaderCell>
          </Table.Row>
        </Table.Footer>
      </Table>
      {error && (
        <Message color='red' header='Occorreu um erro ao deletar usuário' content={error} />
      )}
      {success && <Message success header='Sucesso ao deletar usuário' content={success} />}
      <DeletaPessoa onError={setError} onSuccess={setSuccess} />
    </Container>
  );
}
