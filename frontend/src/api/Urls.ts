const baseUrl = process.env.REACT_APP_API_URL;
const loginUrl = `${baseUrl}/usuario/logar`;
const logoutUrl = `${baseUrl}/logout`;
const pessoaUrl = `${baseUrl}/pessoa`;
export { loginUrl, logoutUrl, pessoaUrl };
