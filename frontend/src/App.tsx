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

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path="/" element={<RootLayout />}>
      <Route index element={<HomeRoute />} />
      <Route path="register" element={<RegisterRoute />} />
      <Route path="login" element={<LoginRoute />} />
    </Route>
  )
);

function App() {
  const { updateUser } = useContext(UserContext) as IUserContext;
  const storeUser = useCallback(async () => {
        Client.syncUser(retreiveTokens().token).then((res) => {
            console.log(res)
        }).catch((err) => {
                console.log(err)
            });
  }, []);

  useEffectOnce(() => {
    storeUser();
  });

  return <RouterProvider router={router} />;
}

export default App;
