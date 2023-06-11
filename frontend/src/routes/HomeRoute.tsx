import { Box } from '@chakra-ui/react';
import homeBG from '../assets/home.png';
import contactsImg from '../assets/contacts.png';
import bidsImg from '../assets/bids.png';
import messagesImg from '../assets/mesages.png';

import Marketing from '../components/Home/Marketing';

const HomeRoute = () => {
  return (
    <Box
      backgroundSize="cover"
      backgroundPosition="center"
      backgroundImage={`url(${homeBG})`}
      minH="100vh"
    >
      <Marketing
        isContentReverse={false}
        marketingInformation="Connect with other users to talk about clothing requests and bids"
        img={contactsImg}
      />

      <Marketing
        isContentReverse={true}
        marketingInformation="As a seamster make bids on clothing requests by users."
        img={bidsImg}
      />

      <Marketing
        isContentReverse={false}
        marketingInformation="Become friends and get to know each other more personally for future work together."
        img={messagesImg}
      />
    </Box>
  );
};

export default HomeRoute;
