import { Box, Flex } from '@chakra-ui/react';
import Form from '../components/Login/Form';
import loginBG from '../assets/login.png';

const LoginRoute = () => {
  return (
    <Box
      backgroundSize="cover"
      backgroundPosition="center"
      backgroundImage={`url(${loginBG})`}
      minH="100vh"
    >
      <Flex direction={['column', 'column', 'row']} width="100%" as="main">
        <Flex pt="5rem" m="0 auto" width={['100%', '95%', '50%']} minH="100vh" bg="#fff">
          <Form title="Login" helperText="Welcome Back, Please login to your account." />
        </Flex>
        <Flex width="50%"></Flex>
      </Flex>
    </Box>
  );
};

export default LoginRoute;
