import { Box, Flex, Image, Text, chakra, shouldForwardProp } from '@chakra-ui/react';
import { isValidMotionProp, motion } from 'framer-motion';

interface IMarketingProps {
  img: string;
  marketingInformation: string;
  isContentReverse: boolean;
}

const Marketing = ({ img, marketingInformation, isContentReverse }: IMarketingProps) => {
  return (
    <Flex
      p="0.5rem"
      my="10rem"
      direction={
        isContentReverse
          ? ['column', 'column', 'row-reverse']
          : ['column', 'column', 'row']
      }
      align="center"
      minH="600px"
      width="100%"
      bg="#fff"
    >
      <motion.div
        initial={{ opacity: 0, y: '300' }}
        transition={{ delay: 0.5 }}
        whileInView={{ opacity: 1, y: 0 }}
      >
        <Text
          fontSize="1.2rem"
          textAlign="center"
          my={['2rem', '2rem', '0']}
          mx="3rem"
          color="text.primary"
        >
          {marketingInformation}
        </Text>
      </motion.div>
      <motion.div
        initial={{ opacity: 0, x: '-300px' }}
        transition={{ delay: 0.5 }}
        whileInView={{ opacity: 1, x: 0 }}
      >
        <Image height="400px" src={img} alt="marketing related information" />
      </motion.div>
    </Flex>
  );
};

export default Marketing;
