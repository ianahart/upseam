import { Box, Flex } from '@chakra-ui/react';
import { useContext, useEffect, useRef, useState } from 'react';
import { UserContext } from '../../../context/user';
import { IClothesPagination, IUserContext } from '../../../interfaces';
import Pagination from './Pagination';
import { Client } from '../../../util/client';
import { clothesPaginationState } from '../../../state/initialState';
import FormHeader from '../EditProfile/FormHeader';
import Header from '../Header';
import Clothes from './Clothes';

const MyClothes = () => {
  const PAGE_SIZE = 3;
  const { user } = useContext(UserContext) as IUserContext;
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
      })
      .catch((err) => {
        console.log(err);
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
      <Clothes clothes={pagination.content} />
      <Flex justify="center">
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
