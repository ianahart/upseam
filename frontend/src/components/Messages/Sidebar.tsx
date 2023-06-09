import { Box, Heading, Flex, Input } from '@chakra-ui/react';
import { BiRefresh } from 'react-icons/bi';

const Sidebar = () => {
  return (
    <Box
      borderTopLeftRadius={8}
      borderBottomLeftRadius={8}
      minH="800px"
      bg="blue.500"
      p="0.25rem"
    >
      <Heading color="#fff">Upseam</Heading>
      <Box my="2rem">
        <Input
          color="#fff"
          border="none"
          bg="blue.400"
          _placeholder={{ color: '#fff' }}
          placeholder="Search Here..."
        />
      </Box>
      <Flex justify="flex-start">
        <Box color="#fff" cursor="pointer" fontSize="2rem">
          <BiRefresh />
        </Box>
      </Flex>
    </Box>
  );
};
export default Sidebar;
