import { render, screen } from '@testing-library/react';
import NavLinks from '../components/Navbar/NavLinks';
import { ChakraProvider } from '@chakra-ui/react';
import { MemoryRouter } from 'react-router-dom';

describe('NavLinks', () => {
  test('should render correctly', () => {
    render(
      <ChakraProvider>
        <MemoryRouter>
          <NavLinks />
        </MemoryRouter>
      </ChakraProvider>
    );
    screen.logTestingPlaygroundURL();
    const icons = screen.getAllByRole('img');
    expect(icons).toHaveLength(3);
  });
});
