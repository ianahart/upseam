import {
  Box,
  Flex,
  Spinner,
  Text,
  TableContainer,
  Table,
  TableCaption,
  Thead,
  Tr,
  Th,
  Tbody,
  Td,
} from '@chakra-ui/react';
import { useState, useEffect, useRef } from 'react';
import Header from '../../Header';
import { Client } from '../../../../util/client';
import { bidsPaginationState } from '../../../../state/initialState';
import Pagination from '../../../Shared/Pagination';
import { IBidsPagination } from '../../../../interfaces';
import Bid from './Bid';

interface IBidsProps {
  closedId: number;
  clothId: number;
  clothUserId: number;
  isNewBid: boolean;
  setIsNewBid: (isNewBid: boolean) => void;
  ownerUserId: number;
  handleSelectBid: (clothId: number, _bidId: number, _bidUserId: number) => void;
  clothClosed: boolean;
}

const Bids = ({
  isNewBid,
  setIsNewBid,
  clothId,
  clothUserId,
  ownerUserId,
  handleSelectBid,
  clothClosed,
  closedId,
}: IBidsProps) => {
  const PAGE_SIZE = 4;
  const [isLoading, setIsLoading] = useState(false);
  const [pagination, setPagination] = useState<IBidsPagination>(bidsPaginationState);
  const shouldRun = useRef(true);

  const deleteBid = (id: number) => {
    setPagination((prevState) => ({
      ...prevState,
      content: [...prevState.content.filter((item) => item.id !== id)],
    }));
    Client.deleteBid(id)
      .then((res) => {
        getBids('next', false);
      })
      .catch((err) => {
        throw new Error(err.response.data.message);
      });
  };

  const getBids = (direction: string, paginate: boolean) => {
    setIsLoading(true);

    const pageNum = paginate ? pagination.page : -1;
    setIsNewBid(false);
    Client.getBids(clothId, pageNum, direction, PAGE_SIZE)
      .then((res) => {
        const { bids, page, direction } = res.data.data;

        setPagination((prevState) => ({
          ...prevState,
          content: bids.content,
          totalPages: bids.totalPages,
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
    if (shouldRun.current && clothId !== 0) {
      shouldRun.current = false;
      getBids('next', false);
    }
  }, [shouldRun, getBids, clothId]);

  useEffect(() => {
    if (isNewBid) {
      getBids('next', false);
    }
  }, [isNewBid]);

  return (
    <>
      {pagination.content.length === 0 ? (
        <Flex justify="center" my="3rem">
          <Text color="text.primary">
            No bids have been placed yet. Be the first one to make a bid!
          </Text>
        </Flex>
      ) : (
        <Box>
          <Header heading="Current Bids" />
          {isLoading && (
            <Flex my="3rem" justify="center">
              <Spinner
                thickness="4px"
                speed="0.65s"
                emptyColor="gray.200"
                color="blue.500"
                size="xl"
              />
            </Flex>
          )}
          <TableContainer>
            <Table variant="simple">
              <TableCaption>Current bids for this piece of clothing</TableCaption>
              <Thead>
                <Tr>
                  <Th>Full Name</Th>
                  <Th>Bid Offer</Th>
                  <Th>Date Posted</Th>
                  <Th>Action</Th>
                </Tr>
              </Thead>
              <Tbody>
                {pagination.content.map((bid) => {
                  return (
                    <Bid
                      closedId={closedId}
                      clothClosed={clothClosed}
                      handleSelectBid={handleSelectBid}
                      clothId={clothId}
                      ownerUserId={ownerUserId}
                      deleteBid={deleteBid}
                      key={bid.id}
                      _bid={bid}
                    />
                  );
                })}
              </Tbody>
            </Table>
          </TableContainer>
          <Flex justify="center" my="3rem">
            <Pagination
              fetchData={getBids}
              totalPages={pagination.totalPages}
              page={pagination.page}
            />
          </Flex>
        </Box>
      )}
    </>
  );
};

export default Bids;
