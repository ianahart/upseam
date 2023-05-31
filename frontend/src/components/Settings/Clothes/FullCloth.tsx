import { Box, Flex, Spinner, Image, Text } from '@chakra-ui/react';
import { useParams } from 'react-router-dom';
import { useEffectOnce } from '../../../hooks/useEffectOnce';
import { Client } from '../../../util/client';
import { IFullCloth } from '../../../interfaces';
import { fullClothState } from '../../../state/initialState';
import { useState } from 'react';
import { AiOutlineInfoCircle, AiOutlineMail, AiOutlineUser } from 'react-icons/ai';
import { BiRuler } from 'react-icons/bi';
import Header from '../Header';
import FormHeader from '../EditProfile/FormHeader';
import * as dayjs from 'dayjs';
import relativeTime from 'dayjs/plugin/relativeTime';
dayjs.extend(relativeTime);

const FullCloth = () => {
  const [cloth, setCloth] = useState<IFullCloth>(fullClothState);
  const [isLoading, setIsLoading] = useState(false);

  const params = useParams();

  useEffectOnce(() => {
    if (params.clothId !== undefined) {
      getCloth(params.clothId);
    }
  });

  const getCloth = (clothId: string) => {
    setIsLoading(true);
    Client.getCloth(clothId)
      .then((res) => {
        setCloth(res.data.cloth);
        setIsLoading(false);
      })
      .catch((err) => {
        setIsLoading(false);
        throw new Error(err.response.data.message);
      });
  };

  return (
    <Box>
      <Header heading="Cloth" />
      <Flex
        mt="5rem"
        justify={['unset', 'unset', 'center']}
        direction={['column', 'row', 'row']}
      >
        {isLoading && (
          <Spinner
            thickness="4px"
            speed="0.65s"
            emptyColor="gray.200"
            color="blue.500"
            size="xl"
          />
        )}
      </Flex>
      <Box
        borderRadius="8px"
        border="1px solid"
        boxShadow="rgba(100, 100, 111, 0.2) 0px 7px 29px 0px"
        p="0.5rem"
        borderColor="#f4eeee"
      >
        <Flex justify="space-between" direction={['column', 'row', 'row']}>
          <Box>
            <FormHeader heading="Description" />
            <Image borderRadius="8px" src={cloth.clothUrl} alt={cloth.description} />
            <Flex align="center">
              <Box mr="0.5rem" color="blue.500">
                <AiOutlineInfoCircle />
              </Box>

              <Text
                color="black.primary"
                lineHeight="1.6"
                mt="3rem"
                width="300px"
                wordBreak="break-all"
              >
                {cloth.description}
              </Text>
            </Flex>
            <Flex mt="1.5rem" align="center">
              <Box mr="0.5rem" color="blue.500">
                <BiRuler />
              </Box>

              <Text
                color="black.primary"
                lineHeight="1.6"
                width="300px"
                wordBreak="break-all"
              >
                {cloth.size}
              </Text>
            </Flex>
          </Box>
          <Box className="user-info">
            <FormHeader heading="Personal information" />
            <Flex align="center">
              <Box mr="0.5rem" color="blue.500">
                <AiOutlineUser />
              </Box>
              <Text color="text.primary" wordBreak="break-all">
                {cloth.firstName} {cloth.lastName}
              </Text>
            </Flex>
            <Flex align="center">
              <Box mr="0.5rem" color="blue.500">
                <AiOutlineMail />
              </Box>

              <Text color="text.primary" wordBreak="break-all">
                {cloth.email}
              </Text>
            </Flex>
          </Box>
        </Flex>
        <Flex justify={['flex-start', 'flex-start', 'flex-end']}>
          <FormHeader heading="Extra Information" />
        </Flex>
        <Flex justify={['flex-start', 'flex-end', 'flex-end']}>
          <Flex direction="column">
            <Text color="text.primary">
              Date to be finished: {dayjs().to(cloth.dueDate)}
            </Text>
            <Text color="text.primary">Published {dayjs(cloth.createdAt).fromNow()}</Text>
            <Text color="text.primary">Updated {dayjs(cloth.updatedAt).fromNow()}</Text>
          </Flex>
        </Flex>
      </Box>
    </Box>
  );
};

export default FullCloth;
