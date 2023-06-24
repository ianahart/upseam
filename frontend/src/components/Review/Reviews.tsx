import {
  Box,
  Text,
  Grid,
  GridItem,
  Card,
  Image,
  CardHeader,
  CardBody,
  CardFooter,
  Avatar,
  Button,
} from '@chakra-ui/react';
import { useContext, useEffect, useRef, useState } from 'react';
import { UserContext } from '../../context/user';
import { IReview, IUserContext } from '../../interfaces';
import { Client } from '../../util/client';
import Header from '../Menu/Header';
import { AiFillStar } from 'react-icons/ai';

const Reviews = () => {
  const [reviews, setReviews] = useState<IReview[]>([]);
  const [pagination, setPagination] = useState({
    pageSize: 1,
    direction: 'next',
    page: 0,
    totalPages: 0,
  });
  const { user } = useContext(UserContext) as IUserContext;

  const shouldRun = useRef(true);

  useEffect(() => {
    if (shouldRun.current && user.id !== 0) {
      shouldRun.current = false;
      getReviews(false);
    }
  }, [shouldRun, user.id]);

  const getReviews = (paginate: boolean) => {
    const { pageSize, direction, page } = pagination;
    const pageNum = paginate ? page : -1;
    Client.getReviews(user.id, pageNum, pageSize, direction)
      .then((res) => {
        console.log(res);
        const { page, pageSize, reviews, totalPages, direction } = res.data.data;
        setPagination({ ...pagination, page, pageSize, totalPages, direction });
        if (paginate) {
          setReviews((prevState) => [...prevState, ...reviews]);
        } else {
          setReviews(reviews);
        }
      })
      .catch((err) => {
        throw new Error(err.response.data.message);
      });
  };

  return (
    <Box>
      <Header heading="Your Reviews" />
      <Grid my="4rem" gap="10px" gridTemplateColumns={['1fr', '1fr 1fr', '1fr 1fr 1fr']}>
        {reviews.map((review) => {
          return (
            <GridItem key={review.reviewId}>
              <Card>
                <CardHeader>
                  <Box>
                    {review.avatarUrl ? (
                      <Image
                        borderRadius="50%"
                        height="45px"
                        width="45px"
                        src={review.avatarUrl}
                        alt="profile picture"
                      />
                    ) : (
                      <Avatar />
                    )}
                  </Box>
                  <Text mt="0.25rem" color="text.primary">
                    {review.firstName} {review.lastName}
                  </Text>
                </CardHeader>
                <CardBody>{review.text}</CardBody>
                <CardFooter>
                  {[...Array(review.rating)].map((_, index) => {
                    return (
                      <Box key={index}>
                        <AiFillStar color={index <= review.rating ? 'orange' : 'black'} />
                      </Box>
                    );
                  })}
                </CardFooter>
              </Card>
            </GridItem>
          );
        })}
      </Grid>
      {pagination.page !== pagination.totalPages && (
        <Box display="flex" justifyContent="center">
          <Button onClick={() => getReviews(true)}>Load more...</Button>
        </Box>
      )}
    </Box>
  );
};

export default Reviews;
