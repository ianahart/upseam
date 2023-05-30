import { Box } from '@chakra-ui/react';
import ClothesRequestsForm from '../User/ClothesRequestForm';
import Header from '../Header';

const EditCloth = () => {
  return (
    <Box>
      <Header heading="Update piece of clothing" />

      <ClothesRequestsForm type="update" buttonText="Update" />
    </Box>
  );
};

export default EditCloth;
