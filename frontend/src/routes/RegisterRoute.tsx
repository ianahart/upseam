import { Box } from '@chakra-ui/react';
import registerBG from '../assets/register.jpg';

const RegisterRoute = () => {
  return (
    <Box
      backgroundSize="cover"
      backgroundPosition="center"
      backgroundImage={`url(${registerBG})`}
      minH="100vh"
    ></Box>
  );
};

export default RegisterRoute;
