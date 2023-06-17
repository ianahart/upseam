import { Avatar, Box, Button, Image, Td, Text, Tr } from '@chakra-ui/react';
import { IInvoice } from '../../interfaces';
import { AiOutlineCheckCircle } from 'react-icons/ai';

interface IInvoiceProps {
  invoice: IInvoice;
  handleOnPayment: (invoiceId: number, receiverUserId: number) => void;
}

const Invoice = ({ invoice, handleOnPayment }: IInvoiceProps) => {
  const handlePayNow = () => {
    const { invoiceId, userId } = invoice;
    handleOnPayment(invoiceId, userId);
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
          <Button colorScheme="blue">
            <Box mr="0.5rem">
              <AiOutlineCheckCircle />
            </Box>
            Paid
          </Button>
        ) : (
          <Button onClick={handlePayNow}>Pay Now</Button>
        )}
      </Td>
    </Tr>
  );
};

export default Invoice;
