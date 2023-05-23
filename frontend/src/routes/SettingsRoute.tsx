import { Box, Flex, Heading, Spacer, VStack } from '@chakra-ui/react';
import { Outlet } from 'react-router-dom';
import NavLink from '../components/Navbar/NavLink';
import NestedRoutes from '../components/Settings/NestedRoutes';

const SettingsRoute = () => {
  return (
    <Box minH="100vh">
      <Flex>
        <NestedRoutes />
        <Box bg="light.primary" width="100%" minH="100vh">
          <Box width={['95%', '95%', '750px']} m="2rem auto 1rem auto" as="header">
            <Heading color="black.primary">Account Settings</Heading>
          </Box>
          <Box
            margin="3rem auto"
            bg="#fff"
            borderRadius="8px"
            minH="100vh"
            width={['95%', '95%', '95%']}
          >
            <Box border="1px solid red" margin=" 0 auto" width={['95%', '95%', '700px']}>
              <Outlet />
            </Box>
          </Box>
        </Box>
      </Flex>
    </Box>
  );
};

export default SettingsRoute;
