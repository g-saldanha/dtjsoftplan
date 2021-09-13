import React from 'react';
import { Button, Container, Form, Message, Segment } from 'semantic-ui-react';
import { PessoaDTO } from '../../PessoaDTO';
import { nacionaliadeOptions, sexoOptions } from './options';
import { InputOnChangeData } from 'semantic-ui-react/dist/commonjs/elements/Input/Input';
import { DropdownProps } from 'semantic-ui-react/dist/commonjs/modules/Dropdown/Dropdown';

export type AdicionapessoaProps = {
  pessoa: PessoaDTO;
  onChange: Function;
};

export default function FormPessoa({ pessoa, onChange }: AdicionapessoaProps) {
  const handleChange = (event: React.ChangeEvent<HTMLInputElement>, data: InputOnChangeData) =>
    onChange(data.name, data.value);

  const handleSelect = (event: React.SyntheticEvent<HTMLElement>, data: DropdownProps) => {
    onChange(data.name, data.value);
  };

  return (
    <Container fluid>
      <Segment inverted padded='very'>
        <Form inverted success>
          <Form.Group widths='equal'>
            <Form.Input
              error='Campo Obrigatório'
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
              error='Campo Obrigatório'
              type='number'
              label='CPF'
              name='cpf'
              value={pessoa.cpf}
              placeholder='Digite o cpf'
              required
              onChange={handleChange}
            />
          </Form.Group>
          <Form.Input
            error='Email inválido'
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
              error='Data inválida, certifique-se que data não é maior que hoje'
              fluid
              label='Data de Nascimento'
              type='date'
              name='dataNascimento'
              value={pessoa.dataNascimento}
              placeholder='Escolha a data de nascimento'
              onChange={handleChange}
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
          <Button type='submit' fluid size='massive' color='green'>
            Cadastrar
          </Button>
          <Message
            success
            header='Usuário adicionado com sucesso'
            content={`Adicionada com sucesso`}
          />
        </Form>
      </Segment>
    </Container>
  );
}
