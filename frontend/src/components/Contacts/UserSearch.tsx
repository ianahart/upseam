import {
  Box,
  Button,
  Flex,
  FormControl,
  Heading,
  Image,
  Input,
  Text,
} from '@chakra-ui/react';
import { useState, useCallback, useEffect, useRef } from 'react';
import { Client } from '../../util/client';
import { ISearchUser } from '../../interfaces';
import { abbreviate } from '../../util';
import UserPopOver from './UserPopOver';

const UserSearch = () => {
  const [term, setTerm] = useState('');
  const [error, setError] = useState('');
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [searchUsers, setSearchUsers] = useState<ISearchUser[]>([]);
  const [isDropDownOpen, setIsDropDownOpen] = useState(false);
  const menuRef = useRef<HTMLDivElement>(null);

  const handleOnChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setTerm(e.target.value);
  };

  const validate = () => {
    let errors = false;
    if (term.trim().length === 0 || term.trim().length > 255) {
      errors = true;
    }
    return errors;
  };

  const handleOnSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    setError('');
    if (validate()) {
      setError('Please type in a user by name');
    }
    preformSearch(false);
  };

  const preformSearch = (paginate: boolean) => {
    const pageNum = paginate ? page : -1;
    Client.searchUser(term, pageNum)
      .then((res) => {
        const { page, totalPages, users } = res.data.data;
        if (paginate) {
          setSearchUsers((prevState) => [...prevState, ...users]);
        } else {
          setSearchUsers(users);
        }
        setIsDropDownOpen(true);
        setPage(page);
        setTotalPages(totalPages);
      })
      .catch((err) => {
        throw new Error(err.response.data.message);
      })
      .finally(() => {});
  };

  const clickAway = useCallback(
    (e: MouseEvent) => {
      const target = e.target as Element;
      if (menuRef.current !== null) {
        if (!menuRef.current.contains(target)) {
          setIsDropDownOpen(false);
        }
      }
    },
    [setIsDropDownOpen]
  );

  useEffect(() => {
    window.addEventListener('click', clickAway);
    return () => window.removeEventListener('click', clickAway);
  }, [clickAway]);

  return (
    <Box mt="3rem" mb="6rem" width="80%">
      <Flex justify="center" as="header" mb="3rem">
        <Heading color="text.primary" fontSize="1.5rem">
          User Search
        </Heading>
      </Flex>
      <form onSubmit={handleOnSubmit}>
        {error.length > 0 && (
          <Text color="red.500" fontSize="0.85rem">
            {error}
          </Text>
        )}
        <FormControl display="flex">
          <Input
            onFocus={() => setIsDropDownOpen(false)}
            value={term}
            onChange={handleOnChange}
            placeholder="Enter a user..."
            type="text"
          />
          <Button type="submit" mx="0.5rem">
            Search
          </Button>
        </FormControl>
      </form>
      {isDropDownOpen && (
        <Box
          border="1px solid"
          borderColor="light.primary"
          borderTop="none"
          borderBottomRadius={8}
          p="0.25rem"
          ref={menuRef}
          overflow="auto"
          className="overflow-scroll"
          height="250px"
        >
          {searchUsers.map(({ id, profileId, firstName, lastName, avatarUrl }) => {
            return (
              <Box my="1.5rem" key={id}>
                {avatarUrl ? (
                  <Image
                    height="45px"
                    width="45px"
                    borderRadius="50%"
                    src={avatarUrl}
                    alt="profile picture"
                  />
                ) : (
                  <Flex
                    borderRadius="50%"
                    direction="column"
                    align="center"
                    justify="center"
                    height="45px"
                    width="45px"
                    bg="blue.500"
                  >
                    <Text color="#fff">{abbreviate(firstName, lastName)}</Text>
                  </Flex>
                )}
                <UserPopOver
                  userId={id}
                  firstName={firstName}
                  lastName={lastName}
                  profileId={profileId}
                />
              </Box>
            );
          })}
          {totalPages !== page && (
            <Box display="flex" justifyContent="center" my="1rem">
              <Button onClick={() => preformSearch(true)}>Load more...</Button>
            </Box>
          )}
        </Box>
      )}
    </Box>
  );
};

export default UserSearch;
