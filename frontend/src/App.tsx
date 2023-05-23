import './App.css';
import { useCallback } from 'react';
import { retreiveTokens } from './util';
import { Client } from './util/client';
import {
  createBrowserRouter,
  createRoutesFromElements,
  Route,
  Routes,
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
import WithAxios from './hooks/withAxios';
import RequireAuth from './components/Guard/RequireAuth';
import HeartBeatRoute from './routes/HeartBeatRoute';
import SettingsRoute from './routes/SettingsRoute';
import EditProfile from './components/Settings/EditProfile';
import Contacts from './components/Settings/Contacts';

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
      </Route>
    </Route>
  )
);

function App() {
  const { updateUser, stowTokens } = useContext(UserContext) as IUserContext;
  const storeUser = useCallback(async () => {
    Client.syncUser(retreiveTokens().token)
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
