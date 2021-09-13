import axios from 'axios';
import { AuthState } from '../../login/loginSlice';
import { urlGetPessoas } from '../../../api/Urls';

export async function listarPessoas(login: AuthState) {
  const axiosResponse = await axios.get(urlGetPessoas, {
    auth: {
      username: login.login,
      password: login.senha,
    },
  });
  return axiosResponse.data;
}
