import { Box } from '@chakra-ui/react';
import Header from '../Menu/Header';
import ShippingForm from './ShippingForm';

const Shipping = () => {
  return (
    <Box>
      <Header heading="Shipping Information" />

      <ShippingForm />
    </Box>
  );
};

export default Shipping;
