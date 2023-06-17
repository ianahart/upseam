import * as dayjs from 'dayjs';
import localizedFormat from 'dayjs/plugin/localizedFormat';
dayjs.extend(localizedFormat);

import {
  Box,
  Card,
  CardBody,
  CardFooter,
  Heading,
  Divider,
  Image,
  Stack,
  Text,
  Flex,
  Tooltip,
} from '@chakra-ui/react';
import { Link as RouterLink } from 'react-router-dom';
import { IClothes, IUserContext } from '../../../interfaces';
import { AiOutlineEdit } from 'react-icons/ai';
import { useContext } from 'react';
import { UserContext } from '../../../context/user';
import { slugify } from '../../../util';

interface IClothProps {
  cloth: IClothes;
  isEditable: boolean;
}

const Cloth = ({ cloth, isEditable }: IClothProps) => {
  const { user } = useContext(UserContext) as IUserContext;
  return (
    <Card maxW="sm">
      <CardBody>
        <RouterLink
          to={`/menu/${slugify(user.firstName, user.lastName)}/clothes/${cloth.id}`}
        >
          <Image
            src={cloth.clothUrl}
            alt="a peice of clothing belonging to you"
            borderRadius="lg"
          />
        </RouterLink>
        <Stack mt="6" spacing="3">
          <Heading color="black.primary" size="md">
            {cloth.firstName} {cloth.lastName}
          </Heading>
          <Text>{cloth.email}</Text>
          <Text color="black.primary">{cloth.description}</Text>
          <Flex justify="space-between">
            <Box>
              <Text color="text.primary" fontSize="0.85rem">
                Preferred date
              </Text>
              <Text color="blue.500" fontSize="md">
                {dayjs(cloth.dueDate).format('L')}
              </Text>
            </Box>
            <Box>
              <Text color="text.primary" fontSize="0.85rem">
                Size
              </Text>
              <Text color="blue.500" fontSize="md">
                {cloth.size}
              </Text>
            </Box>
          </Flex>
        </Stack>
      </CardBody>
      <Divider />
      <CardFooter display="flex" justify="space-between" alignItems="center">
        <Box>
          <Text color="text.primary" fontSize="0.8rem">
            Request created
          </Text>
          <Text fontSize="0.8rem" color="text.primary">
            {dayjs(cloth.createdAt).format('L')}
          </Text>
        </Box>
        <Box cursor="pointer" color="text.primary">
          <RouterLink
            to={`/menu/${slugify(user.firstName, user.lastName)}/clothes/edit/${
              cloth.id
            }`}
          >
            <Tooltip label="Edit Clothing" fontSize="sm" placement="top-end">
              <span>
                <AiOutlineEdit fontSize="1rem" />
              </span>
            </Tooltip>
          </RouterLink>
        </Box>
      </CardFooter>
    </Card>
  );
};

export default Cloth;
