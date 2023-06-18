import { Avatar, Box, Button, Flex, Image, Text } from '@chakra-ui/react';
import { useContext, useEffect, useRef, useState } from 'react';
import { UserContext } from '../../../context/user';
import { IOrder, IUserContext } from '../../../interfaces';
import Header from '../Header';
import { Link as RouterLink } from 'react-router-dom';
import { Client } from '../../../util/client';
// @ts-ignore
import * as dayjs from 'dayjs';
import { slugify } from '../../../util';

const Orders = () => {
  const { user } = useContext(UserContext) as IUserContext;
  const [pagination, setPagination] = useState({ page: 0, totalPages: 0 });
  const [seamsterOrders, setSeamsterOrders] = useState<IOrder[]>([]);
  const [error, setError] = useState('');
  const shouldRun = useRef(true);

  useEffect(() => {
    if (shouldRun.current && user.id !== 0) {
      shouldRun.current = false;
      getOrders(false);
    }
  }, [shouldRun.current, user.id]);

  const getOrders = (paginate: boolean) => {
    const pageNum = paginate ? pagination.page : -1;
    Client.getOrders(user.id, pageNum)
      .then((res) => {
        const { orders, totalPages, page } = res.data.orders;
        setPagination({ ...pagination, page, totalPages });
        setOrders(orders, paginate);
      })
      .catch((err) => {
        throw new Error(err.response.data.message);
      });
  };

  const setOrders = (orders: IOrder[], paginate: boolean) => {
    if (orders.length === 0 && !paginate) {
      setError('You currently have no orders.');
      return;
    }
    paginate
      ? setSeamsterOrders((prevState) => [...prevState, ...orders])
      : setSeamsterOrders(orders);
  };

  const updateOrder = (orderId: number, isCompleted: boolean) => {
    const updatedOrders = seamsterOrders.map((order) => {
      if (order.id === orderId) {
        order['complete'] = isCompleted;
      }
      return order;
    });

    setSeamsterOrders(updatedOrders);
  };

  const completeOrder = (orderId: number, isCompleted: boolean, bid: number) => {
    Client.updateOrder(orderId, isCompleted)
      .then(() => {
        updateOrder(orderId, isCompleted);
      })
      .catch((err) => {
        throw new Error(err.response.data.message);
      });

    if (isCompleted) {
      createInvoice(orderId, user.id, bid);
    }
  };

  const createInvoice = (orderId: number, seamsterId: number, bid: number) => {
    Client.createInvoice(orderId, seamsterId, bid)
      .then(() => {})
      .catch((err) => {
        throw new Error(err.response.data.message);
      });
  };

  return (
    <Box>
      <Header heading="Orders" />
      {error.length > 0 && (
        <Flex justify="center" my="3rem">
          <Text color="text.primary">{error}</Text>
        </Flex>
      )}
      <Box my="4rem">
        {seamsterOrders.map((o) => {
          return (
            <Flex
              bg={o.complete ? '#00ff7f7a' : '#fff'}
              borderBottom="1px solid"
              borderColor="light.primary"
              direction={['column', 'column', 'row']}
              my="2rem"
              align="center"
              justify="space-between"
              key={o.id}
            >
              <Box>
                {o.avatarUrl ? (
                  <Image
                    m="0.25rem"
                    borderRadius="20%"
                    height="50px"
                    width="50px"
                    src={o.avatarUrl}
                    alt="profile picture"
                  />
                ) : (
                  <Box m="0.25rem">
                    <Avatar />
                  </Box>
                )}
                <Text color="text.primary">
                  {o.firstName} {o.lastName}
                </Text>
              </Box>
              <Flex align="center">
                <Text color="text.primary" fontStyle="italic">
                  ${o.bid.toFixed(2)}
                </Text>
                <RouterLink
                  to={`/menu/${slugify(user.firstName, user.lastName)}/clothes/${
                    o.clothId
                  }`}
                >
                  <Image
                    mx="1rem"
                    borderRadius="20%"
                    height="50px"
                    width="50px"
                    src={o.clothUrl}
                    alt="a piece of clothing that the seamser will make"
                  />
                </RouterLink>
                <Flex mx="1rem" direction="column">
                  <Text fontStyle="italic" color="text.primary">
                    Due Date
                  </Text>
                  <Text color="black.primary">{dayjs(o.dueDate).format('L')}</Text>
                </Flex>

                {o.complete ? (
                  <Box>
                    <Text mx="1rem" color="green" fontWeight="bold">
                      Completed
                    </Text>

                    <Button
                      mx="0.25rem"
                      mt="0.5rem"
                      onClick={() => completeOrder(o.id, false, o.bid)}
                    >
                      UnComplete
                    </Button>
                  </Box>
                ) : (
                  <Box mx="1rem">
                    <Button onClick={() => completeOrder(o.id, true, o.bid)}>
                      Complete
                    </Button>
                  </Box>
                )}
              </Flex>
            </Flex>
          );
        })}
      </Box>
      {pagination.page !== pagination.totalPages && (
        <Flex justify="center">
          <Button onClick={() => getOrders(true)}>Load More...</Button>
        </Flex>
      )}
    </Box>
  );
};

export default Orders;
