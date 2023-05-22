export interface IRegisterForm {
  firstName: { name: string; value: string; error: string; type: string };
  lastName: { name: string; value: string; error: string; type: string };
  email: { name: string; value: string; error: string; type: string };
  password: { name: string; value: string; error: string; type: string };
  confirmPassword: { name: string; value: string; error: string; type: string };
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
}

export interface IUserContext {
  user: IUser;
  stowTokens: (tokens: ITokens) => void;
  updateUser: (user: IUser) => void;
  logout: () => void;
}
