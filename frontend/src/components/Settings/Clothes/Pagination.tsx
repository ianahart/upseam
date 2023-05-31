import { Box, Button, Flex } from '@chakra-ui/react';

interface IPaginationProps {
  fetchClothes: (pagination: boolean, page: number, direction: string) => void;
  totalPages: number;
  page: number;
  fetchType: string;
}

const Pagination = ({ fetchClothes, totalPages, page, fetchType }: IPaginationProps) => {
  const handleOnPrevClick = (e: React.MouseEvent<HTMLButtonElement>) => {
    e.stopPropagation();
    fetchClothes(true, page, 'prev');
  };

  const handleOnNextClick = (e: React.MouseEvent<HTMLButtonElement>) => {
    e.stopPropagation();
    fetchClothes(true, page, 'next');
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
