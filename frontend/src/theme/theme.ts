import { extendTheme } from '@chakra-ui/react';
export const theme = extendTheme({
  fonts: {
    heading: 'Roboto Condensed, sans-serif',
    body: 'Open Sans, sans-serif',
  },
  colors: {
    black: {
      primary: '#181717',
      secondary: '#200F21',
    },
    text: {
      primary: '#8a8f9d',
      secondary: '#444447',
      tertiary: '#403d40',
    },
    light: {
      primary: '#e4e3e3',
    },
    cover: {
      primary: 'rgba(54, 54, 54, 0.8)',
    },
  },
});
