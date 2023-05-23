import { Box, Button, Flex, Heading, Spinner, Text } from '@chakra-ui/react';
import FormLayout from '../Form/FormLayout';
import { Link as RouterLink, useNavigate } from 'react-router-dom';
import { useContext, useState } from 'react';
import { loginFormState } from '../../state/initialState';
import { ILoginForm, IUserContext } from '../../interfaces';
import FormInput from '../Form/FormInput';
import { Client } from '../../util/client';
import { UserContext } from '../../context/user';

interface IFormProps {
  title: string;
  helperText: string;
}

const Form = ({ title, helperText }: IFormProps) => {
  const { stowTokens, updateUser } = useContext(UserContext) as IUserContext;
  const navigate = useNavigate();
  const [loginForm, setLoginForm] = useState(loginFormState);
  const [error, setError] = useState('');
  const [isLoading, setIsLoading] = useState(false);

  const updateField = (name: string, value: string, attribute: string) => {
    setLoginForm((prevState) => ({
      ...prevState,
      [name]: { ...prevState[name as keyof ILoginForm], [attribute]: value },
    }));
  };

  const goToCreateAccount = (e: React.MouseEvent<HTMLButtonElement>) => {
    e.stopPropagation();
    navigate('/register');
  };

  const clearErrors = () => {
    for (const [key, _] of Object.entries(loginForm)) {
      updateField(key, '', 'error');
    }
  };

  const checkForErrors = () => {
    let errors = false;
    for (const [_, field] of Object.entries(loginForm)) {
      if (field.error.length > 0 || field.value.length === 0) {
        errors = true;
      }
    }
    return errors;
  };

  const handleOnSubmit = (e: React.ChangeEvent<HTMLFormElement>) => {
    e.preventDefault();
    setError('');
    clearErrors();
    if (checkForErrors()) return;

    setIsLoading(true);
    Client.login(loginForm)
      .then((res) => {
        const { refreshToken, token, userDto } = res.data;
        setIsLoading(false);
        if (res.status === 200) {
          stowTokens({ refreshToken, token });
          updateUser(userDto);
          navigate('/');
        }
      })
      .catch((err) => {
        console.log(err);
        setIsLoading(false);
        setError(err.response.data.message);
      });
  };

  return (
    <FormLayout
      height="500px"
      width={['95%', '450px', '450px']}
      boxShadow={false}
      borderRadius={false}
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
          <Box textAlign="center">
            <Text fontSize="0.85rem" color="red.300">
              {error}
            </Text>
          </Box>
        )}
        <FormInput
          updateField={updateField}
          value={loginForm.email.value}
          error={loginForm.email.error}
          name={loginForm.email.name}
          type={loginForm.email.type}
          errorField="Email"
          width="100%"
          htmlFor="email"
          label="Email"
        />
        <FormInput
          updateField={updateField}
          value={loginForm.password.value}
          error={loginForm.password.error}
          name={loginForm.password.name}
          type={loginForm.password.type}
          errorField="Password"
          width="100%"
          htmlFor="password"
          label="Password"
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
            <Flex color="text.primary" justify="flex-end">
              <RouterLink to="/forgot-password">Forgot Password?</RouterLink>
            </Flex>
            <Flex my="3rem" justify="space-between">
              <Button colorScheme="blue" width="40%" type="submit">
                Login
              </Button>
              <Button colorScheme="gray" width="40%" onClick={goToCreateAccount}>
                Create Account
              </Button>
            </Flex>
          </Box>
        )}
      </form>
    </FormLayout>
  );
};

export default Form;
