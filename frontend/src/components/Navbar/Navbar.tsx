import { Box, Image, Flex, Heading, Spacer, HStack } from '@chakra-ui/react';
import { Link as RouterLink } from 'react-router-dom';
import logo from '../../assets/logo.png';
import { useContext } from 'react';
import { RxHamburgerMenu } from 'react-icons/rx';
import NavLinks from './NavLinks';
import NavDrawer from './NavDrawer';
import InitialIcon from '../Shared/InitialIcon';
import { UserContext } from '../../context/user';
import { IUserContext } from '../../interfaces';

const Navbar = () => {
  const { user } = useContext(UserContext) as IUserContext;
  return (
    <Flex bg="#323292" as="nav">
      <Box display="flex" alignItems="center" m="0.5rem">
        <Image src={logo} mr="0.5rem" />
        <Heading color="blackAlpha.700" as="h3">
          Upsteam
        </Heading>
      </Box>
      <Spacer />
      <HStack display={['none', 'none', 'flex']} spacing="20px" m="0.5rem">
        <NavLinks />
        {user.isLoggedIn && <InitialIcon />}
      </HStack>
      <NavDrawer />
    </Flex>
  );
};

export default Navbar;
