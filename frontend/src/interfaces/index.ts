export interface IShippingForm {
  firstName: { name: string; value: string; error: string; type: string };
  lastName: { name: string; value: string; error: string; type: string };
  zipCode: { name: string; value: string; error: string; type: string };
  country: { name: string; value: string; error: string; type: string };
  address: { name: string; value: string; error: string; type: string };
  state: { name: string; value: string; error: string; type: string };
}

export interface IInvoice {
  avatarUrl: string;
  bid: number;
  clothUrl: string;
  firstName: string;
  invoiceId: number;
  lastName: string;
  paid: boolean;
  userId: number;
}

export interface IClothes {
  clothUrl: string;
  createdAt: Date;
  description: string;
  dueDate: string;
  email: string;
  firstName: string;
  id: number;
  lastName: string;
  size: string;
  updatedAt: Date;
  userId: number;
}

export interface IShippingProfile {
  [key: string]: string;
  address: string;
  country: string;
  firstName: string;
  lastName: string;
  state: string;
  zipCode: string;
}

export interface IPreviousAddress {
  id: number;
  address: string;
  country: string;
  firstName: string;
  lastName: string;
  shippingType: string;
  shippingValue: string;
  state: string;
  userId: number;
  zipCode: string;
}

export interface IUserWithMessage {
  receiverUserId: number;
  id: number;
  content: string;
  firstName: string;
  lastName: string;
  avatarUrl: string;
}
export interface IOrder {
  avatarUrl: string;
  clothId: number;
  clothUrl: string;
  complete: boolean;
  dueDate: string;
  firstName: string;
  id: number;
  lastName: string;
  bid: number;
}

export interface ISimpleUserProfile {
  address: string;
  avatarUrl: string;
  country: string;
  email: string;
  firstName: string;
  id: number;
  lastName: string;
  state: string;
  zipCode: string;
}

export interface IChatMessage {
  id: number;
  receiverAvatarUrl: string;
  receiverFirstName: string;
  receiverLastName: string;
  receiverUserId: number;
  senderAvatarUrl: string;
  senderUserId: number;
  content: string;
  createdAt: string;
}

export interface ISearchUser {
  id: number;
  firstName: string;
  lastName: string;
  profileId: number;
  avatarUrl: string;
  role: string;
}

export interface IBid {
  avatarUrl: string;
  id: number;
  createdAt: Date;
  firstName: string;
  lastName: string;
  bid: number;
  profileId: number;
  userId: number;
}

export interface IFriendRequest {
  avatarUrl: string;
  firstName: string;
  id: number;
  lastName: string;
  profileId: number;
  requesteeId: number;
  requesterId: number;
}

export interface IContact {
  avatarUrl: string;
  firstName: string;
  lastName: string;
  profileId: number;
  userId: number;
}

export interface IContactsPagination {
  content: IContact[];
  totalPages: number;
  page: number;
  direction: string;
}

export interface IBidsPagination {
  content: IBid[];
  totalPages: number;
  page: number;
  direction: string;
}

export interface IClothesPagination {
  content: IClothes[];
  totalPages: number;
  page: number;
  direction: string;
}

export interface IFullCloth {
  clothUrl: string;
  createdAt: Date;
  description: string;
  dueDate: string;
  email: string;
  firstName: string;
  id: number;
  lastName: string;
  size: string;
  updatedAt: Date;
  userId: number;
  closed: boolean;
  closedId: number;
}

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
  state: { name: string; value: string; error: string; type: string };
}

export interface ISpeciality {
  id: string;
  text: string;
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
  state: string;
  pricing: string;
  site: string;
  bio: string;
}

export interface IProfilePage {
  firstName: string;
  lastName: string;
  profileUserId: number;
  role: string;
  avatarUrl: string;
  zipCode: string;
  state: string;
  country: string;
  address: string;
  pricing: string;
  site: string;
  bio: string;
  specialities: ISpeciality[];
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
