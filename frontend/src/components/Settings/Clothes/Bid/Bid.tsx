import {
  Td,
  Text,
  Tr,
  Image,
  Popover,
  PopoverTrigger,
  PopoverContent,
  Box,
  Button,
  PopoverFooter,
  PopoverHeader,
  PopoverBody,
  PopoverArrow,
  PopoverCloseButton,
  Flex,
} from '@chakra-ui/react';
import { IBid } from '../../../../interfaces';
import * as dayjs from 'dayjs';
import relativeTime from 'dayjs/plugin/relativeTime';
import InitialIcon from '../../../Shared/InitialIcon';
import { useRef } from 'react';
import { slugify } from '../../../../util';
import { useNavigate } from 'react-router-dom';
import { AiOutlineUser } from 'react-icons/ai';
dayjs.extend(relativeTime);

export interface IBidProps {
  _bid: IBid;
}

const Bid = ({ _bid }: IBidProps) => {
  const initialFocusRef = useRef(null);
  const navigate = useNavigate();

  const sendToProfile = () => {
    const { firstName, lastName, profileId } = _bid;
    navigate(`/${slugify(firstName, lastName)}/profile`, { state: { profileId } });
  };

  return (
    <Tr>
      <Td display="flex" align="center">
        {!_bid.avatarUrl ? (
          <InitialIcon />
        ) : (
          <Image
            height="45px"
            width="45px"
            borderRadius="50%"
            src={_bid.avatarUrl}
            alt={`Description of ${_bid.firstName}`}
          />
        )}
        <Popover initialFocusRef={initialFocusRef} placement="bottom" closeOnBlur={false}>
          <PopoverTrigger>
            <Td cursor="pointer" color="text.primary">
              {_bid.firstName} {_bid.lastName}
            </Td>
          </PopoverTrigger>
          <PopoverContent
            minW="500px"
            wordBreak="break-all"
            color="white"
            bg="blue.800"
            borderColor="blue.800"
          >
            <PopoverHeader pt={4} fontWeight="bold" border="0">
              Get In Touch With {_bid.firstName}
            </PopoverHeader>
            <PopoverArrow />
            <PopoverCloseButton />
            <PopoverBody minW={{ base: '100%', lg: 'max-content' }}>
              <Flex direction="column" justify="center" align="center">
                <Text>Send a friend request to {_bid.firstName}?</Text>
                <Button mt="0.5rem" colorScheme="blue">
                  Send Request
                </Button>
              </Flex>
            </PopoverBody>
            <PopoverFooter border="0" pb={4}>
              <Flex justify="flex-start" textAlign="center" align="center">
                <Button
                  onClick={sendToProfile}
                  _hover={{ background: 'transparent' }}
                  variant="ghost"
                >
                  <AiOutlineUser fontSize="1.3rem" />
                  View Profile
                </Button>
              </Flex>
            </PopoverFooter>
          </PopoverContent>
        </Popover>
      </Td>
      <Td color="blue.500">${_bid.bid.toFixed(2)}</Td>
      <Td fontStyle="italic" color="text.primary">
        {' '}
        {dayjs(_bid.createdAt).fromNow()}
      </Td>
    </Tr>
  );
};

export default Bid;
