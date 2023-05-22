import { Box, Flex } from '@chakra-ui/react';

interface IInitialIconProps {
  abbrev: string;
}

const InitialIcon = ({ abbrev }: IInitialIconProps) => {
  return (
    <Flex
      justify="center"
      align="center"
      color="#fff"
      direction="column"
      width="60px"
      height="60px"
      borderRadius="50%"
      bg="blue.500"
    >
      {abbrev}
    </Flex>
  );
};

export default InitialIcon;
