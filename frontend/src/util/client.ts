import axios from 'axios';
import {
  IEditProfileForm,
  IEditUserForm,
  ILoginForm,
  IRegisterForm,
  IResetPasswordForm,
  ISpeciality,
} from '../interfaces';

export const http = axios.create({
  baseURL: 'http://localhost:5173/api/v1',
});

export const Client = {
  updateProfile: (
    form: IEditProfileForm,
    specialities: ISpeciality[],
    profileId: number
  ) => {
    return http.patch(`/profiles/${profileId}`, {
      specialities: JSON.stringify(specialities),
      country: form.country,
      state: form.state,
      address: form.address,
      zipCode: form.zipCode,
      site: form.site,
      bio: form.bio,
      pricing: form.pricing,
    });
  },

  updateUser: (form: IEditUserForm, userId: number) => {
    return http.patch(`/users/${userId}`, {
      firstName: form.firstName.value,
      lastName: form.lastName.value,
    });
  },

  resetPassword: (uid: string | null, token: string | null, form: IResetPasswordForm) => {
    return http.post('/auth/reset-password', {
      id: uid,
      token,
      newPassword: form.password.value,
      confirmPassword: form.confirmPassword.value,
    });
  },
  forgotPassword: (email: string) => {
    console.log(email);
    return http.post('/auth/forgot-password', { email });
  },
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
  logout: (refreshToken: string) => {
    return http.post('/auth/logout', {
      refreshToken,
    });
  },
  syncUser: (token: string) => {
    return http.get('/users/sync', {
      headers: { Authorization: `Bearer ${token}` },
    });
  },
  heartBeat: () => {
    return http.get('/heartbeat');
  },
  uploadPhoto: (file: File, profileId: number) => {
    const formData = new FormData();
    formData.append('file', file);
    formData.append('profileId', profileId.toString());

    return http.post('/profiles/upload', formData);
  },
  getProfile: (profileId: number) => {
    return http.get(`/profiles/${profileId}`);
  },
};
