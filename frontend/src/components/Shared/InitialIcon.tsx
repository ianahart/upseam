import { Box, Flex } from '@chakra-ui/react';
import { useContext } from 'react';
import { UserContext } from '../../context/user';
import { IUserContext } from '../../interfaces';

const InitialIcon = () => {
  const { user } = useContext(UserContext) as IUserContext;
  return (
    <Flex
      justify="center"
      align="center"
      color="#fff"
      direction="column"
      width="50px"
      height="50px"
      borderRadius="50%"
      bg="blue.500"
    >
      {user.abbreviation}
    </Flex>
  );
};

export default InitialIcon;
