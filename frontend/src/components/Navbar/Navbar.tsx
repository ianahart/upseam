import { Box, Image, Flex, Heading, Spacer, HStack } from '@chakra-ui/react';
import Avatar from '../Shared/Avatar';
import logo from '../../assets/logo.png';
import { useContext } from 'react';
import NavLinks from './NavLinks';
import NavDrawer from './NavDrawer';
import { UserContext } from '../../context/user';
import { IUserContext } from '../../interfaces';

const Navbar = () => {
  const { user } = useContext(UserContext) as IUserContext;
  return (
    <Flex bg="transparent" as="nav">
      <Box display="flex" alignItems="center" m="0.5rem">
        <Image src={logo} mr="0.5rem" />
        <Heading color="blackAlpha.700" as="h3">
          Upsteam
        </Heading>
      </Box>
      <Spacer />
      <HStack display={['none', 'none', 'flex']} spacing="20px" m="0.5rem">
        <NavLinks />
        {user.isLoggedIn && <Avatar />}
      </HStack>
      <NavDrawer />
    </Flex>
  );
};

export default Navbar;
