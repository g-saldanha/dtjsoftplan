import React, { useState } from 'react';
import FormPessoa from '../form/FormPessoa';
import { adicionaPessoa } from '../../listapessoas/listaPessoasAPI';
import { useAppSelector } from '../../../../app/hooks';
import { selectLogin } from '../../../login/loginSlice';
import validateErrors from '../form/validator/validateErrors';

export default function Adicionapessoa() {
  const selector = useAppSelector(selectLogin);
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');
  const [isLoading, setIsLoading] = useState(false);
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
  const handleSubmit = () => {
    setIsLoading(true);
    adicionaPessoa(selector, pessoa)
      .then((value) => {
        setSuccess(value);
        setIsLoading(false);
      })
      .catch((reason) => {
        setError(validateErrors(reason.response.data));
        setIsLoading(false);
      });
  };
  return (
    <FormPessoa
      pessoa={pessoa}
      onChange={handleChange}
      onSubmit={handleSubmit}
      isEdit={false}
      errors={error}
      success={success}
      isSubmitting={isLoading}
    />
  );
}
