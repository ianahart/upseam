import { Box, Flex, Heading, Spacer, VStack } from '@chakra-ui/react';
import { Outlet } from 'react-router-dom';
import NavLink from '../components/Navbar/NavLink';
import NestedRoutes from '../components/Menu/NestedRoutes';

const MenuRoute = () => {
  return (
    <Box minH="100vh">
      <Flex className="settingsContainer">
        <NestedRoutes />
        <Box bg="light.primary" width="100%" minH="100vh">
          <Box width={['95%', '95%', '750px']} m="2rem auto 1rem auto" as="header">
            <Heading color="black.primary">My Account</Heading>
          </Box>
          <Box
            margin="3rem auto"
            bg="#fff"
            borderRadius="8px"
            minH="100vh"
            width={['95%', '95%', '95%']}
          >
            <Box maxW="1280px" p="0.5rem" margin=" 0 auto" width={['95%', '95%', '90%']}>
              <Outlet />
            </Box>
          </Box>
        </Box>
      </Flex>
    </Box>
  );
};

export default MenuRoute;
