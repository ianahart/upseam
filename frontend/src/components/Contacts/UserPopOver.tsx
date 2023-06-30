import {
  Popover,
  PopoverTrigger,
  PopoverContent,
  PopoverHeader,
  PopoverFooter,
  PopoverArrow,
  PopoverCloseButton,
  Button,
  ButtonGroup,
  useDisclosure,
  Text,
} from '@chakra-ui/react';
import { useNavigate } from 'react-router-dom';
import { useContext, useRef, useState } from 'react';
import { slugify } from '../../util';
import { UserContext } from '../../context/user';
import { IUserContext } from '../../interfaces';
import { Client } from '../../util/client';
import { AiOutlineCheck, AiOutlineUser } from 'react-icons/ai';

interface IUserPopOverProps {
  userId: number;
  firstName: string;
  lastName: string;
  profileId: number;
  role: string;
}

const UserPopOver = ({
  userId,
  firstName,
  lastName,
  profileId,
  role,
}: IUserPopOverProps) => {
  const { user } = useContext(UserContext) as IUserContext;

  const [areFriends, setAreFriends] = useState(false);
  const { onOpen, onClose, isOpen } = useDisclosure();

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

  const sendFriendRequest = () => {
    if (userId === user.id) return;
    Client.createFriendRequest(userId, user.id)
      .then(() => {
        onClose();
      })
      .catch((err) => {
        console.log(err);
        throw new Error(err.response.data.message);
      });
  };

  return (
    <Popover
      isOpen={isOpen}
      onOpen={onOpen}
      onClose={onClose}
      initialFocusRef={initialFocusRef}
      placement="bottom"
      closeOnBlur={false}
    >
      <PopoverTrigger>
        <Text onClick={() => checkIfFriends()} role="button" color="text.primary">
          {firstName} {lastName}
        </Text>
      </PopoverTrigger>
      <PopoverContent color="white" bg="blue.800" borderColor="blue.800">
        <PopoverHeader pt={4} fontWeight="bold" border="0">
          {firstName} {lastName}
        </PopoverHeader>
        <Text p="0.25rem" mx="1rem">
          {role.toLowerCase()}
        </Text>
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
            {!areFriends && userId !== user.id && (
              <Button onClick={sendFriendRequest} color="black.primary">
                <AiOutlineUser /> Add Friend
              </Button>
            )}
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
