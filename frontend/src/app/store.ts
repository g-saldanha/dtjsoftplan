import { Action, configureStore, ThunkAction } from '@reduxjs/toolkit';
import loginReducer from '../components/login/loginSlice';
import pessoaReducer from '../components/pessoa/reducer/pessoaSlice';

export const store = configureStore({
  reducer: {
    auth: loginReducer,
    pessoa: pessoaReducer,
  },
});

export type AppDispatch = typeof store.dispatch;
export type RootState = ReturnType<typeof store.getState>;
export type AppThunk<ReturnType = void> = ThunkAction<
  ReturnType,
  RootState,
  unknown,
  Action<string>
>;
