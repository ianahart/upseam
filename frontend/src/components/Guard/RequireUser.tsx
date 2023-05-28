import { useContext, useEffect, useState } from 'react';
import { useLocation, Navigate } from 'react-router-dom';
import { retreiveTokens } from '../../util';
import { UserContext } from '../../context/user';
import { IUserContext } from '../../interfaces';
interface Props {
  children: JSX.Element;
}

//@ts-ignore
const RequireUser: React.FC<Props> = ({ children }): JSX.Element => {
  const { user } = useContext(UserContext) as IUserContext;
  const [isLoading, setIsLoading] = useState(false);
  const location = useLocation();

  useEffect(() => {
    if (user.id !== 0) {
      setIsLoading(true);
    }
  }, [user.role]);

  if (isLoading) {
    let loaded;
    if (isLoading && retreiveTokens()?.token && user.role === 'USER') {
      loaded = true;
    } else {
      loaded = false;
    }
    return loaded ? (
      children
    ) : (
      <Navigate to="/" replace state={{ path: location.pathname }} />
    );
  } else {
    <Navigate to="/" replace state={{ path: location.pathname }} />;
  }
};

export default RequireUser;
