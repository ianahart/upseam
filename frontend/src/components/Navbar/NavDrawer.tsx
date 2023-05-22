import { useContext, useRef } from 'react';

import { RxHamburgerMenu } from 'react-icons/rx';
import {
  Box,
  VStack,
  Drawer,
  DrawerBody,
  DrawerHeader,
  DrawerOverlay,
  DrawerContent,
  DrawerCloseButton,
  useDisclosure,
  Text,
} from '@chakra-ui/react';
import NavLinks from './NavLinks';
import { UserContext } from '../../context/user';
import { IUserContext } from '../../interfaces';
import InitialIcon from '../Shared/InitialIcon';

const NavDrawer = () => {
  const { isOpen, onOpen, onClose } = useDisclosure();
  const { user } = useContext(UserContext) as IUserContext;
  const btnRef = useRef<HTMLDivElement>(null);

  return (
    <Box display={['block', 'block', 'none']}>
      <Box cursor="pointer" ref={btnRef} onClick={onOpen}>
        <RxHamburgerMenu fontSize="1.6rem" />
      </Box>
      <Drawer
        isOpen={isOpen}
        placement="right"
        onCloseComplete={() => true}
        onClose={onClose}
        finalFocusRef={btnRef}
      >
        <DrawerOverlay />
        <DrawerContent>
          <DrawerCloseButton />
          {user.isLoggedIn && (
            <DrawerHeader color="black.primary" fontSize="1rem">
              <Text>{user.email}</Text>
              <InitialIcon abbrev={user.abbreviation} />
            </DrawerHeader>
          )}
          <DrawerBody>
            <VStack align="baseline">
              <NavLinks />
            </VStack>
          </DrawerBody>
        </DrawerContent>
      </Drawer>
    </Box>
  );
};

export default NavDrawer;
