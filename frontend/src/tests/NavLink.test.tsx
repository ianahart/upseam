import { screen, render } from '@testing-library/react';
import NavLink from '../components/Navbar/NavLink';
import { ChakraProvider } from '@chakra-ui/react';
import { MemoryRouter } from 'react-router-dom';

describe('NavLink', () => {
  test('should render correctly', () => {
    const props = {
      icon: undefined,
      to: '/',
      routeName: 'Home',
    };
    render(
      <ChakraProvider>
        <MemoryRouter>
          <NavLink {...props} />
        </MemoryRouter>
      </ChakraProvider>
    );

    expect(screen.getByText('Home')).toBeInTheDocument();
  });
});
