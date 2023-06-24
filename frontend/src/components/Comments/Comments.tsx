import {
  Avatar,
  Box,
  Image,
  Button,
  Flex,
  Text,
  Textarea,
  Tooltip,
} from '@chakra-ui/react';
import { useContext, useState } from 'react';
import EmojiPicker from 'emoji-picker-react';
import { BsEmojiSmile, BsTrash } from 'react-icons/bs';
import { UserContext } from '../../context/user';
import { IComment, IPagination, IUserContext } from '../../interfaces';
import Header from '../Menu/Header';
import Pagination from '../Shared/Pagination';
// @ts-ignore
import dayjs from 'dayjs';
import localizedFormat from 'dayjs/plugin/localizedFormat';
dayjs.extend(localizedFormat);

interface ICommentsProps {
  handleCreateComment: (userId: number, text: string) => void;
  comments: IComment[];
  fetchData: (direction: string, paginate: boolean) => void;
  pagination: IPagination;
  handleRemoveComment: (id: number) => void;
}

const Comments = ({
  handleCreateComment,
  handleRemoveComment,
  comments,
  fetchData,
  pagination,
}: ICommentsProps) => {
  const { user } = useContext(UserContext) as IUserContext;
  const [commentText, setCommentText] = useState('');
  const [error, setError] = useState('');
  const [toggleEmojis, setToggleEmojis] = useState(false);

  const handleOnChange = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    setCommentText(e.target.value);
  };

  const removeComment = (id: number) => {
    handleRemoveComment(id);
  };

  const createComment = () => {
    setError('');
    if (commentText.trim().length === 0 || commentText.length > 250) {
      setError('Comment must be between 1 and 250 characters');
      return;
    }
    handleCreateComment(user.id, commentText);
    setCommentText('');
  };

  const handleOnEmojiClick = (emoji: any) => {
    setToggleEmojis(false);
    setCommentText((prevState) => prevState + ' ' + emoji.emoji);
  };

  return (
    <Box p="0.5rem">
      <Header heading="Comments" />
      <Flex mt="2rem" alignItems="center">
        {user.avatarUrl ? (
          <Image
            height="45px"
            width="45px"
            borderRadius="50%"
            src={user.avatarUrl}
            alt={user.firstName}
          />
        ) : (
          <Avatar />
        )}
        <Text ml="0.25rem" color="text.primary">
          {user.firstName} {user.lastName}
        </Text>
      </Flex>
      <Box
        my="1.5rem"
        minH="180px"
        boxShadow="rgba(49, 130, 206, 0.25) 0px 2px 5px -1px, rgba(49,130,206, 0.5) 0px 1px 3px -1px;"
      >
        <Textarea
          value={commentText}
          onChange={handleOnChange}
          minH="180px"
          resize="none"
          border="none"
          placeholder="Post a comment..."
        ></Textarea>
        {error.length > 0 && (
          <Text color="red.500" textAlign="center" my="0.25rem" fontSize="0.85rem">
            {error}
          </Text>
        )}
        <hr />
        <Flex align="center" justify="space-between">
          <Box
            color="text.primary"
            fontSize="1.3rem"
            cursor="pointer"
            position="relative"
            p="0.25rem"
            onClick={() => setToggleEmojis((prevState) => !prevState)}
          >
            <BsEmojiSmile />
          </Box>
          {toggleEmojis && (
            <Box position="absolute" left="5px">
              <EmojiPicker onEmojiClick={handleOnEmojiClick} />
            </Box>
          )}
          <Flex p="0.5rem">
            <Button onClick={createComment}>Comment</Button>
          </Flex>
        </Flex>
      </Box>
      <Flex direction="column" justify="flex-start">
        {comments.map((comment) => {
          return (
            <Box key={comment.commentId}>
              <Flex align="center">
                <Box>
                  {comment.avatarUrl ? (
                    <Image
                      borderRadius="50%"
                      width="45px"
                      height="45px"
                      src={comment.avatarUrl}
                      alt="profile picture"
                    />
                  ) : (
                    <Avatar />
                  )}
                  <Text color="text.primary">
                    {comment.firstName} {comment.lastName}
                  </Text>
                </Box>
                <Box>
                  {user.id === comment.userId && (
                    <Box>
                      <Text fontSize="0.85rem" fontStyle="italic" color="text.primary">
                        (You)
                      </Text>
                    </Box>
                  )}
                  <Text
                    color="black.primary"
                    borderTopLeftRadius={8}
                    borderTopRightRadius={8}
                    borderBottomRightRadius={8}
                    margin="1rem"
                    bg="gray.100"
                    p="1rem"
                  >
                    {comment.text}
                  </Text>
                  <Text textAlign="right" color="text.primary" fontSize="0.85rem">
                    {dayjs(comment.createdAt).format('L LT')}
                  </Text>
                </Box>
                {user.id === comment.userId && (
                  <Tooltip label="remove">
                    <Box
                      onClick={() => removeComment(comment.commentId)}
                      cursor="pointer"
                    >
                      <BsTrash />
                    </Box>
                  </Tooltip>
                )}
              </Flex>
            </Box>
          );
        })}
      </Flex>
      <Flex my="2rem" justify="center">
        <Pagination
          fetchData={fetchData}
          totalPages={pagination.totalPages}
          page={pagination.page}
        />
      </Flex>
    </Box>
  );
};

export default Comments;
