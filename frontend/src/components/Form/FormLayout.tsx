import { Box } from '@chakra-ui/react';

interface IFormLayoutProps {
  height: string;
  width: string[];
  children: JSX.Element;
}

const FormLayout = ({ height, width, children }: IFormLayoutProps) => {
  return (
    <Box
      boxShadow="rgba(100, 100, 111, 0.2) 0px 7px 29px 0px"
      minH={height}
      width={width}
      borderRadius="8px"
      p="0.5rem"
      bg="#fff"
    >
      {children}
    </Box>
  );
};

export default FormLayout;
