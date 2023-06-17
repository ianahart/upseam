import { Box, Heading, Text, Flex } from '@chakra-ui/react';
import { AiOutlineCheckCircle, AiOutlineHome } from 'react-icons/ai';
import { Link as RouterLink } from 'react-router-dom';

const PaymentSuccessRoute = () => {
  return (
    <>
      <Flex align="center" my="3rem" justify="center">
        <Box fontSize="2rem" mr="1.5rem" color="green.200">
          <AiOutlineCheckCircle />
        </Box>
        <Heading color="black.primary">Payment Success!</Heading>
      </Flex>
      <Flex align="center" justify="center">
        <Box color="text.primary" mr="0.5rem">
          <AiOutlineHome />
        </Box>
        <Box color="text.primary" textDecoration="underline">
          <RouterLink to="/">Back to Home</RouterLink>
        </Box>
      </Flex>
    </>
  );
};

export default PaymentSuccessRoute;
