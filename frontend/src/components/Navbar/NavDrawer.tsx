import { useRef } from 'react';

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
} from '@chakra-ui/react';
import NavLinks from './NavLinks';

const NavDrawer = () => {
  const { isOpen, onOpen, onClose } = useDisclosure();
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
          <DrawerHeader>millie@gmail.com</DrawerHeader>
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
