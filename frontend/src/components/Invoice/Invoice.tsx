import {
  Avatar,
  Box,
  Button,
  Image,
  Td,
  Text,
  Tr,
  Modal,
  ModalOverlay,
  ModalContent,
  ModalHeader,
  ModalFooter,
  ModalBody,
  ModalCloseButton,
  useDisclosure,
  ButtonGroup,
} from '@chakra-ui/react';
import { MdOutlineRateReview } from 'react-icons/md';
import { IInvoice, IUserContext } from '../../interfaces';
import { AiOutlineCheckCircle } from 'react-icons/ai';
import { useContext, useRef, useState } from 'react';
import { loadStripe } from '@stripe/stripe-js';
import {
  PaymentElement,
  Elements,
  useStripe,
  useElements,
} from '@stripe/react-stripe-js';
import { Client } from '../../util/client';
import { UserContext } from '../../context/user';
import CreateReview from '../Review/CreateReview';

interface IInvoiceProps {
  invoice: IInvoice;
  handleOnPayment: (invoiceId: number, receiverUserId: number) => void;
}

interface ICheckoutFormProps {
  invoice: IInvoice;
  handleOnPayment: (invoiceId: number, receiverUserId: number) => void;
}

const CheckoutForm = ({ invoice, handleOnPayment }: ICheckoutFormProps) => {
  const { user } = useContext(UserContext) as IUserContext;
  const stripe = useStripe();
  const elements = useElements();

  const [errorMessage, setErrorMessage] = useState<string | undefined>(undefined);

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    if (elements == null) {
      return;
    }

    const { error: submitError } = await elements.submit();
    if (submitError) {
      setErrorMessage(submitError.message);
      return;
    }

    const obj = {
      amount: invoice.bid,
      userId: user.id,
      email: user.email,
      billerUserId: invoice.userId,
    };
    const response = await Client.createPayment(obj);
    handleOnPayment(invoice.invoiceId, user.id);

    //@ts-ignore
    const { error } = await stripe?.confirmPayment({
      elements,
      clientSecret: response.data.clientSecret,
      confirmParams: {
        return_url: 'https://upseam.netlify.app/payment-success',
      },
    });

    if (error) {
      setErrorMessage(error.message);
    } else {
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <PaymentElement />
      <button type="submit" disabled={!stripe || !elements}>
        Pay
      </button>
      {/* Show error message to your customers */}
      {errorMessage && <div>{errorMessage}</div>}
    </form>
  );
};

const stripePromise = loadStripe(import.meta.env.VITE_STRIPE_PUBLISHABLE_KEY);

const options = {
  mode: 'payment',
  amount: 1099,
  currency: 'usd',
  payment_method_types: ['card'],
};

const Invoice = ({ invoice, handleOnPayment }: IInvoiceProps) => {
  const { isOpen, onOpen, onClose } = useDisclosure();
  const {
    isOpen: isReviewOpen,
    onOpen: onReviewOpen,
    onClose: onReviewClose,
  } = useDisclosure();
  const finalRef = useRef(null);
  const finalTwoRef = useRef(null);

  const closeReview = () => {
    onReviewClose();
  };

  return (
    <Tr>
      <Td>
        {invoice.avatarUrl ? (
          <Image width="45px" height="45px" borderRadius="50%" src={invoice.avatarUrl} />
        ) : (
          <Avatar />
        )}

        <Text color="text.primary" fontSize="0.9rem" mt="0.5rem">
          {invoice.firstName} {invoice.lastName}
        </Text>
      </Td>
      <Td color="text.primary">{invoice.invoiceId}</Td>
      <Td color="text.primary">${invoice.bid.toFixed(2)}</Td>
      <Td>
        <Image
          width="45px"
          height="45px"
          src={invoice.clothUrl}
          borderRadius={12}
          alt="a piece of clothing"
        />
      </Td>
      <Td>
        {invoice.paid ? (
          <>
            <ButtonGroup>
              <Button colorScheme="blue">
                <Box mr="0.5rem">
                  <AiOutlineCheckCircle />
                </Box>
                Paid
              </Button>
              <Button onClick={onReviewOpen}>
                <Box mr="0.5rem">
                  <MdOutlineRateReview />
                </Box>
                Review
              </Button>
              <Modal
                finalFocusRef={finalTwoRef}
                isOpen={isReviewOpen}
                onClose={onReviewClose}
              >
                <ModalOverlay />
                <ModalContent>
                  <ModalHeader>Review purchase</ModalHeader>
                  <ModalCloseButton />
                  <ModalBody>
                    <CreateReview closeReview={closeReview} invoice={invoice} />
                  </ModalBody>
                  <ModalFooter></ModalFooter>
                </ModalContent>
              </Modal>
            </ButtonGroup>
          </>
        ) : (
          <>
            <Button onClick={onOpen}>Pay Now</Button>

            <Modal finalFocusRef={finalRef} isOpen={isOpen} onClose={onClose}>
              <ModalOverlay />
              <ModalContent>
                <ModalHeader>Pay Now</ModalHeader>
                <ModalCloseButton />
                <ModalBody>
                  {/*@ts-ignore*/}
                  <Elements stripe={stripePromise} options={options}>
                    <CheckoutForm invoice={invoice} handleOnPayment={handleOnPayment} />
                  </Elements>
                </ModalBody>
                <ModalFooter></ModalFooter>
              </ModalContent>
            </Modal>
          </>
        )}
      </Td>
    </Tr>
  );
};

export default Invoice;
