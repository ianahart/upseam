import { Box, Container } from '@chakra-ui/react';
import { Outlet } from 'react-router-dom';
import Navbar from '../components/Navbar/Navbar';
import Footer from '../components/Footer/Footer';
import WithAxios from '../hooks/withAxios';

const RootLayout = () => {
  return (
    <Box className="App" minH="100vh">
      <Navbar />
      <WithAxios>
        <Outlet />
      </WithAxios>
      <Footer />
    </Box>
  );
};

export default RootLayout;
