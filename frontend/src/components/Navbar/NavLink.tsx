import { Link as RouterLink } from 'react-router-dom';
import { Flex, Box } from '@chakra-ui/react';

interface INavLinkProps {
  to: string;
  routeName: string;
  icon: JSX.Element;
}

const NavLink = ({ to, routeName, icon }: INavLinkProps) => {
  return (
    <Flex alignItems="center">
      <RouterLink className="routerLink" to={to}>
        <Box color="text.primary" mr="0.25rem" display={['block', 'block', 'none']}>
          {icon}
        </Box>
        {routeName}
      </RouterLink>
    </Flex>
  );
};

export default NavLink;
