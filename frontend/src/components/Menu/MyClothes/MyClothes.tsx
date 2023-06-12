import { Box, Flex, Spinner, Text } from '@chakra-ui/react';
import { useContext, useEffect, useRef, useState } from 'react';
import { UserContext } from '../../../context/user';
import { IClothesPagination, IUserContext } from '../../../interfaces';
import Pagination from './Pagination';
import { Client } from '../../../util/client';
import { clothesPaginationState } from '../../../state/initialState';
import Header from '../Header';
import Clothes from './Clothes';

const MyClothes = () => {
  const PAGE_SIZE = 3;
  const { user } = useContext(UserContext) as IUserContext;
  const [isLoading, setIsLoading] = useState(false);
  const [pagination, setPagination] =
    useState<IClothesPagination>(clothesPaginationState);

  let shouldRun = useRef(true);

  const fetchClothes = (
    fetchType: string,
    userId: number,
    direction: string,
    paginate: boolean = false
  ) => {
    const pageNum = paginate ? pagination.page : -1;
    setIsLoading(true);
    Client.getClothes(fetchType, pageNum, PAGE_SIZE, direction, userId)
      .then((res) => {
        const { clothes, page, direction } = res.data.data;
        const { content, totalPages } = clothes;
        setPagination((prevState) => ({
          ...prevState,
          content,
          totalPages,
          page,
          direction,
        }));
        setIsLoading(false);
      })
      .catch((err) => {
        setIsLoading(false);
        throw new Error(err.response.data.message);
      });
  };

  useEffect(() => {
    if (shouldRun.current) {
      shouldRun.current = false;
      fetchClothes('user', user.id, 'next');
    }
  }, [shouldRun, fetchClothes]);

  return (
    <Box>
      <Header heading="My Clothes" />
      {isLoading && (
        <Flex justify="center">
          <Spinner
            thickness="4px"
            speed="0.65s"
            emptyColor="gray.200"
            color="blue.500"
            size="xl"
          />
        </Flex>
      )}
      {pagination.content.length === 0 && (
        <Flex justify="center">
          <Text color="text.primary">
            You currently do not have any clothes to be requested.
          </Text>
        </Flex>
      )}
      <Clothes clothes={pagination.content} />
      <Flex justify="center" mt="3rem">
        <Pagination
          userId={user.id}
          fetchType="user"
          page={pagination.page}
          totalPages={pagination.totalPages}
          fetchClothes={fetchClothes}
        />
      </Flex>
    </Box>
  );
};

export default MyClothes;
