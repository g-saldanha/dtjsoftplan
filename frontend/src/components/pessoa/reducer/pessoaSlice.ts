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
}

export interface PessoaSlice {
  step: Step | undefined;
  pessoaEditar: PessoaDTO | undefined;
}

const initialState: PessoaSlice = {
  step: undefined,
  pessoaEditar: undefined,
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
  },
});

export const { setPessoaEditar, setStep } = pessoaSlice.actions;

export const selectPessoa = (state: RootState) => state.pessoa;

export default pessoaSlice.reducer;
