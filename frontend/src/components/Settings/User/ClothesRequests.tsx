import Header from '../Header';
import ClothesRequestsForm from './ClothesRequestForm';

const ClothesRequests = () => {
  return (
    <>
      <Header heading="Clothing Requests" />
      <ClothesRequestsForm type="create" buttonText="Create" />
    </>
  );
};

export default ClothesRequests;
