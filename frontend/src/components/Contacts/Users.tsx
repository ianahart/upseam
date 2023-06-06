import { Box, Heading } from '@chakra-ui/react';
import { useContext, useRef, useEffect, useState } from 'react';
import { UserContext } from '../../context/user';
import { IContactsPagination, IUserContext } from '../../interfaces';
import { Client } from '../../util/client';
import { contactsPaginationState } from '../../state/initialState';
import Pagination from '../Shared/Pagination';

const Users = () => {
  const PAGE_SIZE = 10;
  const { user } = useContext(UserContext) as IUserContext;
  const [pagination, setPagination] = useState<IContactsPagination>(
    contactsPaginationState
  );
  const shouldRun = useRef(true);

  const getFriends = (direction: string, paginate: boolean) => {
    const pageNum = paginate ? pagination.page : -1;
    Client.getFriends(user.id, pageNum, PAGE_SIZE, direction)
      .then((res) => {
        console.log(res);
        const { content, direction, page, totalPages } = res.data.data;
        setPagination((prevState) => ({
          ...prevState,
          content,
          direction,
          page,
          totalPages,
        }));
      })
      .catch((err) => {
        console.log(err);
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
    <Box>
      <Box as="header">
        <Heading color="text.primary" fontSize="1.5rem">
          Contacts
        </Heading>
        {pagination.content.map((contact) => {
          return <Box key={contact.userId}>{contact.userId}</Box>;
        })}
      </Box>
      <Pagination
        fetchData={getFriends}
        totalPages={pagination.totalPages}
        page={pagination.page}
      />
    </Box>
  );
};

export default Users;
