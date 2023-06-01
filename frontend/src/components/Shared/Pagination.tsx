import { Box, Button, Flex } from '@chakra-ui/react';

interface IPaginationProps {
  fetchData: (direction: string, paginate: boolean) => void;
  totalPages: number;
  page: number;
}

const Pagination = ({ fetchData, totalPages, page }: IPaginationProps) => {
  const handleOnPrevClick = (e: React.MouseEvent<HTMLButtonElement>) => {
    e.stopPropagation();
    fetchData('prev', true);
  };

  const handleOnNextClick = (e: React.MouseEvent<HTMLButtonElement>) => {
    e.stopPropagation();
    fetchData('next', true);
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
