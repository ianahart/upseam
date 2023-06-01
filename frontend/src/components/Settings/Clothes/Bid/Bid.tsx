import { Td, Text, Tr, Image } from '@chakra-ui/react';
import { IBid } from '../../../../interfaces';
import * as dayjs from 'dayjs';
import relativeTime from 'dayjs/plugin/relativeTime';
import InitialIcon from '../../../Shared/InitialIcon';
dayjs.extend(relativeTime);

export interface IBidProps {
  _bid: IBid;
}

const Bid = ({ _bid }: IBidProps) => {
  console.log(_bid);
  return (
    <Tr>
      <Td display="flex" align="center">
        {!_bid.avatarUrl ? (
          <InitialIcon />
        ) : (
          <Image
            height="45px"
            width="45px"
            borderRadius="50%"
            src={_bid.avatarUrl}
            alt={`Description of ${_bid.firstName}`}
          />
        )}
        <Text ml="0.25rem" mb="0.25rem" color="text.primary">
          {_bid.firstName} {_bid.lastName}
        </Text>
      </Td>
      <Td color="blue.500">${_bid.bid.toFixed(2)}</Td>
      <Td fontStyle="italic" color="text.primary">
        {' '}
        {dayjs(_bid.createdAt).fromNow()}
      </Td>
    </Tr>
  );
};

export default Bid;
