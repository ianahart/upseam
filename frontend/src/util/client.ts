import axios from 'axios';
import { ILoginForm, IRegisterForm } from '../interfaces';

export const http = axios.create({
  baseURL: 'http://localhost:5173/api/v1',
});

export const Client = {
  register: (form: IRegisterForm) => {
    return http.post('/auth/register', {
      firstName: form.firstName.value,
      lastName: form.lastName.value,
      email: form.email.value,
      password: form.password.value,
      confirmPassword: form.confirmPassword.value,
      role: 'USER',
    });
  },
  login: (form: ILoginForm) => {
    return http.post('/auth/login', {
      email: form.email.value,
      password: form.password.value,
    });
  },
  syncUser: (token: string) => {
    return http.get('/user/sync', {
      headers: { Authorization: `Bearer ${token}` },
    });
  },
};