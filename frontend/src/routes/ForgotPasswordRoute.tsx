import { Box, Button, Flex, Heading, Spinner, Text } from '@chakra-ui/react';
import forgotPasswordBG from '../assets/forgot-password.png';
import FormLayout from '../components/Form/FormLayout';
import { useState } from 'react';
import { forgotPasswordFormState } from '../state/initialState';
import FormInput from '../components/Form/FormInput';
import { IForgotPasswordForm } from '../interfaces';
import { Client } from '../util/client';

const ForgotPasswordRoute = () => {
  const [forgotPasswordForm, setForgotPasswordForm] = useState(forgotPasswordFormState);
  const [error, setError] = useState('');
  const [isLoading, setIsLoading] = useState(false);
  const [emailSent, setEmailSent] = useState(false);

  const updateField = (name: string, value: string, attribute: string) => {
    setForgotPasswordForm((prevState) => ({
      ...prevState,
      [name]: { ...prevState[name as keyof IForgotPasswordForm], [attribute]: value },
    }));
  };

  const handleOnSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    setError('');
    setEmailSent(false);
    if (forgotPasswordForm.email.value.length === 0) return;
    setIsLoading(true);
    Client.forgotPassword(forgotPasswordForm.email.value)
      .then((res) => {
        if (res.status === 200) {
          setEmailSent(true);
          setIsLoading(false);
        }
      })
      .catch((err) => {
        setError(err.response.data.message);
        setIsLoading(false);
      });
  };
  return (
    <Box
      minH="100vh"
      backgroundImage={`url(${forgotPasswordBG})`}
      backgroundPosition="center"
      backgroundSize="cover"
    >
      <Flex minH="70vh" direction="column" align="center" justify="center">
        <FormLayout
          height="400px"
          width={['95%', '95%', '400px']}
          boxShadow={true}
          borderRadius={true}
        >
          <form onSubmit={handleOnSubmit}>
            <Box my="2rem" textAlign="center">
              <Heading color="black.primary" as="h2">
                Forgot Password
              </Heading>
              <Text mt="1.5rem" mx="auto" width="300px" color="text.primary">
                Enter the email address associated with your account and we'll send you a
                link to reset your password.{' '}
              </Text>
              {error.length > 0 && (
                <Text fontSize="0.85rem" textAlign="center" color="red.500">
                  {error}
                </Text>
              )}
              {emailSent && (
                <Text mt="2rem" color="text.primary">
                  Please check your email
                </Text>
              )}
            </Box>
            {isLoading && (
              <Flex justify="center">
                <Spinner
                  thickness="4px"
                  speed="0.65s"
                  emptyColor="gray.200"
                  color="blue.500"
                  size="xl"
                />
              </Flex>
            )}
            {!isLoading && (
              <Box>
                <FormInput
                  updateField={updateField}
                  value={forgotPasswordForm.email.value}
                  error={forgotPasswordForm.email.error}
                  name={forgotPasswordForm.email.name}
                  type={forgotPasswordForm.email.type}
                  errorField="Email"
                  width="100%"
                  htmlFor="email"
                  label="Email"
                />
                <Flex mt="2rem" justifyContent="center">
                  <Button type="submit" width="100%" colorScheme="blue">
                    Send Email
                  </Button>
                </Flex>
              </Box>
            )}
          </form>
        </FormLayout>
      </Flex>
    </Box>
  );
};

export default ForgotPasswordRoute;
