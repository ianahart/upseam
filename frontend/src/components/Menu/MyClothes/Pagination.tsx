import { Box, Button, Flex } from '@chakra-ui/react';

interface IPaginationProps {
  fetchClothes: (
    fetchType: string,
    userId: number,
    direction: string,
    paginate: boolean
  ) => void;
  totalPages: number;
  page: number;
  userId: number;
  fetchType: string;
}

const Pagination = ({
  fetchClothes,
  totalPages,
  page,
  userId,
  fetchType,
}: IPaginationProps) => {
  const handleOnPrevClick = (e: React.MouseEvent<HTMLButtonElement>) => {
    e.stopPropagation();
    fetchClothes(fetchType, userId, 'prev', true);
  };

  const handleOnNextClick = (e: React.MouseEvent<HTMLButtonElement>) => {
    e.stopPropagation();
    fetchClothes(fetchType, userId, 'next', true);
  };

  return (
    <Box>
      <Flex align="center">
        {page + 1 > 1 && (
          <Button mx="1rem" onClick={handleOnPrevClick}>
            Prev
          </Button>
        )}
        <Box color="black.primary">
          {page + 1} of {totalPages}
        </Box>
        {page + 1 !== totalPages && (
          <Button mx="1rem" onClick={handleOnNextClick}>
            Next
          </Button>
        )}
      </Flex>
    </Box>
  );
};

export default Pagination;
