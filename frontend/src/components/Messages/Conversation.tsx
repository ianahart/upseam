import { Flex, Input, Text } from '@chakra-ui/react';
import { useState } from 'react';
import { IChatMessage, ISimpleUserProfile } from '../../interfaces';
import ChatMessage from './ChatMessage';

interface IConversationProps {
  sendMessage: (message: string) => void;
  chatMessages: IChatMessage[];
  receiverUser: ISimpleUserProfile;
}

const Conversation = ({
  sendMessage,
  chatMessages,
  receiverUser,
}: IConversationProps) => {
  const [inputValue, setInputValue] = useState('');

  const handleOnChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setInputValue(e.target.value);
  };

  const handleOnKeyDown = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === 'Enter') {
      sendMessage(inputValue);
      setInputValue('');
    }
  };

  return (
    <Flex
      direction="column"
      bg="gray.50"
      align="center"
      justify="space-between"
      minH="800px"
    >
      <Flex
        p="0.25rem"
        width="100%"
        direction="column"
        align="flex-start"
        justify="center"
        height="90px"
        as="header"
        boxShadow="rgba(100, 100, 111, 0.2) 0px 7px 29px 0px"
      >
        <Text color="black.primary" fontSize="1.6rem">
          {receiverUser.firstName} {receiverUser.lastName}
        </Text>
      </Flex>
      <Flex
        direction="column-reverse"
        className="overflow-scroll"
        p="0.25rem"
        overflow="auto"
        width="100%"
        height="400px"
      >
        {chatMessages.map((chatMessage) => {
          return <ChatMessage key={chatMessage.id} chatMessage={chatMessage} />;
        })}
      </Flex>
      <Input
        value={inputValue}
        onKeyDown={handleOnKeyDown}
        onChange={handleOnChange}
        height="50px"
        bg="gray.100"
        borderRadius={0}
        placeholder="Type your message here..."
      />
    </Flex>
  );
};

export default Conversation;
