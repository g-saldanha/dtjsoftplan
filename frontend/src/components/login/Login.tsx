import React, { useState } from 'react';
import { Button, Form, Grid, Header, Label, Message, Segment } from 'semantic-ui-react';
// @ts-ignore
import logo from '../../logo.svg';
import { loginAsync, selectLogin } from './loginSlice';
import { useAppDispatch, useAppSelector } from '../../app/hooks';

export type LoginProps = {};

export default function Login({}: LoginProps) {
  const login = useAppSelector(selectLogin);
  const dispatch = useAppDispatch();
  const [auth, setAuth] = useState({ login: '', senha: '' });
  const [obrigatorios, setObrigatorios] = useState({ login: false, senha: false });
  const handleSave = () => {
    const camposObrigatorios = { login: false, senha: false };
    if (!auth.login) {
      camposObrigatorios.login = true;
    }
    if (!auth.senha) {
      camposObrigatorios.senha = true;
    }
    setObrigatorios(camposObrigatorios);

    if (auth.login && auth.senha) {
      dispatch(loginAsync(auth));
    }
  };
  return (
    <Grid textAlign='center' style={{ height: '100vh' }} verticalAlign='middle'>
      <Grid.Column style={{ maxWidth: 450 }}>
        <Header as='h2' color='teal' textAlign='center'>
          <img src={logo} className='App-logo' alt='logo' />
          Login para o Teste de Softplan
        </Header>
        <Form size='large'>
          <Segment stacked>
            <Form.Field>
              <Form.Input
                fluid
                icon='user'
                iconPosition='left'
                placeholder='Login'
                value={auth.login}
                error={obrigatorios.login}
                onChange={(event) => setAuth({ ...auth, login: event.target.value })}
              />
              {obrigatorios.login && (
                <Label basic color='red' pointing>
                  Campo Obrigatório
                </Label>
              )}
            </Form.Field>
            <Form.Field>
              <Form.Input
                fluid
                icon='lock'
                iconPosition='left'
                placeholder='Senha'
                type='password'
                value={auth.senha}
                error={obrigatorios.senha}
                onChange={(event) => setAuth({ ...auth, senha: event.target.value })}
              />
              {obrigatorios.senha && (
                <Label basic color='red' pointing>
                  Campo Obrigatório
                </Label>
              )}
            </Form.Field>

            <Form.Field>
              <Button color='teal' fluid size='large' loading={login.loading} onClick={handleSave}>
                Login
              </Button>
              {login.error && (
                <Label basic color='red' pointing size='large'>
                  {login.error}
                </Label>
              )}
            </Form.Field>

            <br />
            <Label content='OU' />
            <br />
            <br />
            <Button
              color='google plus'
              fluid
              size='large'
              content='Google Login'
              icon='google'
              disabled={true}
            />
          </Segment>
        </Form>
        <Message>
          <a href='#'>Criar conta</a>
        </Message>
      </Grid.Column>
    </Grid>
  );
}
