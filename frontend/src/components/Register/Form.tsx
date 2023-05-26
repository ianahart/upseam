import { Box, Button, Flex, Link, Heading, Spinner, Text } from '@chakra-ui/react';
import FormLayout from '../Form/FormLayout';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { IRegisterForm } from '../../interfaces';
import { registerFormState } from '../../state/initialState';
import FormInput from '../Form/FormInput';
import { Client } from '../../util/client';

interface IFormProps {
  title: string;
  helperText: string;
}

const Form = ({ title, helperText }: IFormProps) => {
  const navigate = useNavigate();
  const [registerForm, setRegisterForm] = useState(registerFormState);
  const [error, setError] = useState('');
  const [isLoading, setIsLoading] = useState(false);

  const updateField = (name: string, value: string, attribute: string) => {
    setRegisterForm((prevState) => ({
      ...prevState,
      [name]: { ...prevState[name as keyof IRegisterForm], [attribute]: value },
    }));
  };

  const checkForErrors = () => {
    let errors = false;
    for (const [_, field] of Object.entries(registerForm)) {
      const { error } = field;
      if (error.length > 0) {
        errors = true;
      }
    }
    return errors;
  };

  const clearErrors = () => {
    for (const [key, _] of Object.entries(registerForm)) {
      updateField(key, '', 'error');
    }
  };

  const handleOnSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    setError('');
    clearErrors();
    if (checkForErrors()) return;
    setIsLoading(true);
    Client.registerSeamster(registerForm)
      .then((res) => {
        setIsLoading(false);
        if (res.status === 201) {
          navigate('/login');
        }
      })
      .catch((err) => {
        if (err.response.status === 400) {
          setError(err.response.data.message);
          setIsLoading(false);
        }
      });
  };
  return (
    <FormLayout
      width={['95%', '450px', '450px']}
      height="600px"
      boxShadow={true}
      borderRadius={true}
    >
      <form onSubmit={handleOnSubmit}>
        <Box my="2rem" as="header">
          <Heading color="black.primary" textAlign="center" as="h1">
            {title}
          </Heading>
          <Text textAlign="center" color="text.primary">
            {helperText}
          </Text>
        </Box>
        {error.length > 0 && (
          <Flex justify="center">
            <Text fontSize="0.85rem" color="red.300">
              {error}
            </Text>
          </Flex>
        )}
        <FormInput
          updateField={updateField}
          value={registerForm.firstName.value}
          error={registerForm.firstName.error}
          name={registerForm.firstName.name}
          type={registerForm.firstName.type}
          errorField="First name"
          width="100%"
          htmlFor="firstName"
          label="First Name"
        />
        <FormInput
          updateField={updateField}
          value={registerForm.lastName.value}
          error={registerForm.lastName.error}
          name={registerForm.lastName.name}
          type={registerForm.lastName.type}
          errorField="Last name"
          width="100%"
          htmlFor="lastName"
          label="Last Name"
        />
        <FormInput
          updateField={updateField}
          value={registerForm.email.value}
          error={registerForm.email.error}
          name={registerForm.email.name}
          type={registerForm.email.type}
          errorField="Email"
          width="100%"
          htmlFor="email"
          label="Email"
        />
        <FormInput
          updateField={updateField}
          value={registerForm.password.value}
          error={registerForm.password.error}
          name={registerForm.password.name}
          type={registerForm.password.type}
          errorField="Password"
          width="100%"
          htmlFor="password"
          label="Password"
        />
        <FormInput
          updateField={updateField}
          value={registerForm.confirmPassword.value}
          error={registerForm.confirmPassword.error}
          name={registerForm.confirmPassword.name}
          type={registerForm.confirmPassword.type}
          errorField="Confirm password"
          width="100%"
          htmlFor="confirmPassword"
          label="Confirm Password"
        />
        {isLoading ? (
          <Flex justify="center">
            <Spinner
              thickness="4px"
              speed="0.65s"
              emptyColor="gray.200"
              color="blue.500"
              size="xl"
            />
          </Flex>
        ) : (
          <Box>
            <Box width="100%">
              <Button type="submit" width="100%" colorScheme="blue">
                Create your account
              </Button>
            </Box>
            <Box my="2rem" textAlign="center">
              <Text>By creating an account you agree to our</Text>
              <Link
                fontWeight="bold"
                href="https://www.termsfeed.com/live/6d5c9d4f-ec88-4c01-b28c-862392fb762c"
              >
                Privacy Policy
              </Link>
            </Box>
          </Box>
        )}
      </form>
    </FormLayout>
  );
};

export default Form;
