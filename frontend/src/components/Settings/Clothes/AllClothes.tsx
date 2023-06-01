import { Box, Flex, Grid, GridItem, Image, Spinner } from '@chakra-ui/react';
import { useContext, useState } from 'react';
import { Link as RouterLink } from 'react-router-dom';
import Header from '../Header';
import { useEffectOnce } from '../../../hooks/useEffectOnce';
import { Client } from '../../../util/client';
import { clothesPaginationState } from '../../../state/initialState';
import { IClothesPagination, IUserContext } from '../../../interfaces';
import Pagination from './Pagination';
import { slugify } from '../../../util';
import { UserContext } from '../../../context/user';

const AllClothes = () => {
  const { user } = useContext(UserContext) as IUserContext;
  const [isLoading, setIsLoading] = useState(false);
  const PAGE_SIZE = 3;
  const [pagination, setPagination] =
    useState<IClothesPagination>(clothesPaginationState);

  useEffectOnce(() => {
    getClothes(false, pagination.page, 'next');
  });

  const getClothes = (paginate: boolean, page: number, direction: string) => {
    const pageNum = paginate ? page : -1;
    setIsLoading(true);
    Client.getClothes('public', pageNum, PAGE_SIZE, direction)
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

  return (
    <>
      {isLoading ? (
        <Flex direction="column" align="center" justify="center">
          <Spinner
            thickness="4px"
            speed="0.65s"
            emptyColor="gray.200"
            color="blue.500"
            size="xl"
          />
        </Flex>
      ) : (
        <Box>
          <Header heading="All Clothes" />
          <Grid mt="3rem" gridTemplateColumns={['1fr', '1fr', '1fr 1fr 1fr']} gap="15px">
            {pagination.content.map((cloth) => {
              return (
                <GridItem key={cloth.id}>
                  <RouterLink
                    to={`/settings/${slugify(user.firstName, user.lastName)}/clothes/${
                      cloth.id
                    }`}
                  >
                    <Image
                      boxShadow="rgba(100, 100, 111, 0.2) 0px 7px 29px 0px"
                      borderRadius="8px"
                      src={cloth.clothUrl}
                      alt={cloth.description}
                    />
                  </RouterLink>
                </GridItem>
              );
            })}
          </Grid>

          <Flex justify="center" mt="3rem">
            <Pagination
              fetchType="public"
              page={pagination.page}
              totalPages={pagination.totalPages}
              fetchClothes={getClothes}
            />
          </Flex>
        </Box>
      )}
    </>
  );
};

export default AllClothes;
