import { Box, Heading } from '@chakra-ui/react';

interface IHeaderProps {
  heading: string;
}

const Header = ({ heading }: IHeaderProps) => {
  return (
    <Box mt="2rem" as="header">
      <Heading color="text.primary">{heading}</Heading>
    </Box>
  );
};
export default Header;
