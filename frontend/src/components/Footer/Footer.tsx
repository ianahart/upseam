import {
  Button,
  ButtonGroup,
  Container,
  Divider,
  IconButton,
  Input,
  Stack,
  Text,
  Link,
} from '@chakra-ui/react';
import { useState } from 'react';
import { Client } from '../../util/client';
const Footer = () => {
  const [email, setEmail] = useState('');
  const [error, setError] = useState('');

  const subscribe = () => {
    Client.subscribeToUpseam(email)
      .then((res) => {
        setEmail('');
      })
      .catch((err) => {
        if (err.response.status === 400) {
          setError(err.response.data.message);
        }
      });
  };

  const handleOnChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = e.target;
    setEmail(value);
  };

  const handleOnClick = () => {
    setError('');
    if (email.trim().length === 0) {
      setError('Please provide an email address');
    }
    if (error.length) {
      return;
    }
    subscribe();
  };

  return (
    <Container maxW="100%" textAlign="center" as="footer" role="contentinfo">
      <Stack
        spacing="8"
        direction={{ base: 'column', md: 'row' }}
        justify="center"
        py={{ base: '12', md: '16' }}
      >
        <Stack
          direction={{ base: 'column-reverse', md: 'column', lg: 'row' }}
          spacing={{ base: '12', md: '8' }}
        >
          <Stack direction="row" spacing="8">
            <Stack spacing="4" minW="36" flex="1">
              <Text fontSize="sm" fontWeight="semibold" color="subtle">
                Product
              </Text>
              <Stack spacing="3" shouldWrapChildren>
                <Button variant="link">How it works</Button>
                <Button variant="link">Pricing</Button>
                <Button variant="link">Use Cases</Button>
              </Stack>
            </Stack>
            <Stack spacing="4" minW="36" flex="1">
              <Text fontSize="sm" fontWeight="semibold" color="subtle">
                Legal
              </Text>
              <Stack spacing="3" shouldWrapChildren>
                <Button variant="link">
                  <Link
                    fontWeight="bold"
                    href="https://www.termsfeed.com/live/6d5c9d4f-ec88-4c01-b28c-862392fb762c"
                  >
                    Privacy
                  </Link>
                </Button>
              </Stack>
            </Stack>
          </Stack>
          <Stack spacing="4">
            <Text fontSize="sm" fontWeight="semibold" color="subtle">
              Stay up to date
            </Text>
            {error.length > 0 && (
              <Text color="red.500" fontSize="0.85rem">
                {error}
              </Text>
            )}
            <Stack spacing="4" direction={{ base: 'column', sm: 'row' }}>
              <Input
                value={email}
                onChange={handleOnChange}
                placeholder="Enter your email"
                type="email"
                required
              />
              <Button
                onClick={handleOnClick}
                variant="primary"
                type="submit"
                flexShrink={0}
              >
                Subscribe
              </Button>
            </Stack>
          </Stack>
        </Stack>
      </Stack>
      <Divider />
      <Stack pt="8" pb="12" justify="center" align="center">
        <Text textAlign="center" fontSize="sm" color="subtle">
          &copy; {new Date().getFullYear()} Upsteam All rights reserved.
        </Text>
      </Stack>
    </Container>
  );
};

export default Footer;
