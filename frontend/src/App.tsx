import React, { useEffect } from 'react';
import { useAppDispatch, useAppSelector } from './app/hooks';
import { selectLogin } from './components/login/loginSlice';
import Login from './components/login/Login';
import './App.css';
import { selectPessoa, setStep, Step } from './components/pessoa/reducer/pessoaSlice';
import ListaPessoas from './components/pessoa/listapessoas/ListaPessoas';
import Adicionapessoa from './components/pessoa/crudpessoa/AdicionaPessoa/AdicionaPessoa';
import { Breadcrumb } from 'semantic-ui-react';
import EditaPessoa from './components/pessoa/crudpessoa/EditaPessoa/EditaPessoa';

export default function App() {
  const auth = useAppSelector(selectLogin);
  const pessoaRedux = useAppSelector(selectPessoa);
  const dispatch = useAppDispatch();
  const onListar = () => dispatch(setStep(Step.LISTAR));

  useEffect(() => {
    if (auth.login && auth.senha) {
      dispatch(setStep(Step.LISTAR));
    }
  }, [auth]);

  if (!auth.login && !auth.senha) {
    return (
      <div className='App'>
        <header className='App-header'>
          <Breadcrumb size='massive'>
            <Breadcrumb.Section active>Login</Breadcrumb.Section>
          </Breadcrumb>
          <Login />
        </header>
      </div>
    );
  }

  if (pessoaRedux.step == Step.LISTAR) {
    return (
      <div className='App'>
        <header className='App-header'>
          <Breadcrumb size='massive'>
            <Breadcrumb.Section active>Listar</Breadcrumb.Section>
          </Breadcrumb>
          <ListaPessoas />
        </header>
      </div>
    );
  }

  if (pessoaRedux.step == Step.ADICIONAR) {
    return (
      <div className='App'>
        <header className='App-header'>
          <Breadcrumb size='massive'>
            <Breadcrumb.Section link onClick={onListar}>
              Listar
            </Breadcrumb.Section>
            <Breadcrumb.Divider icon='right chevron' />
            <Breadcrumb.Section active>Adicionar Pessoa</Breadcrumb.Section>
          </Breadcrumb>
          <Adicionapessoa />
        </header>
      </div>
    );
  }

  if (pessoaRedux.step == Step.EDITAR && pessoaRedux.pessoaEditar) {
    return (
      <div className='App'>
        <header className='App-header'>
          <Breadcrumb size='massive'>
            <Breadcrumb.Section link onClick={onListar}>
              Listar
            </Breadcrumb.Section>
            <Breadcrumb.Divider icon='right chevron' />
            <Breadcrumb.Section active>Editar Pessoa</Breadcrumb.Section>
          </Breadcrumb>
          <EditaPessoa />
        </header>
      </div>
    );
  }

  return (
    <div className='App'>
      <header className='App-header'>
        <Breadcrumb size='massive'>
          <Breadcrumb.Section active>Login</Breadcrumb.Section>
        </Breadcrumb>
        <Login />
      </header>
    </div>
  );
}
