import { Box, Flex, Image, Text } from '@chakra-ui/react';
import { ISimpleUserProfile } from '../../interfaces';
import { AiOutlineMail, AiOutlineHome } from 'react-icons/ai';
import { abbreviate } from '../../util';

interface IExtraInfoProps {
  receiverUser: ISimpleUserProfile;
}

const ExtraInfo = ({ receiverUser }: IExtraInfoProps) => {
  return (
    <Flex
      direction="column"
      align="center"
      justify="center"
      p="0.25rem"
      borderTopRightRadius={8}
      borderBottomRightRadius={8}
      minH="800px"
    >
      <Box>
        {receiverUser.avatarUrl ? (
          <Image
            width="200px"
            height="200px"
            borderRadius="50%"
            src={receiverUser.avatarUrl}
            alt="profile picture"
          />
        ) : (
          <Flex
            bg="blue.500"
            width="200px"
            height="200px"
            borderRadius="50%"
            color="#fff"
            fontSize="2rem"
            direction="column"
            align="center"
            justify="center"
          >
            {abbreviate(receiverUser.firstName, receiverUser.lastName)}
          </Flex>
        )}
        <Flex align="center" my="1.5rem">
          <Box mx="0.5rem" color="text.primary">
            <AiOutlineMail />
          </Box>
          <Text color="text.primary">{receiverUser.email}</Text>
        </Flex>
        <Flex>
          <Box mx="0.5rem" color="text.primary">
            <AiOutlineHome />
          </Box>
          <Flex direction="column">
            <Text color="text.primary">{receiverUser.address}</Text>
            <Text color="text.primary">
              {receiverUser.country} {receiverUser.state} {receiverUser.zipCode}
            </Text>
          </Flex>
        </Flex>
      </Box>
    </Flex>
  );
};

export default ExtraInfo;
