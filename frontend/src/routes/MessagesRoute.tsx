import { Box, Grid, GridItem } from '@chakra-ui/react';
import Sidebar from '../components/Messages/Sidebar';
import { useLocation } from 'react-router-dom';
import Conversation from '../components/Messages/Conversation';
import ExtraInfo from '../components/Messages/ExtraInfo';
import { useEffect, useRef, useState } from 'react';
import { Client } from '../util/client';
import { IChatMessage } from '../interfaces';
import { simpleUserProfileState } from '../state/initialState';

const MessagesRoute = () => {
  const location = useLocation();
  const [receiverUserId, setReceiverUserId] = useState(0);
  const [receiverUser, setReceiverUser] = useState(simpleUserProfileState);
  const [senderUserId, setSenderUserId] = useState(0);
  const [chatMessages, setChatMessages] = useState<IChatMessage[]>([]);
  const shouldRun = useRef(true);

  const getMessages = (receiverUserId: number, senderUserId: number) => {
    Client.getChatMessages(receiverUserId, senderUserId)
      .then((res) => {
        setChatMessages(res.data.chatMessages);
      })
      .catch((err) => {
        throw new Error(err.response.data.message);
      });
  };

  const getReceiverProfile = (receiverUserId: number) => {
    Client.getSimpleUser(receiverUserId)
      .then((res) => {
        setReceiverUser(res.data.userProfile);
      })
      .catch((err) => {
        throw new Error(err.response.data.message);
      });
  };

  useEffect(() => {
    if (Object.keys(location.state).length && shouldRun.current) {
      shouldRun.current = false;
      const { receiverUserId, senderUserId } = location.state;
      setReceiverUserId(receiverUserId);
      setSenderUserId(senderUserId);
      getMessages(receiverUserId, senderUserId);
      getReceiverProfile(receiverUserId);
    }
  }, [location.state]);

  const sendMessage = (message: string) => {
    Client.sendChatMessage(message, receiverUserId, senderUserId)
      .then((res) => {
        setChatMessages((prevState) => [res.data.chatMessage, ...prevState]);
      })
      .catch((err) => {
        throw new Error(err.response.data.message);
      });
  };

  return (
    <Box minH="100vh">
      <Grid
        mx="auto"
        boxShadow="rgba(100, 100, 111, 0.2) 0px 7px 29px 0px"
        maxW="1280px"
        minH="800px"
        gridTemplateColumns={['1fr', '1fr', '1fr 2fr 1fr']}
      >
        <GridItem>
          <Sidebar />
        </GridItem>
        <GridItem>
          <Conversation
            receiverUser={receiverUser}
            chatMessages={chatMessages}
            sendMessage={sendMessage}
          />
        </GridItem>
        <GridItem>
          <ExtraInfo receiverUser={receiverUser} />
        </GridItem>
      </Grid>
    </Box>
  );
};

export default MessagesRoute;
