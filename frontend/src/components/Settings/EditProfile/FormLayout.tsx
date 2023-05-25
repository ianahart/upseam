import { Box } from '@chakra-ui/react';

interface IFormLayoutProps {
  children: JSX.Element;
}

const FormLayout = ({ children }: IFormLayoutProps) => {
  return (
    <Box
      minH="300px"
            p="0.25rem"
      border="1px solid"
      borderBottomLeftRadius="8px"
      borderTopLeftRadius="8px"
      borderRight="none"
      borderColor="light.primary"
    >
      {children}
    </Box>
  );
};

export default FormLayout;
