import {
  Popover,
  PopoverTrigger,
  PopoverContent,
  PopoverHeader,
  PopoverBody,
  PopoverFooter,
  PopoverArrow,
  PopoverCloseButton,
  Button,
  ButtonGroup,
  PopoverAnchor,
  Text,
  Box,
  Flex,
} from '@chakra-ui/react';
import { useNavigate } from 'react-router-dom';
import { useContext, useEffect, useRef, useState } from 'react';
import { slugify } from '../../util';
import { UserContext } from '../../context/user';
import { IUserContext } from '../../interfaces';
import { Client } from '../../util/client';
import { AiOutlineCheck } from 'react-icons/ai';

interface IUserPopOverProps {
  userId: number;
  firstName: string;
  lastName: string;
  profileId: number;
}

const UserPopOver = ({ userId, firstName, lastName, profileId }: IUserPopOverProps) => {
  const { user } = useContext(UserContext) as IUserContext;
  const [areFriends, setAreFriends] = useState(false);
  const navigate = useNavigate();

  const initialFocusRef = useRef(null);

  const checkIfFriends = () => {
    Client.checkIfFriends(user.id, userId)
      .then((res) => {
        setAreFriends(res.data.areFriends);
      })
      .catch((err) => {
        throw new Error(err.response.data.message);
      });
  };

  const goToProfile = (profileId: number, firstName: string, lastName: string) => {
    navigate(`/${slugify(firstName, lastName)}/profile`, { state: { profileId } });
  };

  return (
    <Popover initialFocusRef={initialFocusRef} placement="bottom" closeOnBlur={false}>
      <PopoverTrigger>
        <Text onClick={() => checkIfFriends()} role="button" color="text.primary">
          {firstName} {lastName}
        </Text>
      </PopoverTrigger>
      <PopoverContent color="white" bg="blue.800" borderColor="blue.800">
        <PopoverHeader pt={4} fontWeight="bold" border="0">
          {firstName} {lastName}
        </PopoverHeader>
        <PopoverArrow bg="blue.800" />
        <PopoverCloseButton />
        <PopoverFooter
          border="0"
          display="flex"
          alignItems="center"
          justifyContent="space-between"
          pb={4}
        >
          <ButtonGroup size="sm">
            {areFriends && (
              <Button color="black.primary">
                <AiOutlineCheck /> Friends
              </Button>
            )}
            <Button
              onClick={() => goToProfile(profileId, firstName, lastName)}
              colorScheme="blue"
              ref={initialFocusRef}
            >
              Profile
            </Button>
          </ButtonGroup>
        </PopoverFooter>
      </PopoverContent>
    </Popover>
  );
};
export default UserPopOver;
