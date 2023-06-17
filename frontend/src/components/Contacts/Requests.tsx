import { Box, Button, Flex, Heading, Image, Text } from '@chakra-ui/react';
import { useContext, useState, useEffect, useRef } from 'react';
import { Client } from '../../util/client';
import { UserContext } from '../../context/user';
import { IFriendRequest, IUserContext } from '../../interfaces';
import { abbreviate } from '../../util';

const Requests = () => {
  const PAGE_SIZE = 3;
  const { user } = useContext(UserContext) as IUserContext;
  const [page, setPage] = useState(-1);
  const [totalPages, setTotalPages] = useState(0);
  const [friendRequests, setFriendRequests] = useState<IFriendRequest[]>([]);
  const shouldRun = useRef(true);

  const ignoreFriendRequest = (
    e: React.MouseEvent<HTMLButtonElement>,
    fr: IFriendRequest
  ) => {
    e.stopPropagation();
    updateFriendRequest(fr, 'declined');
  };

  const acceptFriendRequest = (
    e: React.MouseEvent<HTMLButtonElement>,
    fr: IFriendRequest
  ) => {
    e.stopPropagation();
    updateFriendRequest(fr, 'accepted');
  };

  const updateFriendRequest = (fr: IFriendRequest, action: string) => {
    const { id, requesteeId, requesterId } = fr;
    Client.updateFriendRequest(id, requesteeId, requesterId, action)
      .then(() => {
        filterFriendRequests(id);
      })
      .catch((err) => {
        throw new Error(err.response.data.message);
      });
  };

  const filterFriendRequests = (id: number) => {
    setFriendRequests((prevState) => prevState.filter((fr) => fr.id !== id));
  };

  const getFriendRequests = () => {
    Client.getFriendRequests(user.id, page, PAGE_SIZE)
      .then((res) => {
        const { content, number, totalPages } = res.data.friendRequests;
        setFriendRequests((prevState) => [...prevState, ...content]);
        setPage(number);
        setTotalPages(totalPages);
      })
      .catch((err) => {
        throw new Error(err.response.data.message);
      });
  };

  useEffect(() => {
    if (shouldRun.current && user.id !== 0) {
      shouldRun.current = false;
      getFriendRequests();
    }
  }, [shouldRun, user.id]);

  return (
    <Box
      p="0.25rem"
      maxW={['100%', '100%', '300px']}
      minW={['100%', '100%', '300px']}
      border="1px solid"
      borderColor="light.primary"
      borderBottomLeftRadius={8}
      borderTopLeftRadius={8}
    >
      <Flex justify="center" mt="5rem">
        <Box as="header">
          <Heading color="text.primary" fontSize="1.5rem">
            Friend Requests
          </Heading>
        </Box>
      </Flex>
      <Box mt="2rem">
        {friendRequests.map((fr) => {
          return (
            <Box my="1rem" key={fr.id}>
              <Flex justify="flex-start">
                {fr.avatarUrl ? (
                  <Image
                    width="45px"
                    height="45px"
                    borderRadius="50%"
                    src={fr.avatarUrl}
                    alt="profile picture"
                  />
                ) : (
                  <Flex
                    direction="column"
                    alignItems="center"
                    justify="center"
                    width="45px"
                    height="45px"
                    borderRadius="50%"
                    bg="blue.500"
                  >
                    <Text color="#fff">{abbreviate(fr.firstName, fr.lastName)}</Text>
                  </Flex>
                )}
                <Box ml="0.25rem">
                  <Text color="text.primary">
                    {fr.firstName} {fr.lastName}
                  </Text>
                </Box>
              </Flex>
              <Box my="1rem">
                <Button
                  onClick={(e) => acceptFriendRequest(e, fr)}
                  colorScheme="blue"
                  mx="0.5rem"
                >
                  Accept
                </Button>
                <Button onClick={(e) => ignoreFriendRequest(e, fr)} mx="0.5rem">
                  Ignore
                </Button>
              </Box>
            </Box>
          );
        })}
      </Box>
      {totalPages !== page && (
        <Flex my="2rem" justify="center">
          <Button onClick={getFriendRequests}>Load More</Button>
        </Flex>
      )}
      {friendRequests.length === 0 && (
        <Flex justify="center" textAlign="center">
          <Text color="text.primary">You currently do not have any friend requests</Text>
        </Flex>
      )}
    </Box>
  );
};

export default Requests;
