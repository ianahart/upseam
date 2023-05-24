export const registerFormState = {
  firstName: { name: 'firstName', value: '', error: '', type: 'text' },
  lastName: { name: 'lastName', value: '', error: '', type: 'text' },
  email: { name: 'email', value: '', error: '', type: 'email' },
  password: { name: 'password', value: '', error: '', type: 'password' },
  confirmPassword: { name: 'confirmPassword', value: '', error: '', type: 'password' },
};

export const userFormState = {
  firstName: { name: 'firstName', value: '', error: '', type: 'text' },
  lastName: { name: 'lastName', value: '', error: '', type: 'text' },
};

export const editProfileState = {
  zipCode: { name: 'zipCode', value: '', error: '', type: 'text' },
  country: { name: 'country', value: '', error: '', type: 'text' },
  address: { name: 'address', value: '', error: '', type: 'text' },
  pricing: { name: 'pricing', value: '', error: '', type: 'text' },
  site: { name: 'site', value: '', error: '', type: 'text' },
  bio: { name: 'bio', value: '', error: '', type: 'text' },
};

export const loginFormState = {
  email: { name: 'email', value: '', error: '', type: 'email' },
  password: { name: 'password', value: '', error: '', type: 'password' },
};

export const tokenState = {
  refreshToken: '',
  token: '',
};

export const userState = {
  id: 0,
  firstName: '',
  lastName: '',
  email: '',
  role: '',
  abbreviation: '',
  isLoggedIn: false,
  profileId: 0,
  avatarUrl: '',
};

export const profileState = {
  id: 0,
  avatarUrl: '',
  avatarFileName: '',
  zipCode: '',
  country: '',
  address: '',
  specialities: [],
  pricing: '',
  site: '',
  bio: '',
};
