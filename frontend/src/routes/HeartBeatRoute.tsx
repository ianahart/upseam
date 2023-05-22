import { Box } from '@chakra-ui/react';
import { Client } from '../util/client';
import { useEffectOnce } from '../hooks/useEffectOnce';
const HeartBeatRoute = () => {
  useEffectOnce(() => {
    Client.heartBeat()
      .then((res) => {
        console.log(res, 'success');
      })
      .catch((err) => {
        console.log(err, 'error');
      });
  });
  return <Box>HeartBeatRoute</Box>;
};

export default HeartBeatRoute;
