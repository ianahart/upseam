import { Box, Heading } from '@chakra-ui/react';
import { useContext, useRef, useEffect } from 'react';
import { UserContext } from '../../context/user';
import { IUserContext } from '../../interfaces';

const Users = () => {
  const { user } = useContext(UserContext) as IUserContext;
  const shouldRun = useRef(true);

  useEffect(() => {
    if (shouldRun.current && user.id !== 0) {
      shouldRun.current = false;
    }
  }, [shouldRun, user.id]);

  return (
    <Box>
      <Box as="header">
        <Heading color="text.primary" fontSize="1.5rem">
          Contacts
        </Heading>
      </Box>
    </Box>
  );
};

export default Users;
