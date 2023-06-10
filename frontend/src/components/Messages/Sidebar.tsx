import {
  Box,
  Button,
  Heading,
  Flex,
  Input,
  Image,
  Text,
  Spinner,
} from '@chakra-ui/react';
import { useContext, useEffect, useState } from 'react';
import { BiRefresh } from 'react-icons/bi';
import { UserContext } from '../../context/user';
import { IUserContext, IUserWithMessage } from '../../interfaces';
import { abbreviate } from '../../util';

interface ISidebarProps {
  showLoadMoreUsers: boolean;
  getUsersWithMessages: (currentUserId: number, paginate: boolean) => void;
  usersWithMessages: IUserWithMessage[];
  handleOnChangeUser: (receiverUserId: number) => void;
  handleSetFilterTerm: (e: React.ChangeEvent<HTMLInputElement>) => void;
  filterTerm: string;
  isLoading: boolean;
}

const Sidebar = ({
  showLoadMoreUsers,
  getUsersWithMessages,
  usersWithMessages,
  handleOnChangeUser,
  handleSetFilterTerm,
  filterTerm,
  isLoading,
}: ISidebarProps) => {
  const { user } = useContext(UserContext) as IUserContext;
  const [toggleRefresh, setToggleRefresh] = useState(false);
  let timer: ReturnType<typeof setTimeout>;
  const handleRefreshClick = () => {
    setToggleRefresh(true);
    getUsersWithMessages(user.id, false);
    timer = setTimeout(() => {
      setToggleRefresh(false);
    }, 600);
  };

  useEffect(() => {
    return () => clearTimeout(timer);
  }, []);

  return (
    <Box
      borderTopLeftRadius={8}
      borderBottomLeftRadius={8}
      minH="800px"
      bg="blue.500"
      p="0.25rem"
    >
      <Heading color="#fff">Upseam</Heading>
      <Box my="2rem">
        <Input
          onChange={(e) => handleSetFilterTerm(e)}
          value={filterTerm}
          color="#fff"
          border="none"
          bg="blue.400"
          _placeholder={{ color: '#fff' }}
          placeholder="Search Here..."
        />
      </Box>
      <Flex justify="flex-start">
        <Box
          className={toggleRefresh ? 'rotate' : ''}
          onClick={handleRefreshClick}
          color="#fff"
          cursor="pointer"
          fontSize="2rem"
        >
          <BiRefresh />
        </Box>
      </Flex>
      {isLoading && (
        <Spinner
          thickness="4px"
          speed="0.65s"
          emptyColor="gray.200"
          color="#fff"
          size="xl"
        />
      )}
      <Box height="500px" className="overflow-scroll" overflow="auto" mt="4rem">
        {usersWithMessages.map((user) => {
          return (
            <Flex
              key={user.id}
              onClick={() => handleOnChangeUser(user.receiverUserId)}
              cursor="pointer"
              justify="flex-start"
              p="1rem 0.25rem"
            >
              {user.avatarUrl ? (
                <Image
                  height="45px"
                  width="45px"
                  borderRadius="50%"
                  src={user.avatarUrl}
                  alt="profile picture"
                />
              ) : (
                <Flex
                  direction="column"
                  borderRadius="50%"
                  height="45px"
                  width="45px"
                  justify="center"
                  align="center"
                  bg="blue.500"
                  color="#fff"
                >
                  {abbreviate(user.firstName, user.lastName)}
                </Flex>
              )}
              <Flex direction="column">
                <Text color="#fff">
                  {user.firstName} {user.lastName}
                </Text>
                <Text color="#fff" ml="0.5rem" wordBreak="break-all" fontSize="0.8rem">
                  {user.content} (you)
                </Text>
              </Flex>
            </Flex>
          );
        })}
      </Box>
      {showLoadMoreUsers && (
        <Flex justify="center" my="2rem">
          <Button onClick={() => getUsersWithMessages(user.id, true)}>
            Load More...
          </Button>
        </Flex>
      )}
    </Box>
  );
};
export default Sidebar;
