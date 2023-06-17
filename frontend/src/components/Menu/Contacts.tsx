import { Flex } from '@chakra-ui/react';
import Requests from '../Contacts/Requests';
import UserSearch from '../Contacts/UserSearch';
import Users from '../Contacts/Users';
import Header from './Header';

const Contacts = () => {
  return (
    <>
      <Header heading="Contacts" />
      <Flex mt="0.25rem" direction={['column', 'column', 'row']}>
        <Requests />
        <Flex
          flexGrow={2}
          direction="column"
          alignItems="center"
          justifyContent="space-around"
          border="1px solid"
          borderLeft="none"
          borderColor="light.primary"
          borderBottomRightRadius={8}
          borderTopRightRadius={8}
          minH="100vh"
        >
          <UserSearch />
          <Users />
        </Flex>
      </Flex>
    </>
  );
};

export default Contacts;
