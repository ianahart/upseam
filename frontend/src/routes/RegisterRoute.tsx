import { Box, Flex } from '@chakra-ui/react';
import registerBG from '../assets/register.jpg';
import Form from '../components/Register/Form';

const RegisterRoute = () => {
  return (
    <Box
      backgroundSize="cover"
      backgroundPosition="center"
      backgroundImage={`url(${registerBG})`}
      minH="100vh"
    >
      <Flex direction="column" align="center" justify="center" minH="100vh">
        <Form
          title="Create your account"
          helperText="Create an account to view and manage your clothes."
        />
      </Flex>
    </Box>
  );
};

export default RegisterRoute;
