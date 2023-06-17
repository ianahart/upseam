import { Box, Flex, FormLabel, Switch } from '@chakra-ui/react';
import { useState } from 'react';
import registerBG from '../assets/register.jpg';
import Form from '../components/Register/Form';

const RegisterRoute = () => {
  const [isChecked, setIsChecked] = useState(true);

  const handleOnChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setIsChecked(e.target.checked);
  };

  return (
    <Box
      backgroundSize="cover"
      backgroundPosition="center"
      backgroundImage={`url(${registerBG})`}
      minH="100vh"
    >
      <Box p="1rem">
        <FormLabel fontWeight="bold" color="#fff">
          {isChecked ? 'Seamster Account' : 'Buyer Account'}
        </FormLabel>
        <Switch
          onChange={handleOnChange}
          isChecked={isChecked}
          colorScheme="blue"
          size="lg"
        />
      </Box>

      <Flex direction="column" align="center" justify="center" minH="100vh">
        <Form
          title="Create your account"
          helperText="Create an account to view and bid on clothes."
        />
      </Flex>
    </Box>
  );
};

export default RegisterRoute;
