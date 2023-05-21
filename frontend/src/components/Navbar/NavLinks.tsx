import { Link as RouterLink } from 'react-router-dom';
import { AiOutlineHome } from 'react-icons/ai';
import { BsPencil } from 'react-icons/bs';
import { CiLogin } from 'react-icons/ci';
import NavLink from './NavLink';

const NavLinks = () => {
  return (
    <>
      <NavLink to="/" routeName="Home" icon={<AiOutlineHome />} />
      <NavLink to="/register" routeName="Create Account" icon={<BsPencil />} />
      <NavLink to="/login" routeName="Login" icon={<CiLogin />} />
    </>
  );
};

export default NavLinks;