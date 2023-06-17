import { Box, Flex, Text, Heading, Button } from '@chakra-ui/react';
import FormLayout from '../components/Form/FormLayout';
import { useNavigate, useSearchParams } from 'react-router-dom';
import forgotPasswordBG from '../assets/forgot-password.png';
import { useState } from 'react';
import { resetPasswordState } from '../state/initialState';
import FormInput from '../components/Form/FormInput';
import { IResetPasswordForm } from '../interfaces';
import { Client } from '../util/client';

const ResetPasswordRoute = () => {
  const navigate = useNavigate();
  const [params, setSearchParams] = useSearchParams();
  console.log(setSearchParams);
  const [resetPasswordForm, setResetPasswordForm] = useState(resetPasswordState);
  const [error, setError] = useState('');

  const updateField = (name: string, value: string, attribute: string) => {
    setResetPasswordForm((prevState) => ({
      ...prevState,
      [name]: { ...prevState[name as keyof IResetPasswordForm], [attribute]: value },
    }));
  };

  const clearErrors = () => {
    for (const [key, _] of Object.entries(resetPasswordForm)) {
      updateField(key, '', 'error');
    }
  };

  const checkForErrors = () => {
    let errors = false;
    for (const [_, field] of Object.entries(resetPasswordForm)) {
      const { error, value } = field;
      if (error.length > 0 || value.length === 0) {
        errors = true;
      }
    }
    return errors;
  };

  const handleOnSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    clearErrors();
    setError('');
    e.preventDefault();
    if (checkForErrors()) return;
    Client.resetPassword(params.get('uid'), params.get('token'), resetPasswordForm)
      .then((res) => {
        if (res.status === 200) {
          navigate('/');
        }
      })
      .catch((err) => {
        if (err.response.status === 400) {
          setError(err.response.data.message);
        }
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
                Reset Password
              </Heading>
              <Text mt="1.5rem" mx="auto" width="300px" color="text.primary">
                Your new password should not be the same as your old password.
              </Text>
              {error.length > 0 && (
                <Text textAlign="center" color="red.500" fontSize="0.85rem">
                  {error}
                </Text>
              )}
            </Box>

            <FormInput
              updateField={updateField}
              value={resetPasswordForm.password.value}
              error={resetPasswordForm.password.error}
              name={resetPasswordForm.password.name}
              type={resetPasswordForm.password.type}
              errorField="New password"
              width="100%"
              htmlFor="password"
              label="New Password"
            />
            <FormInput
              updateField={updateField}
              value={resetPasswordForm.confirmPassword.value}
              error={resetPasswordForm.confirmPassword.error}
              name={resetPasswordForm.confirmPassword.name}
              type={resetPasswordForm.confirmPassword.type}
              errorField="Confirm password"
              width="100%"
              htmlFor="confirmPassword"
              label="Confirm Password"
            />
            <Flex justify="center">
              <Button type="submit" width="100%" colorScheme="blue">
                Reset Password
              </Button>
            </Flex>
          </form>
        </FormLayout>
      </Flex>
    </Box>
  );
};

export default ResetPasswordRoute;
