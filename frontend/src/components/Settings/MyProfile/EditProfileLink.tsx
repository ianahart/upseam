import { Flex, Text, Box } from '@chakra-ui/react';
import { Link as RouterLink } from 'react-router-dom';
import { AiOutlineEdit } from 'react-icons/ai';
import { useContext } from 'react';
import { UserContext } from '../../../context/user';
import { IUserContext } from '../../../interfaces';
import { slugify } from '../../../util';

const EditProfileLink = () => {
  const { user } = useContext(UserContext) as IUserContext;

  return (
    <RouterLink to={`/settings/${slugify(user.firstName, user.lastName)}/edit-profile`}>
      <Flex justify="flex-end" m="1rem" color="text.primary">
        <Flex
          borderRadius="20px"
          border="1px solid"
          p="0.5rem"
          borderColor="light.primary"
        >
          <Text mr="0.5rem">Edit</Text>
          <Box fontSize="1.5rem">
            <AiOutlineEdit />
          </Box>
        </Flex>
      </Flex>
    </RouterLink>
  );
};

export default EditProfileLink;
