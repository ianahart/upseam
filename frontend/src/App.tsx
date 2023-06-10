import './App.css';
import { useCallback } from 'react';
import { retreiveTokens } from './util';
import { Client } from './util/client';
import {
  createBrowserRouter,
  createRoutesFromElements,
  Route,
  RouterProvider,
} from 'react-router-dom';
import RootLayout from './layouts/RootLayout';
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
import SettingsRoute from './routes/SettingsRoute';
import EditProfile from './components/Settings/EditProfile/EditProfile';
import Contacts from './components/Settings/Contacts';
import MyProfile from './components/Settings/MyProfile/MyProfile';
import ForgotPasswordRoute from './routes/ForgotPasswordRoute';
import ResetPasswordRoute from './routes/ResetPasswordRoute';
import ClothesRequests from './components/Settings/User/ClothesRequests';
import RequireUser from './components/Guard/RequireUser';
import MyClothes from './components/Settings/MyClothes/MyClothes';
import EditCloth from './components/Settings/MyClothes/EditCloth';
import AllClothes from './components/Settings/Clothes/AllClothes';
import FullCloth from './components/Settings/Clothes/FullCloth';
import PublicProfile from './components/Profile/PublicProfile';
import MessagesRoute from './routes/MessagesRoute';

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path="/" element={<RootLayout />}>
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
        path="settings/:username"
        element={
          <RequireAuth>
            <SettingsRoute />
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
    </Route>
  )
);

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

  return <RouterProvider router={router} />;
}

export default App;
