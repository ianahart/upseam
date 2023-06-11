import { Box, Flex, Image, Text } from '@chakra-ui/react';

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
      <Text
        fontSize="1.2rem"
        textAlign="center"
        my={['2rem', '2rem', '0']}
        mx="3rem"
        color="text.primary"
      >
        {marketingInformation}
      </Text>
      <Image height="400px" src={img} alt="marketing related information" />
    </Flex>
  );
};

export default Marketing;
