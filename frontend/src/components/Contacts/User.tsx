import { Box, Flex, Image, Text, Button } from '@chakra-ui/react';
import { IContact, IUserContext } from '../../interfaces';
import { useNavigate } from 'react-router-dom';
import { abbreviate, slugify } from '../../util';
import { AiOutlineMessage, AiOutlineUser } from 'react-icons/ai';
import { useContext } from 'react';
import { UserContext } from '../../context/user';
import { Client } from '../../util/client';

interface IUserProps {
  contact: IContact;
  handleRemoveFriend: (id: number) => void;
}

const User = ({ contact, handleRemoveFriend }: IUserProps) => {
  const { user } = useContext(UserContext) as IUserContext;
  const navigate = useNavigate();

  const goToProfile = (e: React.MouseEvent<HTMLParagraphElement>) => {
    navigate(`/${slugify(contact.firstName, contact.lastName)}/profile`, {
      state: { profileId: contact.profileId },
    });
  };

  const sendMessage = (e: React.MouseEvent<HTMLButtonElement>) => {
    e.stopPropagation();
  };

  const removeFriend = (e: React.MouseEvent<HTMLButtonElement>) => {
    e.stopPropagation();
    Client.removeFriend(user.id, contact.userId)
      .then((res) => {
        console.log(res);
        handleRemoveFriend(contact.userId);
      })
      .catch((err) => {
        console.log(err);
        throw new Error(err.response.data.message);
      });
  };

  return (
    <Flex
      my="1.5rem"
      justify="space-between"
      p="0.25rem"
      align="center"
      borderBottom="1px solid"
      borderColor="light.primary"
      width="100%"
    >
      <Box>
        {contact.avatarUrl ? (
          <Image
            height="45px"
            width="45px"
            borderRadius="50%"
            src={contact.avatarUrl}
            alt="profile picture"
          />
        ) : (
          <Flex
            direction="column"
            color="#fff"
            justify="center"
            align="center"
            bg="blue.500"
            borderRadius="50%"
            height="45px"
            width="45px"
          >
            {abbreviate(contact.firstName, contact.lastName)}
          </Flex>
        )}
        <Box>
          <Text role="button" onClick={goToProfile} mt="0.25rem" color="text.primary">
            {contact.firstName} {contact.lastName}
          </Text>
        </Box>
      </Box>
      <Flex justify="flex-end" flexDir="column">
        <Button onClick={(e) => removeFriend(e)} my="0.5rem">
          {' '}
          <AiOutlineUser /> Remove Friend
        </Button>
        <Button onClick={(e) => sendMessage(e)} colorScheme="blue">
          <AiOutlineMessage /> Send Message
        </Button>
      </Flex>
    </Flex>
  );
};
export default User;
