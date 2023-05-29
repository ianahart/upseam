import { Box, Flex, Button } from '@chakra-ui/react';
import { AiOutlinePlus, AiOutlineUser } from 'react-icons/ai';
import { BsPencil } from 'react-icons/bs';
import { BiBookContent } from 'react-icons/bi';
import NestedRoute from './NestedRoute';
import { useContext, useState } from 'react';
import { UserContext } from '../../context/user';
import { IUserContext } from '../../interfaces';
import { GiClothes } from 'react-icons/gi';

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
        <Box onClick={() => setActiveRoute('clothes')}>
          <NestedRoute
            activeRoute={activeRoute}
            to="clothes"
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
            routeName="Clothes Requests"
            icon={<AiOutlinePlus />}
          />
        </Box>
      )}

      <Box onClick={() => setActiveRoute('contacts')}>
        <NestedRoute
          activeRoute={activeRoute}
          to="contacts"
          routeName="Contacts"
          icon={<BiBookContent />}
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
