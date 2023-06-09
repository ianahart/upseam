import './App.css';
import { useCallback } from 'react';
import { retreiveTokens } from './util';
import { Client } from './util/client';
import { Box } from '@chakra-ui/react';
import { Navigate, Routes, Route, BrowserRouter as Router } from 'react-router-dom';
import Footer from './components/Footer/Footer';
import HomeRoute from './routes/HomeRoute';
import RegisterRoute from './routes/RegisterRoute';
import LoginRoute from './routes/LoginRoute';
import { useContext } from 'react';
import { UserContext } from './context/user';
import { IUserContext } from './interfaces';
import { useEffectOnce } from './hooks/useEffectOnce';
import RequireGuest from './components/Guard/RequireGuest';
import RequireAuth from './components/Guard/RequireAuth';
import HeartBeatRoute from './routes/HeartBeatRoute';
import MenuRoute from './routes/MenuRoute';
import EditProfile from './components/Menu/EditProfile/EditProfile';
import Contacts from './components/Menu/Contacts';
import MyProfile from './components/Menu/MyProfile/MyProfile';
import ForgotPasswordRoute from './routes/ForgotPasswordRoute';
import ResetPasswordRoute from './routes/ResetPasswordRoute';
import ClothesRequests from './components/Menu/User/ClothesRequests';
import RequireUser from './components/Guard/RequireUser';
import MyClothes from './components/Menu/MyClothes/MyClothes';
import EditCloth from './components/Menu/MyClothes/EditCloth';
import AllClothes from './components/Menu/Clothes/AllClothes';
import FullCloth from './components/Menu/Clothes/FullCloth';
import PublicProfile from './components/Profile/PublicProfile';
import MessagesRoute from './routes/MessagesRoute';
import NotFoundRoute from './routes/NotFoundRoute';
import Orders from './components/Menu/Order/Orders';
import Shipping from './components/Shipping/Shipping';
import InvoicesRoute from './routes/InvoicesRoute';
import PaymentSuccessRoute from './routes/PaymentSuccessRoute';
import Reviews from './components/Review/Reviews';
import Navbar from './components/Navbar/Navbar';
import WithAxios from './hooks/withAxios';

function App() {
  const { updateUser, stowTokens } = useContext(UserContext) as IUserContext;
  const storeUser = useCallback(async () => {
    Client.syncUser(retreiveTokens()?.token)
      .then((res) => {
        updateUser(res.data);
        stowTokens(retreiveTokens());
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  useEffectOnce(() => {
    storeUser();
  });

  return (
    <Box as="main" className="App">
      <Router>
        <Navbar />
        <Box minH="100vh">
          <WithAxios>
            <Routes>
              <Route index element={<HomeRoute />} />
              <Route
                path="register"
                element={
                  <RequireGuest>
                    <RegisterRoute />
                  </RequireGuest>
                }
              />
              <Route
                path=":username/profile"
                element={
                  <RequireAuth>
                    <PublicProfile />
                  </RequireAuth>
                }
              />
              <Route
                path="payment-success"
                element={
                  <RequireAuth>
                    <PaymentSuccessRoute />
                  </RequireAuth>
                }
              />
              <Route
                path="forgot-password"
                element={
                  <RequireGuest>
                    <ForgotPasswordRoute />
                  </RequireGuest>
                }
              />
              <Route
                path="reset-password"
                element={
                  <RequireGuest>
                    <ResetPasswordRoute />
                  </RequireGuest>
                }
              />

              <Route
                path="login"
                element={
                  <RequireGuest>
                    <LoginRoute />
                  </RequireGuest>
                }
              />
              <Route
                path="heartbeat"
                element={
                  <RequireAuth>
                    <HeartBeatRoute />
                  </RequireAuth>
                }
              />

              <Route
                path="menu/:username"
                element={
                  <RequireAuth>
                    <MenuRoute />
                  </RequireAuth>
                }
              >
                <Route
                  path="edit-profile"
                  element={
                    <RequireAuth>
                      <EditProfile />
                    </RequireAuth>
                  }
                />
                <Route
                  path="invoices"
                  element={
                    <RequireUser>
                      <InvoicesRoute />
                    </RequireUser>
                  }
                />

                <Route
                  path="reviews"
                  element={
                    <RequireAuth>
                      <Reviews />
                    </RequireAuth>
                  }
                />

                <Route
                  path="contacts"
                  element={
                    <RequireAuth>
                      <Contacts />
                    </RequireAuth>
                  }
                />

                <Route
                  path="messages"
                  element={
                    <RequireAuth>
                      <MessagesRoute />
                    </RequireAuth>
                  }
                />

                <Route
                  path="shipping"
                  element={
                    <RequireUser>
                      <Shipping />
                    </RequireUser>
                  }
                />

                <Route
                  path="orders"
                  element={
                    <RequireAuth>
                      <Orders />
                    </RequireAuth>
                  }
                />
                <Route
                  path="profile"
                  element={
                    <RequireAuth>
                      <MyProfile />
                    </RequireAuth>
                  }
                />
                <Route
                  path="requests"
                  element={
                    <RequireUser>
                      <ClothesRequests />
                    </RequireUser>
                  }
                />
                <Route
                  path="my-clothes"
                  element={
                    <RequireUser>
                      <MyClothes />
                    </RequireUser>
                  }
                />
                <Route
                  path="clothes/edit/:clothId"
                  element={
                    <RequireUser>
                      <EditCloth />
                    </RequireUser>
                  }
                />
                <Route
                  path="clothes/:clothId"
                  element={
                    <RequireAuth>
                      <FullCloth />
                    </RequireAuth>
                  }
                />

                <Route
                  path="clothes"
                  element={
                    <RequireAuth>
                      <AllClothes />
                    </RequireAuth>
                  }
                />
              </Route>
              <Route path="/404" element={<NotFoundRoute />} />
              <Route path="*" element={<Navigate to="/404" replace />} />
            </Routes>
          </WithAxios>
          <Footer />
        </Box>
      </Router>
    </Box>
  );
}

export default App;
