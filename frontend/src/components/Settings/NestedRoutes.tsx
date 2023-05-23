import { Box, Flex, Button } from '@chakra-ui/react';
import NavLink from '../Navbar/NavLink';

const NestedRoutes = () => {
  return (
    <Flex direction="column" m="1rem" width="350px" minH="100vh">
      <Box my="0.5rem">
        <NavLink fontWeight={false} to="edit-profile" routeName="Edit Profile" />
      </Box>
      <Box my="0.5rem">
        <NavLink fontWeight={false} to="contacts" routeName="Contacts" />
      </Box>
      <Box my="1rem">
        <Button
          _hover={{ background: 'transparent' }}
          border="none"
          color="red.500"
          p="0"
          backgroundColor="transparent"
        >
          Delete Account
        </Button>
      </Box>
    </Flex>
  );
};

export default NestedRoutes;
