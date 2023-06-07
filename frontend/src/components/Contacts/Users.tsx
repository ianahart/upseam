import { Box, Flex, Heading, Spinner } from '@chakra-ui/react';
import { useContext, useRef, useEffect, useState } from 'react';
import { UserContext } from '../../context/user';
import { IContactsPagination, IUserContext } from '../../interfaces';
import { Client } from '../../util/client';
import { contactsPaginationState } from '../../state/initialState';
import Pagination from '../Shared/Pagination';
import User from './User';

const Users = () => {
  const PAGE_SIZE = 10;
  const { user } = useContext(UserContext) as IUserContext;
  const [isLoading, setIsLoading] = useState(false);
  const [pagination, setPagination] = useState<IContactsPagination>(
    contactsPaginationState
  );
  const shouldRun = useRef(true);

  const handleRemoveFriend = (id: number) => {
    setPagination((prevState) => ({
      ...prevState,
      content: [...prevState.content.filter((x) => x.userId !== id)],
    }));
  };

  const getFriends = (direction: string, paginate: boolean) => {
    const pageNum = paginate ? pagination.page : -1;
    setIsLoading(true);
    Client.getFriends(user.id, pageNum, PAGE_SIZE, direction)
      .then((res) => {
        const { content, direction, page, totalPages } = res.data.data;
        setPagination((prevState) => ({
          ...prevState,
          content,
          direction,
          page,
          totalPages,
        }));
        setIsLoading(false);
      })
      .catch((err) => {
        setIsLoading(false);
        throw new Error(err.response.data.message);
      });
  };

  useEffect(() => {
    if (shouldRun.current && user.id !== 0) {
      shouldRun.current = false;
      getFriends('next', false);
    }
  }, [shouldRun, user.id]);

  return (
    <Box width="100%">
      <Box as="header">
        <Heading textAlign="center" color="text.primary" fontSize="1.5rem">
          Contacts
        </Heading>
        {isLoading && (
          <Spinner
            thickness="4px"
            speed="0.65s"
            emptyColor="gray.200"
            color="blue.500"
            size="xl"
          />
        )}
      </Box>
      <Box>
        {pagination.content.map((contact) => {
          return (
            <User
              key={contact.userId}
              handleRemoveFriend={handleRemoveFriend}
              contact={contact}
            />
          );
        })}
      </Box>
      <Flex justify="center" my="0.5rem">
        <Pagination
          fetchData={getFriends}
          totalPages={pagination.totalPages}
          page={pagination.page}
        />
      </Flex>
    </Box>
  );
};

export default Users;
