import { AiOutlineHome } from 'react-icons/ai';
import { useNavigate } from 'react-router-dom';
import { BsMap, BsPencil } from 'react-icons/bs';
import { CiLogin, CiSettings } from 'react-icons/ci';
import NavLink from './NavLink';
import { useContext } from 'react';
import { UserContext } from '../../context/user';
import { IUserContext } from '../../interfaces';
import { Box, Button, Flex } from '@chakra-ui/react';
import { AiOutlineLogout } from 'react-icons/ai';
import { Client } from '../../util/client';
import { retreiveTokens, slugify } from '../../util';

const NavLinks = () => {
  const navigate = useNavigate();
  const { user, logout } = useContext(UserContext) as IUserContext;

  const handleLogout = (e: React.MouseEvent<HTMLButtonElement>) => {
    e.stopPropagation();
    Client.logout(retreiveTokens().refreshToken)
      .then(() => {
        logout();
        navigate('/');
      })
      .catch(() => {});
  };

  return (
    <>
      <NavLink to="/" routeName="Home" icon={<AiOutlineHome role="img" />} />
      {!user.isLoggedIn && (
        <NavLink
          to="/register"
          routeName="Create Account"
          icon={<BsPencil role="img" />}
        />
      )}
      {!user.isLoggedIn && (
        <NavLink to="/login" routeName="Login" icon={<CiLogin role="img" />} />
      )}
      {user.isLoggedIn && (
        <NavLink
          to={`menu/${slugify(user.firstName, user.lastName)}/profile`}
          routeName="Menu"
          icon={<BsMap />}
        />
      )}
      {user.isLoggedIn && (
        <Flex align="center">
          <Box color="text.primary" mr="0.25rem" display={['block', 'block', 'none']}>
            <AiOutlineLogout />
          </Box>
          <Button onClick={handleLogout} className="logout-button">
            Logout
          </Button>
        </Flex>
      )}
    </>
  );
};

export default NavLinks;
