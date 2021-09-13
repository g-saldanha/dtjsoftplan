import React, { useEffect, useState } from 'react';
import { Button, Icon, Table, TableCell } from 'semantic-ui-react';
import { listarPessoas } from './listaPessoasAPI';
import { PessoaDTO } from '../PessoaDTO';
import { useAppDispatch, useAppSelector } from '../../../app/hooks';
import { selectLogin } from '../../login/loginSlice';
import { setStep, Step } from '../reducer/pessoaSlice';

export type ListausuariosProps = {};

export default function ListaPessoas({}: ListausuariosProps) {
  const [pessoas, setPessoas] = useState([]);
  const login = useAppSelector(selectLogin);
  const dispatch = useAppDispatch();
  useEffect(() => {
    listarPessoas(login).then((value) => setPessoas(value));
  }, []);

  return (
    <Table compact celled padded='very'>
      <Table.Header>
        <Table.Row>
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
            <Table.Row key={index}>
              <Table.Cell selectable>{value.nome}</Table.Cell>
              <Table.Cell>{value.sexo}</Table.Cell>
              <Table.Cell>{value.email}</Table.Cell>
              <Table.Cell>{new Date(value.dataNascimento).toLocaleDateString()}</Table.Cell>
              <Table.Cell>{value.naturalidade}</Table.Cell>
              <Table.Cell>{value.nacionalidade}</Table.Cell>
              <Table.Cell>{value.cpf}</Table.Cell>
              <Table.Cell>
                <Button animated='fade'>
                  <Button.Content hidden>Editar</Button.Content>
                  <Button.Content visible>
                    <Icon name='edit' />
                  </Button.Content>
                </Button>
              </Table.Cell>
            </Table.Row>
          ))}
      </Table.Body>

      <Table.Footer fullWidth>
        <Table.Row>
          <Table.HeaderCell />
          <Table.HeaderCell colSpan='7'>
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
  );
}
