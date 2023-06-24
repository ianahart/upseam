import { Box, Button, Flex, Text, Textarea, Image, Avatar } from '@chakra-ui/react';
import { useContext, useState } from 'react';
import { AiFillStar } from 'react-icons/ai';
import { IInvoice, IUserContext } from '../../interfaces';
import { Client } from '../../util/client';
import { UserContext } from '../../context/user';

export interface ICreateReviewProps {
  invoice: IInvoice;
  closeReview: () => void;
}

const CreateReview = ({ invoice, closeReview }: ICreateReviewProps) => {
  const [rating, setRating] = useState(0);
  const { user } = useContext(UserContext) as IUserContext;
  const [text, setText] = useState('');
  const [error, setError] = useState('');
  const handleOnMouseOver = (index: number) => {
    setRating(index);
  };
  const handleOnMouseLeave = () => {
    if (rating === 1) {
      setRating(0);
      return;
    }
    setRating(rating);
  };

  const createReview = () => {
    const { clothId, userId } = invoice;
    setError('');
    Client.createReview(clothId, userId, user.id, rating, text)
      .then(() => {
        closeReview();
      })
      .catch((err) => {
        console.log(err);
        if (err.response.status === 400) {
          setError(err.response.data.message);
        }
        throw new Error(err.response.data.message);
      });
  };

  return (
    <Box my="2rem">
      <Flex direction="column" my="1.5rem" justify="center" align="center">
        {error.length > 0 && (
          <Text color="red.500" fontSize="0.85rem" my="1rem">
            {error}
          </Text>
        )}
        <Text fontStyle="italic" color="text.primary">
          Reviewing {invoice.firstName} {invoice.lastName}'s work.
        </Text>
        {invoice.avatarUrl ? (
          <Image
            width="45px"
            height="45px"
            borderRadius="50%"
            src={invoice.avatarUrl}
            alt="profile piciture"
          />
        ) : (
          <Avatar />
        )}
      </Flex>
      <Flex justifyContent="center">
        {[...Array(5)].map((_, index) => {
          index += 1;
          return (
            <Box
              fontSize="1.5rem"
              onMouseEnter={() => handleOnMouseOver(index)}
              onMouseLeave={handleOnMouseLeave}
              key={index}
            >
              <AiFillStar color={index <= rating ? 'orange' : 'black'} />
            </Box>
          );
        })}
      </Flex>
      <Textarea
        placeholder={`${invoice.firstName} is...`}
        value={text}
        onChange={(e) => setText(e.target.value)}
        mt="2.5rem"
      ></Textarea>
      <Flex justify="center" my="1.5rem">
        <Button onClick={createReview} width="90%" colorScheme="blue">
          Review
        </Button>
      </Flex>
    </Box>
  );
};

export default CreateReview;
