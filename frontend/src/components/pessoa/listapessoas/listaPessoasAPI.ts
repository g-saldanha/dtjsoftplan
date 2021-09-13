import axios from 'axios';
import { AuthState } from '../../login/loginSlice';
import { pessoaUrl } from '../../../api/Urls';
import { PessoaDTO } from '../PessoaDTO';

export async function listarPessoas(login: AuthState) {
  const axiosResponse = await axios.get(pessoaUrl, {
    auth: {
      username: login.login,
      password: login.senha,
    },
  });
  return axiosResponse.data;
}

export async function adicionaPessoa(login: AuthState, pessoa: PessoaDTO) {
  const pessoaAdicionar = Object.fromEntries(Object.entries(pessoa).filter(([_, v]) => v != ''));
  const axiosResponse = await axios.post(pessoaUrl, pessoaAdicionar, {
    auth: {
      username: login.login,
      password: login.senha,
    },
  });
  return axiosResponse.data;
}

export async function editaPessoa(login: AuthState, pessoa: PessoaDTO) {
  const pessoaAdicionar = Object.fromEntries(Object.entries(pessoa).filter(([_, v]) => v != ''));
  const axiosResponse = await axios.put(pessoaUrl, pessoaAdicionar, {
    auth: {
      username: login.login,
      password: login.senha,
    },
  });
  return axiosResponse.data;
}

export async function removePessoa(login: AuthState, cpf: string) {
  const axiosResponse = await axios.delete(`${pessoaUrl}/${cpf}`, {
    auth: {
      username: login.login,
      password: login.senha,
    },
  });
  return axiosResponse.data;
}
