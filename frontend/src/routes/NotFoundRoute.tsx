import notFoundImage from '../assets/404.png';
import { Box, Image, Text, Flex } from '@chakra-ui/react';
import { Link as RouterLink } from 'react-router-dom';

const NotFoundRoute = () => {
  return (
    <Box>
      <Flex direction="column" align="center" justify="center" my="2rem" as="header">
        <Text color="text.primary" fontSize="2rem">
          Oops...Page not found!
        </Text>
        <Box color="text.primary" textDecoration="underline">
          <RouterLink to="/">Return Home</RouterLink>
        </Box>
      </Flex>
      <Image src={notFoundImage} alt="a 404 image with woman carrying 404 numbers" />
    </Box>
  );
};

export default NotFoundRoute;
