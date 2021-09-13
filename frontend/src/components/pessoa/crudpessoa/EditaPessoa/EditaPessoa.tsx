import React, { useState } from 'react';
import FormPessoa from '../form/FormPessoa';
import { useAppSelector } from '../../../../app/hooks';
import { selectPessoa } from '../../reducer/pessoaSlice';
import { editaPessoa } from '../../listapessoas/listaPessoasAPI';
import { selectLogin } from '../../../login/loginSlice';
import validateErrors from '../form/validator/validateErrors';

export default function EditaPessoa() {
  const selector = useAppSelector(selectPessoa);
  const authSelector = useAppSelector(selectLogin);
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');
  const [isLoading, setIsLoading] = useState(false);

  const [pessoa, setPessoa] = useState(selector.pessoaEditar);
  if (!pessoa) {
    return null;
  }
  const handleChange = (name: string, value: string) => setPessoa({ ...pessoa, [name]: value });
  const handleSubmit = () => {
    setIsLoading(true);
    editaPessoa(authSelector, pessoa)
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
      isEdit
      errors={error}
      success={success}
      isSubmitting={isLoading}
    />
  );
}
