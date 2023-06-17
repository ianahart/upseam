import { Flex, Box } from '@chakra-ui/react';
import { BsChevronRight } from 'react-icons/bs';
import NavLink from '../Navbar/NavLink';

interface INestedRouteProps {
  to: string;
  routeName: string;
  icon: JSX.Element;
  activeRoute: string;
}

const NestedRoute = ({ to, routeName, icon, activeRoute }: INestedRouteProps) => {
  return (
    <Flex align="center" justify="space-between" color="text.primary" my="0.5rem">
      <Flex align="center">
        <Box mr="0.25rem">{icon}</Box>
        <NavLink to={to} routeName={routeName} />
      </Flex>
      {to === activeRoute && <BsChevronRight />}
    </Flex>
  );
};

export default NestedRoute;
