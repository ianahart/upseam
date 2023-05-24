import { Box, Flex, Image } from '@chakra-ui/react';
import { useContext } from 'react';
import { UserContext } from '../../context/user';
import { IUserContext } from '../../interfaces';

const ProfilePicture = () => {
  const { user } = useContext(UserContext) as IUserContext;
  return (
    <Flex
      justify="center"
      align="center"
      color="#fff"
      direction="column"
      width="50px"
      height="50px"
      borderRadius="50%"
    >
      <Image borderRadius="50%" src={user.avatarUrl} alt="profile picture" />
    </Flex>
  );
};

export default ProfilePicture;
