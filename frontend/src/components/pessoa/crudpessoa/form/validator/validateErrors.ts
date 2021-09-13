export const NOME_OBRIGATORIO = 'Nome da pessoa é obrigatório';
export const CPF_INVALIDO = 'CPF tem que ser um cpf válido';
export const EMAIL_INVALIDO = 'Email tem que ser um email válido';
export const DATA_NASCIMENTO_OBRIGATORIA = 'Data de Nascimento é obrigatória';
export const DATA_NASCIMENTO_INVALIDA = 'Data de Nascimento inválida';
export const JA_EXISTE = 'Pessoa já existe';
export const NAO_EXISTE = 'Pessoa não existe';

export default function validateErrors(error: string) {
  if (error.includes(NOME_OBRIGATORIO)) {
    return NOME_OBRIGATORIO;
  }
  if (error.includes(CPF_INVALIDO)) {
    return CPF_INVALIDO;
  }
  if (error.includes(EMAIL_INVALIDO)) {
    return EMAIL_INVALIDO;
  }
  if (error.includes(DATA_NASCIMENTO_OBRIGATORIA)) {
    return DATA_NASCIMENTO_OBRIGATORIA;
  }
  if (error.includes(DATA_NASCIMENTO_INVALIDA)) {
    return DATA_NASCIMENTO_INVALIDA;
  }
  if (error.includes(JA_EXISTE)) {
    return JA_EXISTE;
  }
  if (error.includes(NAO_EXISTE)) {
    return NAO_EXISTE;
  }

  return 'Ocorreu um erro inesperado, tente novamente';
}
