import { Text } from '@chakra-ui/react';

interface IFormHeaderProps {
  heading: string;
}

const FormHeader = ({ heading }: IFormHeaderProps) => {
  return (
    <Text mt="1rem" mb="3rem" color="text.primary" fontSize="1.1rem">
      {heading}
    </Text>
  );
};

export default FormHeader;
