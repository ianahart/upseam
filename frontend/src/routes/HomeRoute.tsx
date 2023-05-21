import { Box } from '@chakra-ui/react';
import homeBG from '../assets/home.png';

const HomeRoute = () => {
  return (
    <Box
      backgroundSize="cover"
      backgroundPosition="center"
      backgroundImage={`url(${homeBG})`}
      minH="100vh"
    >
      HomeRoute
    </Box>
  );
};

export default HomeRoute;
