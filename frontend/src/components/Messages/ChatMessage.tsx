import { Box, Flex, Avatar, Image, Text } from '@chakra-ui/react';
import { IChatMessage, IUserContext } from '../../interfaces';
import { useContext } from 'react';
import { UserContext } from '../../context/user';
import * as dayjs from 'dayjs';
import localizedFormat from 'dayjs/plugin/localizedFormat';
dayjs.extend(localizedFormat);

interface IChatMessageProps {
  chatMessage: IChatMessage;
}

const ChatMessage = ({ chatMessage }: IChatMessageProps) => {
  const { user } = useContext(UserContext) as IUserContext;
  return (
    <Flex
      justify={`${user.id === chatMessage.senderUserId ? 'flex-end' : 'flex-start'}`}
      my="2rem"
    >
      {user.id === chatMessage.senderUserId && (
        <Box>
          <Box
            borderTopRadius={8}
            borderBottomLeftRadius={8}
            mr="2rem"
            bg="blue.500"
            color="#fff"
            p="0.5rem"
            minW="100px"
          >
            {' '}
            {chatMessage.content}
          </Box>
          <Text fontSize="0.85rem" color="text.primary">
            {dayjs(chatMessage.createdAt).format('L LT')}
          </Text>
        </Box>
      )}
      {chatMessage.senderAvatarUrl ? (
        <Image
          width="45px"
          height="45px"
          borderRadius="50%"
          src={chatMessage.senderAvatarUrl}
          alt="profile picture"
        />
      ) : (
        <Avatar />
      )}
      {user.id !== chatMessage.senderUserId && (
        <Box>
          <Box
            borderTopRadius={8}
            borderBottomRightRadius={8}
            bg="blue.500"
            color="#fff"
            p="0.5rem"
            minW="100px"
            ml="0.5rem"
          >
            {chatMessage.content}
          </Box>
          <Text fontSize="0.85rem" color="text.primary">
            {dayjs(chatMessage.createdAt).format('L LT')}
          </Text>
        </Box>
      )}
    </Flex>
  );
};

export default ChatMessage;
