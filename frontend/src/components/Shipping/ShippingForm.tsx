import {
  useToast,
  Radio,
  RadioGroup,
  Stack,
  Button,
  Box,
  Select,
  Flex,
  Text,
  Tooltip,
} from '@chakra-ui/react';
import { useContext, useEffect, useRef, useState } from 'react';
import { shippingFormState } from '../../state/initialState';
import {
  IPreviousAddress,
  IShippingForm,
  IShippingProfile,
  IUserContext,
} from '../../interfaces';
import FormInput from '../Form/FormInput';
import { countries, states, shippingState } from '../../state/initialState';
import { UserContext } from '../../context/user';
import { Client } from '../../util/client';
import { AiOutlineClose, AiOutlineInfoCircle } from 'react-icons/ai';

const ShippingForm = () => {
  const toast = useToast();
  const shouldRun = useRef(true);
  const { user } = useContext(UserContext) as IUserContext;
  const [form, setForm] = useState(shippingFormState);
  const [previousAddresses, setPreviousAddresses] = useState<IPreviousAddress[]>([]);
  const [shippingAndHandling, setShippingAndHandling] = useState({
    name: shippingState.normal.name,
    value: shippingState.normal.value,
  });
  const [preference, setPreference] = useState('1');
  const handleOnPreferenceChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setPreference(e.target.value);
  };

  useEffect(() => {
    if (shouldRun.current && user.id !== 0 && preference === '1') {
      shouldRun.current = false;
      getShippingAddresses(user.id);
    }
  }, [shouldRun.current, user.id]);

  useEffect(() => {
    if (preference === '2') {
      getProfileShipping();
    }
    clearFormValues('value');
  }, [preference]);

  const getShippingAddresses = (userId: number) => {
    Client.getShipping(userId)
      .then((res) => {
        const { shipping } = res.data;
        setPreviousAddresses(shipping);
      })
      .catch((err) => {
        throw new Error(err.response.data.message);
      });
  };

  const getProfileShipping = () => {
    Client.getProfileShipping(user.profileId)
      .then((res) => {
        const { shipping } = res.data;
        syncForm(shipping);
      })
      .catch((err) => {
        throw new Error(err.response.data.message);
      });
  };

  const syncForm = <T,>(shipping: T) => {
    for (let prop in shipping) {
      const value = shipping[prop] === null ? '' : shipping[prop];
      updateField(prop, value as string, 'value');
    }
  };

  const clearFormValues = (attribute: string) => {
    for (let [key, _] of Object.entries(form)) {
      updateField(key, '', attribute);
    }
  };

  const updateField = (name: string, value: string, attribute: string) => {
    setForm((prevState) => ({
      ...prevState,
      [name]: { ...prevState[name as keyof IShippingForm], [attribute]: value },
    }));
  };

  const handleOnChange = (v: string) => {
    let name: string;
    let value: string;
    switch (v) {
      case '1':
        name = shippingState.premium.name;
        value = shippingState.premium.value;
        break;
      case '2':
        name = shippingState.medium.name;
        value = shippingState.medium.value;
        break;
      case '3':
        name = shippingState.normal.name;
        value = shippingState.normal.value;
        break;
      default:
        name = shippingState.normal.name;
        value = shippingState.normal.value;
    }
    setShippingAndHandling({ ...shippingAndHandling, name, value });
  };

  const checkForErrors = () => {
    let errors = false;
    const exclude = ['state'];
    for (const [key, field] of Object.entries(form)) {
      const { error, value, name } = field;
      if (error.length > 0) {
        errors = true;
      }
      if (!exclude.includes(name)) {
        if (value.trim().length === 0) {
          errors = true;
        }
      }
    }
    return errors;
  };

  const handleOnSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    clearFormValues('error');
    if (checkForErrors()) return;
    createOrUpdateShipping();
  };

  const createOrUpdateShipping = () => {
    Client.createOrUpdateShipping(
      user.id,
      form,
      shippingAndHandling.name,
      shippingAndHandling.value
    )
      .then((res) => {
        toast({
          title: 'Shipping created.',
          description: "We've created/updated your shipping preferences",
          status: 'success',
          duration: 5000,
          isClosable: true,
        });
      })
      .catch((err) => {
        throw new Error(err.response.data.message);
      });
  };

  const handleOnChangePreviousAddress = (
    e: React.ChangeEvent<HTMLInputElement>,
    previousAddress: IPreviousAddress
  ) => {
    handleOnChange(previousAddress.shippingType);
    syncForm(previousAddress);
  };

  const removePreviousAddress = (shippingId: number) => {
    Client.removeShipping(shippingId)
      .then((res) => {
        const filtered = previousAddresses.filter(
          (shipping) => shipping.id !== shippingId
        );
        setPreviousAddresses(filtered);
      })
      .catch((err) => {
        console.log(err);
        throw new Error(err.response.data.message);
      });
  };

  return (
    <Box width={['95%', '95%', '600px']} mx="auto">
      <form onSubmit={handleOnSubmit}>
        <Flex align="center" mt="3rem" color="black.primary">
          <AiOutlineInfoCircle />
          <Text ml="0.5rem">Check your preference</Text>
        </Flex>

        <RadioGroup mb="2rem" mt="1rem" defaultValue="1">
          <Stack>
            <Radio onChange={handleOnPreferenceChange} value="1">
              New shipping address
            </Radio>
            <Radio onChange={handleOnPreferenceChange} value="2">
              Same as profile information
            </Radio>
            <Radio onChange={handleOnPreferenceChange} value="3">
              Previous shipping addresses
            </Radio>
          </Stack>
        </RadioGroup>
        {preference === '3' && (
          <Box ml="1.5rem">
            <RadioGroup>
              {previousAddresses.map((address, index) => {
                return (
                  <Flex align="center" justify="space-between" key={address.id}>
                    <Radio
                      my="0.5rem"
                      value={(index + 1).toString()}
                      onChange={(e) => handleOnChangePreviousAddress(e, address)}
                    >
                      <Text fontStyle="italic" color="text.primary">
                        {address.firstName} {address.lastName}, {address.address},{' '}
                        {address.country}, {address.zipCode}
                      </Text>
                    </Radio>
                    <Tooltip label="remove">
                      <Box
                        cursor="pointer"
                        onClick={() => removePreviousAddress(address.id)}
                        color="text.primary"
                      >
                        <AiOutlineClose />
                      </Box>
                    </Tooltip>
                  </Flex>
                );
              })}
            </RadioGroup>
          </Box>
        )}
        <FormInput
          updateField={updateField}
          value={form.firstName.value}
          error={form.firstName.error}
          name={form.firstName.name}
          type={form.firstName.type}
          errorField="First name"
          width="100%"
          htmlFor="firstName"
          label="First Name"
        />
        <FormInput
          updateField={updateField}
          value={form.lastName.value}
          error={form.lastName.error}
          name={form.lastName.name}
          type={form.lastName.type}
          errorField="Last name"
          width="100%"
          htmlFor="lastName"
          label="Last Name"
        />
        <FormInput
          updateField={updateField}
          value={form.address.value}
          error={form.address.error}
          name={form.address.name}
          type={form.address.type}
          errorField="Address"
          width="100%"
          htmlFor="address"
          label="Address"
        />
        <FormInput
          updateField={updateField}
          value={form.zipCode.value}
          error={form.zipCode.error}
          name={form.zipCode.name}
          type={form.zipCode.type}
          errorField="Zip code"
          width="100%"
          htmlFor="zipCode"
          label="Zip Code"
        />
        <Box my="1.5rem">
          <Select
            value={form.country.value}
            name="country"
            onChange={(e) => updateField('country', e.target.value, 'value')}
            placeholder="Country"
          >
            {countries.map(({ id, name }) => {
              return (
                <option key={id} value={name}>
                  {name}
                </option>
              );
            })}
          </Select>
        </Box>
        {form.country.value.toLowerCase() === 'united states' && (
          <Box my="1.5rem">
            <Select
              value={form.state.value}
              name="state"
              onChange={(e) => updateField('state', e.target.value, 'value')}
              placeholder="State"
            >
              {states.map(({ id, name }) => {
                return (
                  <option key={id} value={name}>
                    {name}
                  </option>
                );
              })}
            </Select>
          </Box>
        )}

        <Flex>
          <Text color="black.primary">Shipping and Handling Preferences</Text>
        </Flex>

        <RadioGroup
          name="shipping"
          onChange={handleOnChange}
          mb="2rem"
          mt="1rem"
          defaultValue="3"
        >
          <Stack color="text.primary">
            <Radio value="1">{shippingState.premium.value}</Radio>
            <Radio value="2">{shippingState.medium.value}</Radio>
            <Radio value="3">{shippingState.normal.value}</Radio>
          </Stack>
        </RadioGroup>
        <Flex my="2rem" justify="center">
          <Button type="submit" colorScheme="blue">
            Save Shipping
          </Button>
        </Flex>
      </form>
    </Box>
  );
};
export default ShippingForm;
