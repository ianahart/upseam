import {
  Box,
  Button,
  MenuButton,
  Menu,
  MenuList,
  MenuItem,
  Flex,
  Text,
  TableContainer,
  Table,
  TableCaption,
  Thead,
  Tbody,
  Th,
  Tr,
} from '@chakra-ui/react';
import Header from '../components/Menu/Header';
import { useContext, useEffect, useRef, useState } from 'react';
import { UserContext } from '../context/user';
import { IInvoice, IUserContext } from '../interfaces';
import { Client } from '../util/client';
import { BiChevronDown } from 'react-icons/bi';
import Invoice from '../components/Invoice/Invoice';

const InvoicesRoute = () => {
  const shouldRun = useRef(true);
  const { user } = useContext(UserContext) as IUserContext;
  const [invoices, setInvoices] = useState<IInvoice[]>([]);
  const [entryMessage, setEntryMessage] = useState('');
  const [pagination, setPagination] = useState({
    direction: 'next',
    paginate: false,
    page: 0,
    sort: 'DESC',
    pageSize: 1,
    totalPages: 0,
  });

  useEffect(() => {
    if (shouldRun.current && user.id !== 0) {
      shouldRun.current = false;
      getInvoices(pagination.paginate, pagination.direction, pagination.sort);
    }
  }, [shouldRun.current, user.id, pagination]);

  const getInvoices = (paginate: boolean, direction: string, sortBy: string) => {
    const pageNum = paginate ? pagination.page : -1;
    Client.getInvoices(direction, sortBy, pageNum, user.id, pagination.pageSize)
      .then((res) => {
        const { invoices, page, pageSize, sort, totalPages } = res.data.data;

        if (!paginate && invoices.length === 0) {
          setEntryMessage('You currently do not have any invoices');
        }

        if (paginate) {
          setInvoices((prevState) => [...prevState, ...invoices]);
        } else {
          setInvoices(invoices);
        }
        setPagination({
          ...pagination,
          page,
          pageSize,
          sort,
          totalPages,
        });
      })
      .catch((err) => {
        throw new Error(err.response.data.message);
      });
  };

  const handleSort = (value: string) => {
    setPagination((prevState) => ({
      ...prevState,
      sort: value,
    }));
    getInvoices(false, 'next', value);
  };

  const handleOnPayment = (invoiceId: number, receiverUserId: number) => {
    console.log(receiverUserId);
    updateStatus(invoiceId);
  };

  const updateStatus = (invoiceId: number) => {
    Client.updateInvoice(invoiceId)
      .then((res) => {
        const updatedInvoices = invoices.map((i) => {
          if (i.invoiceId === invoiceId) {
            i.paid = res.data.paid;
          }
          return i;
        });
        setInvoices(updatedInvoices);
      })
      .catch((err) => {
        throw new Error(err.response.data.message);
      });
  };

  return (
    <Box>
      <Header heading="Invoices" />
      {entryMessage.length > 0 && (
        <Box mt="3rem" textAlign="center">
          <Text color="text.primary">{entryMessage}</Text>
        </Box>
      )}
      <Box mt="6rem">
        <Menu>
          <MenuButton as={Button} rightIcon={<BiChevronDown />}>
            {pagination.sort === 'DESC' ? 'Descending' : 'Ascending'}
          </MenuButton>
          <MenuList>
            <MenuItem onClick={() => handleSort('ASC')}>Ascending</MenuItem>
            <MenuItem onClick={() => handleSort('DESC')}>Descending</MenuItem>
          </MenuList>
        </Menu>
      </Box>
      <TableContainer mt="3rem" mb="5rem">
        <Table variant="simple">
          <TableCaption>Current Invoices</TableCaption>
          <Thead>
            <Tr>
              <Th>Name</Th>
              <Th>Invoice Id</Th>
              <Th>Amount</Th>
              <Th>Clothing</Th>
              <Th>Status</Th>
            </Tr>
          </Thead>
          <Tbody>
            {invoices.map((invoice) => {
              return (
                <Invoice
                  handleOnPayment={handleOnPayment}
                  key={invoice.invoiceId}
                  invoice={invoice}
                />
              );
            })}
          </Tbody>
        </Table>
      </TableContainer>

      <Flex mt="7rem" mb="4rem" justify="center">
        {pagination.page !== pagination.totalPages && entryMessage.length === 0 && (
          <Button onClick={() => getInvoices(true, 'next', pagination.sort)}>
            Load More...
          </Button>
        )}
      </Flex>
    </Box>
  );
};

export default InvoicesRoute;
