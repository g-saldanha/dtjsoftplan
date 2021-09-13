// A mock function to mimic making an async request for data
import { IAuth } from './IAuth';
import axios from 'axios';
import { loginUrl, logoutUrl } from '../../api/Urls';

export async function fetchLogin(auth: IAuth) {
  try {
    return await axios.post(loginUrl, auth);
  } catch (e) {
    const { response }: any = e;
    const message = response.data.split('"');
    console.error(response);
    if (response.status === 404) {
      throw Error(message[1]);
    }
    throw Error(message[1]);
  }
}

export function fetchLogout() {
  axios.post(logoutUrl).then(() => 'deslogado com sucesso');
}
