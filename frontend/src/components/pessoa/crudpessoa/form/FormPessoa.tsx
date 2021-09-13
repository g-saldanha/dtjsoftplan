import React from 'react';
import { Button, Container, Form, Message, Segment } from 'semantic-ui-react';
import { PessoaDTO } from '../../PessoaDTO';
import { nacionaliadeOptions, sexoOptions } from './options';
import { InputOnChangeData } from 'semantic-ui-react/dist/commonjs/elements/Input/Input';
import { DropdownProps } from 'semantic-ui-react/dist/commonjs/modules/Dropdown/Dropdown';
import { FormProps } from 'semantic-ui-react/dist/commonjs/collections/Form/Form';
import {
  CPF_INVALIDO,
  DATA_NASCIMENTO_INVALIDA,
  DATA_NASCIMENTO_OBRIGATORIA,
  EMAIL_INVALIDO,
  NOME_OBRIGATORIO,
} from './validator/validateErrors';

export type FormPessoaProps = {
  pessoa: PessoaDTO;
  onChange: Function;
  onSubmit: (event: React.FormEvent<HTMLFormElement>, data: FormProps) => void;
  isEdit: boolean;
  errors: string;
  success: string;
  isSubmitting: boolean;
};

export default function FormPessoa({
  pessoa,
  onChange,
  onSubmit,
  isEdit,
  errors,
  success,
  isSubmitting,
}: FormPessoaProps) {
  const handleChange = (event: React.ChangeEvent<HTMLInputElement>, data: InputOnChangeData) =>
    onChange(data.name, data.value);

  const handleSelect = (event: React.SyntheticEvent<HTMLElement>, data: DropdownProps) => {
    onChange(data.name, data.value);
  };

  const addEdit = isEdit ? 'editado' : 'adicionado';
  return (
    <Container>
      <Segment inverted padded='very'>
        <Form inverted success onSubmit={onSubmit} loading={isSubmitting}>
          <Form.Group widths='equal'>
            <Form.Input
              error={errors === NOME_OBRIGATORIO ? errors : null}
              fluid
              type='text'
              name='nome'
              value={pessoa.nome}
              label='Nome'
              placeholder='Digite o nome'
              required
              onChange={handleChange}
            />
            <Form.Input
              fluid
              error={errors === CPF_INVALIDO ? errors : null}
              type='number'
              label='CPF'
              name='cpf'
              disabled={isEdit}
              value={pessoa.cpf}
              placeholder='Digite o cpf'
              required
              onChange={handleChange}
            />
          </Form.Group>
          <Form.Input
            error={errors === EMAIL_INVALIDO ? errors : null}
            fluid
            type='email'
            label='Email'
            name='email'
            value={pessoa.email}
            placeholder='Digite o email'
            onChange={handleChange}
          />
          <Form.Group widths='equal'>
            <Form.Input
              error={
                [DATA_NASCIMENTO_INVALIDA, DATA_NASCIMENTO_OBRIGATORIA].includes(errors)
                  ? errors
                  : null
              }
              fluid
              label='Data de Nascimento'
              type='date'
              name='dataNascimento'
              value={pessoa.dataNascimento}
              placeholder='Escolha a data de nascimento'
              onChange={handleChange}
              required
            />
            <Form.Select
              fluid
              label='Sexo'
              placeholder='Escolha o Sexo'
              options={sexoOptions}
              name='sexo'
              value={pessoa.sexo}
              onChange={handleSelect}
            />
          </Form.Group>
          <Form.Group widths='equal'>
            <Form.Select
              search
              fluid
              label='Nacionalidade'
              placeholder='Escolha a Nacionalidade'
              options={nacionaliadeOptions}
              name='nacionalidade'
              value={pessoa.nacionalidade}
              onChange={handleSelect}
            />
            <Form.Input
              fluid
              label='Naturalidade'
              type='text'
              name='naturalidade'
              value={pessoa.naturalidade}
              placeholder='Digite a Naturalidade'
              onChange={handleChange}
            />
          </Form.Group>
          <Button type='submit' fluid size='massive' color='green' loading={isSubmitting}>
            {isEdit ? 'Atualizar' : 'Cadastrar'}
          </Button>
          {success && (
            <Message
              success
              header={'Usuário ' + addEdit + ' com sucesso'}
              content={addEdit + ' com sucesso'}
            />
          )}
          {errors && <Message color='red' header={'Erro ao concretizar acão'} content={errors} />}
        </Form>
      </Segment>
    </Container>
  );
}
