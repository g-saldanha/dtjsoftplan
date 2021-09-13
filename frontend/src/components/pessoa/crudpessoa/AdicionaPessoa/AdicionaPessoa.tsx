import React, { useState } from 'react';
import FormPessoa from '../form/FormPessoa';

export type AdicionapessoaProps = {};

export default function Adicionapessoa({}: AdicionapessoaProps) {
  const [pessoa, setPessoa] = useState({
    nome: '',
    sexo: '',
    email: '',
    dataNascimento: '',
    naturalidade: '',
    nacionalidade: '',
    cpf: '',
  });
  const handleChange = (name: string, value: string) => setPessoa({ ...pessoa, [name]: value });
  return <FormPessoa pessoa={pessoa} onChange={handleChange} />;
}
