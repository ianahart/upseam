import { Box } from '@chakra-ui/react';

interface IFormLayoutProps {
  height: string;
  width: string[];
  boxShadow: boolean;
  borderRadius: boolean;
  children: JSX.Element;
}

const FormLayout = ({
  height,
  width,
  boxShadow,
  borderRadius,
  children,
}: IFormLayoutProps) => {
  return (
    <Box
      m="0 auto"
      boxShadow={`${boxShadow ? 'rgba(100, 100, 111, 0.2) 0px 7px 29px 0px' : ''}`}
      minH={height}
      width={width}
      borderRadius={borderRadius ? '8px' : '0px'}
      p="0.5rem"
      bg="#fff"
    >
      {children}
    </Box>
  );
};

export default FormLayout;
