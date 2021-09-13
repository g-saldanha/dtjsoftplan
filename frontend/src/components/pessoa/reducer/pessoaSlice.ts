import { PessoaDTO } from '../PessoaDTO';
import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { RootState } from '../../../app/store';

export enum Step {
  // eslint-disable-next-line no-unused-vars
  EDITAR,
  // eslint-disable-next-line no-unused-vars
  ADICIONAR,
  // eslint-disable-next-line no-unused-vars
  LISTAR,
  // eslint-disable-next-line no-unused-vars
  REMOVER,
}

export interface PessoaSlice {
  step: Step | undefined;
  pessoaEditar: PessoaDTO | undefined;
  isRemove: boolean;
}

const initialState: PessoaSlice = {
  step: undefined,
  pessoaEditar: undefined,
  isRemove: false,
};

export const pessoaSlice = createSlice({
  name: 'pessoa',
  initialState,
  reducers: {
    setStep: (state, action: PayloadAction<Step | undefined>) => {
      state.step = action.payload;
    },
    setPessoaEditar: (state, action: PayloadAction<PessoaDTO | undefined>) => {
      state.pessoaEditar = action.payload;
    },
    setRemoveModal: (state, action: PayloadAction<boolean>) => {
      state.isRemove = action.payload;
    },
  },
});

export const { setPessoaEditar, setStep, setRemoveModal } = pessoaSlice.actions;

export const selectPessoa = (state: RootState) => state.pessoa;

export default pessoaSlice.reducer;
