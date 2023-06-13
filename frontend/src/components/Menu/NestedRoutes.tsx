import { Box, Flex, Button } from '@chakra-ui/react';
import { AiOutlinePlus, AiOutlineUser, AiOutlineMail } from 'react-icons/ai';
import { BsPencil } from 'react-icons/bs';
import { BiBookContent } from 'react-icons/bi';
import NestedRoute from './NestedRoute';
import { useContext, useState } from 'react';
import { UserContext } from '../../context/user';
import { IUserContext } from '../../interfaces';
import { GiClothes } from 'react-icons/gi';
import { AiOutlineShoppingCart, AiOutlineInbox } from 'react-icons/ai';

const NestedRoutes = () => {
  const { user } = useContext(UserContext) as IUserContext;
  const [activeRoute, setActiveRoute] = useState('profile');

  return (
    <Flex
      direction="column"
      m={['0', '1rem', '1rem']}
      minW={['100%', '250px', '250px']}
      minH="100vh"
    >
      <Box onClick={() => setActiveRoute('profile')}>
        <NestedRoute
          activeRoute={activeRoute}
          to="profile"
          routeName="My Profile"
          icon={<AiOutlineUser />}
        />
      </Box>
      {user.role === 'USER' && (
        <Box onClick={() => setActiveRoute('my-clothes')}>
          <NestedRoute
            activeRoute={activeRoute}
            to="my-clothes"
            routeName="My Clothes"
            icon={<GiClothes />}
          />
        </Box>
      )}
      <Box onClick={() => setActiveRoute('edit-profile')}>
        <NestedRoute
          activeRoute={activeRoute}
          to="edit-profile"
          routeName="Edit Profile"
          icon={<BsPencil />}
        />
      </Box>
      {user.role === 'USER' && (
        <Box onClick={() => setActiveRoute('requests')}>
          <NestedRoute
            activeRoute={activeRoute}
            to="requests"
            routeName="Request Clothes"
            icon={<AiOutlinePlus />}
          />
        </Box>
      )}

      {user.role === 'SEAMSTER' && (
        <Box onClick={() => setActiveRoute('orders')}>
          <NestedRoute
            activeRoute={activeRoute}
            to="orders"
            routeName="Orders"
            icon={<AiOutlineInbox />}
          />
        </Box>
      )}

      <Box onClick={() => setActiveRoute('clothes')}>
        <NestedRoute
          activeRoute={activeRoute}
          to="clothes"
          routeName="Bid On Clothes"
          icon={<AiOutlineShoppingCart />}
        />
      </Box>

      <Box onClick={() => setActiveRoute('contacts')}>
        <NestedRoute
          activeRoute={activeRoute}
          to="contacts"
          routeName="Contacts"
          icon={<BiBookContent />}
        />
      </Box>
      <Box onClick={() => setActiveRoute('messages')}>
        <NestedRoute
          activeRoute={activeRoute}
          to="messages"
          routeName="Messages"
          icon={<AiOutlineMail />}
        />
      </Box>

      <Box my="1rem">
        <Button
          _hover={{ background: 'transparent' }}
          border="none"
          color="red.500"
          p="0"
          backgroundColor="transparent"
        >
          Delete Account
        </Button>
      </Box>
    </Flex>
  );
};

export default NestedRoutes;
