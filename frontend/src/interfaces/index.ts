export interface IRegisterForm {
  firstName: { name: string; value: string; error: string; type: string };
  lastName: { name: string; value: string; error: string; type: string };
  email: { name: string; value: string; error: string; type: string };
  password: { name: string; value: string; error: string; type: string };
  confirmPassword: { name: string; value: string; error: string; type: string };
}

export interface IResetPasswordForm {
  password: { name: string; value: string; error: string; type: string };
  confirmPassword: { name: string; value: string; error: string; type: string };
}

export interface IEditUserForm {
  firstName: { name: string; value: string; error: string; type: string };
  lastName: { name: string; value: string; error: string; type: string };
}

export interface IForgotPasswordForm {
  email: { name: string; value: string; error: string; type: string };
}

export interface IEditProfileForm {
  zipCode: { name: string; value: string; error: string; type: string };
  country: { name: string; value: string; error: string; type: string };
  address: { name: string; value: string; error: string; type: string };
  pricing: { name: string; value: string; error: string; type: string };
  site: { name: string; value: string; error: string; type: string };
  bio: { name: string; value: string; error: string; type: string };
}

export interface ILoginForm {
  email: { name: string; value: string; error: string; type: string };
  password: { name: string; value: string; error: string; type: string };
}

export interface ITokens {
  token: string;
  refreshToken: string;
}

export interface IUser {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  role: string;
  abbreviation: string;
  isLoggedIn: boolean;
  profileId: number;
  avatarUrl: string;
}

export interface IProfile {
  id: number;
  avatarUrl: string;
  avatarFileName: string;
  zipCode: string;
  country: string;
  address: string;
  specialities: string[];
  pricing: string;
  site: string;
  bio: string;
}

export interface IMinimalUser {
  firstName: string;
  lastName: string;
}

export interface IMinimalSpecialities {
  address: string;
  country: string;
  bio: string;
  site: string;
  pricing: string;
}

export interface IUserContext {
  user: IUser;
  stowTokens: (tokens: ITokens) => void;
  updateUser: (user: IUser) => void;
  logout: () => void;
}
