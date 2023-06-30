import { Box, Flex, Button, Text } from '@chakra-ui/react';
import { AiOutlineUser, AiOutlineMail } from 'react-icons/ai';
import { BsPencil, BsTicket } from 'react-icons/bs';
import { CiSettings } from 'react-icons/ci';
import { BiBookContent } from 'react-icons/bi';
import NestedRoute from './NestedRoute';
import { useContext, useState } from 'react';
import { UserContext } from '../../context/user';
import { IUserContext } from '../../interfaces';
import { GiClothes } from 'react-icons/gi';
import { MdOutlineLocalShipping, MdOutlineRateReview } from 'react-icons/md';
import { AiOutlineShoppingCart, AiOutlineInbox } from 'react-icons/ai';
import { TbFileInvoice } from 'react-icons/tb';

const NestedRoutes = () => {
  const { user } = useContext(UserContext) as IUserContext;
  const [activeRoute, setActiveRoute] = useState('profile');
  const [settingsOpen, setSettingsOpen] = useState(false);
  const [error, setError] = useState('');

  const deleteAccount = (e: React.MouseEvent<HTMLButtonElement>) => {
    setError('');
    e.stopPropagation();
    setError('Delete Account is currently unavailable for use.');
  };

  const handleSettingsOpen = () => {
    setSettingsOpen((prevState) => !prevState);
  };

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
            icon={<AiOutlineShoppingCart />}
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
          icon={<BsTicket />}
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

      {user.role === 'SEAMSTER' && (
        <Box onClick={() => setActiveRoute('reviews')}>
          <NestedRoute
            activeRoute={activeRoute}
            to="reviews"
            routeName="Reviews"
            icon={<MdOutlineRateReview />}
          />
        </Box>
      )}

      {user.role === 'USER' && (
        <Box onClick={() => setActiveRoute('invoices')}>
          <NestedRoute
            activeRoute={activeRoute}
            to="invoices"
            routeName="Invoices"
            icon={<TbFileInvoice />}
          />
        </Box>
      )}

      <Box>
        <Flex
          onClick={handleSettingsOpen}
          cursor="pointer"
          my="0.5rem"
          color="text.primary"
          fontWeight="bold"
          align="center"
        >
          <Box>
            <CiSettings />
          </Box>
          <Text>Settings</Text>
        </Flex>
        {settingsOpen && (
          <Box ml="2rem">
            <Box onClick={() => setActiveRoute('shipping')}>
              <NestedRoute
                activeRoute={activeRoute}
                to="shipping"
                routeName="Shipping"
                icon={<MdOutlineLocalShipping />}
              />
            </Box>
          </Box>
        )}
      </Box>

      <Box my="1rem">
        <Button
          onClick={deleteAccount}
          _hover={{ background: 'transparent' }}
          border="none"
          color="red.500"
          p="0"
          backgroundColor="transparent"
        >
          Delete Account
        </Button>
        {error.length > 0 && (
          <Text color="red.500" fontSize="0.85rem">
            {error}
          </Text>
        )}
      </Box>
    </Flex>
  );
};

export default NestedRoutes;
