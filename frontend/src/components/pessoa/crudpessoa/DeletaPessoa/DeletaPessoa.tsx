import React from 'react';
import { Button, Header, Icon, Modal } from 'semantic-ui-react';
import { useAppDispatch, useAppSelector } from '../../../../app/hooks';
import { selectPessoa, setPessoaEditar, setRemoveModal } from '../../reducer/pessoaSlice';
import { removePessoa } from '../../listapessoas/listaPessoasAPI';
import { selectLogin } from '../../../login/loginSlice';

export type DeletapessoaProps = {
  onSuccess: Function;
  onError: Function;
};

export default function DeletaPessoa({ onSuccess, onError }: DeletapessoaProps) {
  const selector = useAppSelector(selectPessoa);
  const login = useAppSelector(selectLogin);
  const dispatch = useAppDispatch();
  const handleClose = () => {
    dispatch(setPessoaEditar(undefined));
    dispatch(setRemoveModal(false));
  };
  const handleOpen = () => {
    dispatch(setRemoveModal(true));
  };
  const handleDelete = () => {
    if (selector?.pessoaEditar?.cpf)
      removePessoa(login, selector.pessoaEditar.cpf)
        .then((response) => {
          onSuccess(response);
          handleClose();
          setTimeout(() => onSuccess(''), 2000);
        })
        .catch((reason) => {
          onError(reason.response.data);
          handleClose();
          setTimeout(() => onError(''), 2000);
        });
  };

  if (!selector.pessoaEditar) {
    return null;
  }

  return (
    <Modal basic onClose={handleClose} onOpen={handleOpen} open={selector.isRemove} size='small'>
      <Header icon>
        <Icon name='delete' />
        Remover Pessoa da lista
      </Header>
      <Modal.Content>
        <p>Deseja remover {selector.pessoaEditar.nome}</p>
      </Modal.Content>
      <Modal.Actions>
        <Button basic color='red' inverted onClick={handleDelete}>
          <Icon name='remove' /> Sim
        </Button>
        <Button color='green' inverted onClick={handleClose}>
          <Icon name='close' /> Não
        </Button>
      </Modal.Actions>
    </Modal>
  );
}
