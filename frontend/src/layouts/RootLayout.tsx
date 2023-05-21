import { Box, Container } from '@chakra-ui/react';
import { Outlet } from 'react-router-dom';
import Navbar from '../components/Navbar/Navbar';
import Footer from '../components/Footer/Footer';

const RootLayout = () => {
  return (
    <Box className="App" minH="100vh">
      <Navbar />
      <Outlet />
      <Footer />
    </Box>
  );
};

export default RootLayout;
