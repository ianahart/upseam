import {
  Button,
  Box,
  Flex,
  Spinner,
  Image,
  Text,
  useDisclosure,
  Modal,
  ModalOverlay,
  ModalContent,
  ModalHeader,
  ModalFooter,
  ModalBody,
  ModalCloseButton,
  Input,
} from '@chakra-ui/react';
import { useParams } from 'react-router-dom';
import { useEffectOnce } from '../../../hooks/useEffectOnce';
import { Client } from '../../../util/client';
import { IFullCloth, IUserContext } from '../../../interfaces';
import { fullClothState } from '../../../state/initialState';
import { useContext, useState } from 'react';
import { AiOutlineInfoCircle, AiOutlineMail, AiOutlineUser } from 'react-icons/ai';
import { BiRuler } from 'react-icons/bi';
import Header from '../Header';
import FormHeader from '../EditProfile/FormHeader';
import { IoTicketOutline } from 'react-icons/io5';
//@ts-ignore
import { default as dayjs } from 'dayjs';
import relativeTime from 'dayjs/plugin/relativeTime';
import { UserContext } from '../../../context/user';
import Bids from './Bid/Bids';
dayjs.extend(relativeTime);

const FullCloth = () => {
  const { user } = useContext(UserContext) as IUserContext;
  const { isOpen, onOpen, onClose } = useDisclosure();
  const [cloth, setCloth] = useState<IFullCloth>(fullClothState);
  const [bid, setBid] = useState('');
  const [isNewBid, setIsNewBid] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState('');

  const params = useParams();

  useEffectOnce(() => {
    if (params.clothId !== undefined) {
      getCloth(params.clothId);
    }
  });

  const handleSelectBid = (
    clothId: number,
    _bidId: number,
    bidUserId: number,
    _bidBid: number
  ) => {
    selectBid(clothId, _bidId);
    createOrder(clothId, user.id, bidUserId, _bidBid);
  };

  const createOrder = (
    clothId: number,
    userId: number,
    bidUserId: number,
    bid: number
  ) => {
    Client.createOrder(clothId, userId, bidUserId, bid)
      .then((res) => {
        console.log(res);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const selectBid = (clothId: number, _bidId: number) => {
    Client.selectBid(clothId, _bidId)
      .then(() => {
        setCloth({ ...cloth, closed: true, closedId: _bidId });
      })
      .catch((err) => {
        throw new Error(err.response.data.message);
      });
  };

  const makeBid = (e: React.MouseEvent<HTMLButtonElement>) => {
    e.stopPropagation();
    if (!bid) {
      return;
    }
    setIsLoading(true);
    Client.createBid(user.id, cloth.id, bid)
      .then(() => {
        setIsNewBid(true);
        setIsLoading(false);
        onClose();
      })
      .catch((err) => {
        setError(err.response.data.message);
        setIsLoading(false);
        throw new Error(err.response.data.message);
      });
  };

  const getCloth = (clothId: string) => {
    setIsLoading(true);
    Client.getCloth(clothId)
      .then((res) => {
        setCloth(res.data.cloth);
        setIsLoading(false);
      })
      .catch((err) => {
        setIsLoading(false);
        throw new Error(err.response.data.message);
      });
  };

  return (
    <Box>
      <Header heading="Cloth" />
      <Flex
        mt="5rem"
        justify={['unset', 'unset', 'center']}
        direction={['column', 'row', 'row']}
      >
        {isLoading && (
          <Spinner
            thickness="4px"
            speed="0.65s"
            emptyColor="gray.200"
            color="blue.500"
            size="xl"
          />
        )}
      </Flex>
      {!isLoading && (
        <Box>
          <Box
            borderRadius="8px"
            border="1px solid"
            boxShadow="rgba(100, 100, 111, 0.2) 0px 7px 29px 0px"
            p="0.5rem"
            borderColor="#f4eeee"
          >
            <Flex justify="space-between" direction={['column', 'row', 'row']}>
              <Box>
                <FormHeader heading="Description" />
                <Image borderRadius="8px" src={cloth.clothUrl} alt={cloth.description} />
                <Flex align="center">
                  <Box mr="0.5rem" color="blue.500">
                    <AiOutlineInfoCircle />
                  </Box>

                  <Text
                    color="black.primary"
                    lineHeight="1.6"
                    mt="3rem"
                    width="300px"
                    wordBreak="break-all"
                  >
                    {cloth.description}
                  </Text>
                </Flex>
                <Flex mt="1.5rem" align="center">
                  <Box mr="0.5rem" color="blue.500">
                    <BiRuler />
                  </Box>

                  <Text
                    color="black.primary"
                    lineHeight="1.6"
                    width="300px"
                    wordBreak="break-all"
                  >
                    {cloth.size}
                  </Text>
                </Flex>
              </Box>
              <Box className="user-info">
                <FormHeader heading="Personal information" />
                <Flex align="center">
                  <Box mr="0.5rem" color="blue.500">
                    <AiOutlineUser />
                  </Box>
                  <Text color="text.primary" wordBreak="break-all">
                    {cloth.firstName} {cloth.lastName}
                  </Text>
                </Flex>
                <Flex align="center">
                  <Box mr="0.5rem" color="blue.500">
                    <AiOutlineMail />
                  </Box>

                  <Text color="text.primary" wordBreak="break-all">
                    {cloth.email}
                  </Text>
                </Flex>
              </Box>
            </Flex>
            <Flex justify={['flex-start', 'flex-start', 'flex-end']}>
              <FormHeader heading="Extra Information" />
            </Flex>
            <Flex justify={['flex-start', 'flex-end', 'flex-end']}>
              <Flex direction="column">
                <Text fontStyle="italic" color="text.primary">
                  Date to be finished: {dayjs().to(cloth.dueDate)}
                </Text>
                <Text fontStyle="italic" color="text.primary">
                  Published {dayjs(cloth.createdAt).fromNow()}
                </Text>
                <Text fontStyle="italic" color="text.primary">
                  Updated {dayjs(cloth.updatedAt).fromNow()}
                </Text>
              </Flex>
            </Flex>
            {user.id !== cloth.userId && !cloth.closed && user.role === 'SEAMSTER' && (
              <Button colorScheme="blue" my="2rem" onClick={onOpen}>
                Make Bid
              </Button>
            )}
            <Modal isOpen={isOpen} onClose={onClose}>
              <ModalOverlay />
              <ModalContent>
                <ModalHeader color="black.primary">Make a bid</ModalHeader>
                {error && (
                  <Text color="red.500" fontSize="0.85rem">
                    {error}
                  </Text>
                )}
                <ModalCloseButton />
                <ModalBody>
                  <Text color="text.primary" fontSize="0.85rem">
                    The lower the better.
                  </Text>
                  <Box pos="relative">
                    <Input
                      onChange={(e) => setBid(parseFloat(e.target.value).toFixed(2))}
                      pl="1.5rem"
                      type="number"
                      placeholder="bid"
                    />
                    <Box color="blue.500" pos="absolute" top="10px" left="5px">
                      <IoTicketOutline />
                    </Box>
                  </Box>
                </ModalBody>
                <ModalFooter>
                  {isLoading && (
                    <Spinner
                      thickness="4px"
                      speed="0.65s"
                      emptyColor="gray.200"
                      color="blue.500"
                      size="xl"
                    />
                  )}

                  {!isLoading && (
                    <Box>
                      <Button colorScheme="blue" mr={3} onClick={makeBid}>
                        Ok
                      </Button>
                      <Button variant="ghost" mr={3} onClick={onClose}>
                        Cancel
                      </Button>
                    </Box>
                  )}
                </ModalFooter>
              </ModalContent>
            </Modal>
          </Box>
        </Box>
      )}
      <Bids
        closedId={cloth.closedId}
        handleSelectBid={handleSelectBid}
        ownerUserId={cloth.userId}
        setIsNewBid={setIsNewBid}
        isNewBid={isNewBid}
        clothUserId={cloth.userId}
        clothId={cloth.id}
        clothClosed={cloth.closed}
      />
    </Box>
  );
};

export default FullCloth;
