import axios from 'axios';
//@ts-ignore
import dayjs from 'dayjs';
import customParseFormat from 'dayjs/plugin/customParseFormat';
dayjs.extend(customParseFormat);

import {
  IEditProfileForm,
  IEditUserForm,
  ILoginForm,
  IPagination,
  IPaymentObj,
  IRegisterForm,
  IResetPasswordForm,
  IShippingForm,
  ISpeciality,
} from '../interfaces';

export const http = axios.create({
 baseURL: 'https://upseam.netlify.app/api/v1',

});

export const Client = {
  removeComment: (id: number) => {
    return http.delete(`/comments/${id}`);
  },

  getComments: (
    clothId: number,
    pagination: IPagination,
    page: number,
    direction: string
  ) => {
    const { pageSize, totalPages } = pagination;
    return http.get(
      `/comments?clothId=${clothId}&page=${page}&pageSize=${pageSize}&totalPages=${totalPages}&direction=${direction}`
    );
  },

  createComment: (userId: number, clothId: number, text: string) => {
    return http.post('/comments', {
      userId,
      text,
      clothId,
    });
  },

  getUserReviewRating: (userId: number) => {
    return http.get(`/reviews/user?userId=${userId}`);
  },
  getReviews: (userId: number, page: number, pageSize: number, direction: string) => {
    return http.get(
      `reviews?userId=${userId}&page=${page}&pageSize=${pageSize}&direction=${direction}`
    );
  },
  createReview: (
    clothId: number,
    revieweeUserId: number,
    reviewerUserId: number,
    rating: number,
    text: string
  ) => {
    return http.post('/reviews', {
      clothId,
      revieweeUserId,
      reviewerUserId,
      rating,
      text,
    });
  },

  createPayment: (obj: IPaymentObj) => {
    return http.post('/payments', {
      amount: obj.amount,
      customerUserId: obj.userId,
      email: obj.email,
      billerUserId: obj.billerUserId,
    });
  },

  updateInvoice: (invoiceId: number) => {
    return http.patch(`/invoices/${invoiceId}`);
  },
  getInvoices: (
    direction: string,
    sortBy: string,
    page: number,
    userId: number,
    pageSize: number
  ) => {
    return http.get(
      `/invoices?direction=${direction}&sort=${sortBy}&page=${page}&userId=${userId}&pageSize=${pageSize}`
    );
  },
  createInvoice: (orderId: number, seamsterId: number, bid: number) => {
    return http.post('/invoices', { orderId, seamsterId, bid });
  },

  removeShipping: (shippingId: number) => {
    return http.delete(`shippings/${shippingId}`);
  },

  getShipping: (userId: number) => {
    return http.get(`/shippings?userId=${userId}`);
  },

  createOrUpdateShipping: (
    userId: number,
    form: IShippingForm,
    shippingType: string,
    shippingValue: string
  ) => {
    return http.post('/shippings', {
      userId,
      zipCode: form.zipCode.value,
      state: form.state.value,
      country: form.country.value,
      address: form.address.value,
      firstName: form.firstName.value,
      lastName: form.lastName.value,
      shippingType,
      shippingValue,
    });
  },

  getProfileShipping: (profileId: number) => {
    return http.get(`/profiles/${profileId}/shipping`);
  },

  updateOrder: (orderId: number, complete: boolean) => {
    return http.patch(`/orders/${orderId}`, { complete });
  },

  getOrders: (userId: number, page: number) => {
    return http.get(`/orders?userId=${userId}&page=${page}`);
  },

  createOrder: (clothId: number, userId: number, bidUserId: number, bid: number) => {
    return http.post('/orders', { clothId, userId, bidUserId, bid });
  },

  selectBid: (clothId: number, bidId: number) => {
    return http.patch(`/clothes/select/${clothId}`, { bidId });
  },

  subscribeToUpseam: (email: string) => {
    return http.post('/subscribers', { email });
  },

  getUsersWithMessages: (currentUserId: number, page: number) => {
    return http.get(`/chat/users?currentUserId=${currentUserId}&page=${page}`);
  },

  getSimpleUser: (userId: number) => {
    return http.get(`/users/${userId}`);
  },

  getChatMessages: (receiverUserId: number, senderUserId: number) => {
    return http.get(
      `/chat?receiverUserId=${receiverUserId}&senderUserId=${senderUserId}`
    );
  },

  sendChatMessage: (message: string, receiverUserId: number, senderUserId: number) => {
    return http.post('/chat', { message, receiverUserId, senderUserId });
  },

  checkIfFriends: (currentUserId: number, userId: number) => {
    return http.post('/friends/check-friend', { currentUserId, userId });
  },
  searchUser: (term: string, page: number) => {
    return http.get(`/users/search?term=${term}&page=${page}`);
  },
  removeFriend: (userId: number, friendId: number) => {
    return http.post('friends/unfriend', { userId, friendId });
  },
  getFriends: (userId: number, page: number, pageSize: number, direction: string) => {
    return http.get(
      `/friends?userId=${userId}&page=${page}&pageSize=${pageSize}&direction=${direction}`
    );
  },
  updateFriendRequest: (
    id: number,
    requesteeId: number,
    requesterId: number,
    action: string
  ) => {
    return http.patch(`/friendships/${id}`, {
      requesteeId,
      requesterId,
      action,
    });
  },
  getFriendRequests: (requestee: number, page: number, pageSize: number) => {
    return http.get(
      `/friendships?requesteeId=${requestee}&page=${page}&pageSize=${pageSize}`
    );
  },
  createFriendRequest: (requestee: number, requester: number) => {
    return http.post(`/friendships`, {
      requestee,
      requester,
    });
  },
  deleteBid: (id: number) => {
    return http.delete(`/bids/${id}`);
  },
  getBids: (clothId: number, page: number, direction: string, pageSize: number) => {
    const url = `/bids?clothId=${clothId}&page=${page}&direction=${direction}&pageSize=${pageSize}`;
    return http.get(url);
  },

  createBid: (userId: number, clothId: number, bid: string) => {
    return http.post('/bids', {
      userId,
      clothId,
      bid,
    });
  },
  getCloth: (clothId: string) => {
    return http.get(`clothes/${clothId}`);
  },
  updateCloth: (
    date: Date,
    description: string,
    file: File | null,
    userId: number,
    size: string,
    clothId: number
  ) => {
    const formData = new FormData();
    if (file !== null) {
      formData.append('file', file);
    }
    formData.append('dueDate', JSON.stringify(date.toISOString().substr(0, 10)));
    formData.append('description', description);
    formData.append('size', size);
    formData.append('userId', userId.toString());

    return http.patch(`/clothes/${clothId}`, formData);
  },
  syncCloth: (clothId: string) => {
    return http.get(`/clothes/sync?clothId=${clothId}`);
  },
  getClothes: (
    fetchType: string,
    page: number,
    pageSize: number,
    direction: string,
    userId?: number
  ) => {
    const url =
      fetchType === 'user'
        ? `/clothes?fetchType=${fetchType}&userId=${userId}`
        : `/clothes?fetchType=${fetchType}`;
    return http.get(`${url}&page=${page}&pageSize=${pageSize}&direction=${direction}`);
  },
  createCloth: (
    date: Date,
    description: string,
    file: File,
    userId: number,
    size: string
  ) => {
    const formData = new FormData();
    formData.append('file', file);
    formData.append('dueDate', JSON.stringify(date.toISOString().substr(0, 10)));
    formData.append('description', description);
    formData.append('userId', userId.toString());
    formData.append('size', size);
    return http.post('/clothes', formData);
  },

  updateProfile: (
    form: IEditProfileForm,
    specialities: ISpeciality[],
    profileId: number
  ) => {
    return http.put(`/profiles/${profileId}`, {
      specialities: JSON.stringify(specialities),
      country: form.country.value,
      state: form.state.value,
      address: form.address.value,
      zipCode: form.zipCode.value,
      site: form.site.value,
      bio: form.bio.value,
      pricing: form.pricing.value,
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
  register: (form: IRegisterForm, isChecked: boolean) => {
    return http.post('/auth/register', {
      firstName: form.firstName.value,
      lastName: form.lastName.value,
      email: form.email.value,
      password: form.password.value,
      confirmPassword: form.confirmPassword.value,
      role: isChecked ? 'SEAMSTER' : 'USER',
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
