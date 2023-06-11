import { Box, Grid, GridItem } from '@chakra-ui/react';
import Sidebar from '../components/Messages/Sidebar';
import { useLocation } from 'react-router-dom';
import Conversation from '../components/Messages/Conversation';
import ExtraInfo from '../components/Messages/ExtraInfo';
import { useContext, useEffect, useRef, useState } from 'react';
import { Client } from '../util/client';
import { IChatMessage, IUserContext, IUserWithMessage } from '../interfaces';
import { simpleUserProfileState } from '../state/initialState';
import { UserContext } from '../context/user';

const MessagesRoute = () => {
  const { user } = useContext(UserContext) as IUserContext;
  const location = useLocation();
  const [receiverUserId, setReceiverUserId] = useState(0);
  const [receiverUser, setReceiverUser] = useState(simpleUserProfileState);
  const [filterTerm, setFilterTerm] = useState('');
  const [senderUserId, setSenderUserId] = useState(0);
  const [chatMessages, setChatMessages] = useState<IChatMessage[]>([]);
  const [filteredUsersWithMessages, setFilteredUsersWithMessages] = useState<
    IUserWithMessage[]
  >([]);
  const [isLoading, setIsLoading] = useState(false);
  const [page, setPage] = useState(0);
  const [showLoadMoreUsers, setShowLoadMoreUsers] = useState(false);
  const [usersWithMessages, setUsersWithMessages] = useState<IUserWithMessage[]>([]);
  const shouldRun = useRef(true);

  const handleSetFilterTerm = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFilterTerm(e.target.value);
  };

  useEffect(() => {
    if (filterTerm.length === 0) {
      setFilteredUsersWithMessages(usersWithMessages);
    } else {
      const filtered = [...usersWithMessages].filter(({ firstName, lastName }) => {
        if (
          firstName.toLowerCase().includes(filterTerm.toLowerCase()) ||
          lastName.toLowerCase().includes(filterTerm.toLowerCase())
        ) {
          return user;
        }
      });
      setFilteredUsersWithMessages(filtered);
    }
  }, [usersWithMessages, filterTerm]);

  const handleOnChangeUser = (receiverUserId: number) => {
    getMessages(receiverUserId, user.id);
    setReceiverUserId(receiverUserId);
    setSenderUserId(user.id);
    getReceiverProfile(receiverUserId);
  };

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

  const getUsersWithMessages = (currentUserId: number, paginate: boolean) => {
    const pageNum = paginate ? page : -1;
    setIsLoading(true);
    Client.getUsersWithMessages(currentUserId, pageNum)
      .then((res) => {
        const { friends, page } = res.data.pagination;
        if (friends.length === 0) {
          setShowLoadMoreUsers(false);
          return;
        }
        setShowLoadMoreUsers(true);
        setPage(page);
        if (paginate) {
          setUsersWithMessages((prevState) => [...prevState, ...friends]);
        } else {
          setUsersWithMessages(friends);
        }
      })
      .catch((err) => {
        throw new Error(err.response.data.message);
      })
      .finally(() => {
        setIsLoading(false);
      });
  };

  useEffect(() => {
    if (
      location.state !== null &&
      Object.keys(location.state).length &&
      shouldRun.current
    ) {
      shouldRun.current = false;
      const { receiverUserId, senderUserId } = location.state;
      setReceiverUserId(receiverUserId);
      setSenderUserId(senderUserId);
      getMessages(receiverUserId, senderUserId);
      getReceiverProfile(receiverUserId);
      getUsersWithMessages(senderUserId, false);
    }
    if (shouldRun.current && user.id !== 0) {
      shouldRun.current = false;
      getUsersWithMessages(user.id, false);
    }
  }, [location.state, user.id]);

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
          <Sidebar
            isLoading={isLoading}
            handleOnChangeUser={handleOnChangeUser}
            showLoadMoreUsers={showLoadMoreUsers}
            getUsersWithMessages={getUsersWithMessages}
            usersWithMessages={
              filterTerm.length === 0 ? usersWithMessages : filteredUsersWithMessages
            }
            filterTerm={filterTerm}
            handleSetFilterTerm={handleSetFilterTerm}
          />
        </GridItem>
        <GridItem>
          <Conversation
            receiverUser={receiverUser}
            chatMessages={chatMessages}
            sendMessage={sendMessage}
          />
        </GridItem>
        <GridItem>
          {receiverUser.id !== 0 && <ExtraInfo receiverUser={receiverUser} />}
        </GridItem>
      </Grid>
    </Box>
  );
};

export default MessagesRoute;
