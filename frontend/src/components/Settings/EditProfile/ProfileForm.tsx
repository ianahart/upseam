import {
  Box,
  Button,
  Flex,
  FormLabel,
  Input,
  Select,
  Text,
  Textarea,
} from '@chakra-ui/react';
import { IEditProfileForm, ISpeciality } from '../../../interfaces';
import FormInput from '../../Form/FormInput';
import { AiOutlineCloseCircle } from 'react-icons/ai';
import { states, countries, pricing } from '../../../state/initialState';
import FormHeader from './FormHeader';
import { useState } from 'react';

interface IProfileFormProps {
  specialities: ISpeciality[];
  handleUpdateProfileForm: () => void;
  handleDeleteSpeciality: (id: string) => void;
  profileForm: IEditProfileForm;
  handleAddSpeciality: (speciality: string) => void;
  handleUpdateField: (
    name: string,
    value: string,
    attribute: string,
    formType: string
  ) => void;
}

const ProfileForm = ({
  specialities,
  profileForm,
  handleAddSpeciality,
  handleUpdateField,
  handleDeleteSpeciality,
  handleUpdateProfileForm,
}: IProfileFormProps) => {
  const [speciality, setSpeciality] = useState('');
  const updateField = (name: string, value: string, attribute: string) => {
    handleUpdateField(name, value, attribute, 'profile');
  };

  const handleOnClick = (e: React.MouseEvent<HTMLButtonElement>) => {
    e.stopPropagation();
    handleAddSpeciality(speciality);
  };

  const handleOnTextAreaChange = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    const { name, value } = e.target;
    console.log(name, value);
    handleUpdateField(name, value, 'value', 'profile');
  };

  const handleOnSelectChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    const { name, value } = e.target;
    handleUpdateField(name, value, 'value', 'profile');
  };

  return (
    <Box>
      <form>
        <Flex direction={['column', 'row', 'row']} justify="space-between">
          <Flex direction="column">
            <FormLabel color="text.primary">Country</FormLabel>
            <Select
              onChange={handleOnSelectChange}
              name="country"
              width="100%"
              placeholder="Select Country"
            >
              {countries.map((country) => {
                return <option key={country.id}>{country.name}</option>;
              })}
            </Select>
          </Flex>
          <Flex direction="column">
            <FormLabel color="text.primary">State</FormLabel>
            <Select
              onChange={handleOnSelectChange}
              name="state"
              width="100%"
              placeholder="Select State"
            >
              {states.map((state) => {
                return <option key={state.id}>{state.name}</option>;
              })}
            </Select>
          </Flex>
        </Flex>
        <Flex direction={['column', 'row', 'row']} justify="space-between">
          <FormInput
            updateField={updateField}
            value={profileForm.address.value}
            error={profileForm.address.error}
            name={profileForm.address.name}
            type={profileForm.address.type}
            errorField="Address"
            width="70%"
            htmlFor="address"
            label="Address"
            noValidation={true}
          />
          <Box ml="auto">
            <FormInput
              updateField={updateField}
              value={profileForm.zipCode.value}
              error={profileForm.zipCode.error}
              name={profileForm.zipCode.name}
              type={profileForm.zipCode.type}
              errorField="Zip code"
              width="100%"
              htmlFor="zipCode"
              label="Zip Code"
              noValidation={true}
            />
          </Box>
        </Flex>
        <FormHeader heading="About" />
        <FormInput
          updateField={updateField}
          value={profileForm.site.value}
          error={profileForm.site.error}
          name={profileForm.site.name}
          type={profileForm.site.type}
          errorField="Site"
          width="70%"
          htmlFor="site"
          label="Personal Site"
          noValidation={true}
        />
        <Textarea
          name="bio"
          onChange={handleOnTextAreaChange}
          my="1rem"
          value={profileForm.bio.value}
          placeholder="About yourself"
        />
        <Box my="2rem">
          <FormLabel mb="0.25rem" color="text.primary">
            Specialities
          </FormLabel>
          <Text mb="0.25rem" color="text.primary">
            {specialities.length}/5
          </Text>
          {specialities.length !== 5 && (
            <Flex>
              <Input
                onChange={(e) => setSpeciality(e.target.value)}
                width="60%"
                type="text"
              />
              <Button onClick={handleOnClick} mx="1rem">
                Add
              </Button>
            </Flex>
          )}
          {specialities.length > 0 && (
            <Flex justify="space-around" wrap="wrap" my="2rem" width="60%">
              {specialities.map(({ id, text }) => {
                return (
                  <Box p="0.5rem" m="0.5rem" bg="gray.100" key={id} pos="relative">
                    <Text>{text}</Text>
                    <Box
                      cursor="pointer"
                      onClick={() => handleDeleteSpeciality(id)}
                      pos="absolute"
                      top="0"
                      right="-15px"
                    >
                      <AiOutlineCloseCircle />
                    </Box>
                  </Box>
                );
              })}
            </Flex>
          )}
        </Box>
        <Flex direction="column">
          <FormLabel color="text.primary">Your price range</FormLabel>
          <Select
            onChange={handleOnSelectChange}
            name="pricing"
            width="70%"
            placeholder="Select Pricing"
          >
            {pricing.map(({ id, price }) => {
              return <option key={id}>{price}</option>;
            })}
          </Select>
        </Flex>

        <Flex justify="flex-end" my="2rem">
          <Button onClick={handleUpdateProfileForm} colorScheme="blue" type="submit">
            Save
          </Button>
        </Flex>
      </form>
    </Box>
  );
};

export default ProfileForm;
