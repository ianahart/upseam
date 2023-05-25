import { Box, Flex, Button, Text, Spacer } from '@chakra-ui/react';
import { IEditUserForm } from '../../../interfaces';
import FormInput from '../../Form/FormInput';

interface IUserFormProps {
  userForm: IEditUserForm;
  handleUpdateUserForm: (form: IEditUserForm) => void;
  handleUpdateField: (
    name: string,
    value: string,
    attribute: string,
    formType: string
  ) => void;
}
const UserForm = ({
  userForm,
  handleUpdateUserForm,
  handleUpdateField,
}: IUserFormProps) => {
  const updateField = (name: string, value: string, attribute: string) => {
    handleUpdateField(name, value, attribute, 'user');
  };

  const checkForErrors = () => {
    let errors = false;
    for (const [_, field] of Object.entries(userForm)) {
      if (field.error.length > 0 || field.value.length === 0) {
        errors = true;
      }
    }
    return errors;
  };

  const clearErrors = () => {
    for (const [key, _] of Object.entries(userForm)) {
      handleUpdateField(key, '', 'error', 'user');
    }
  };

  const handleOnSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    clearErrors();

    if (checkForErrors()) return;
    handleUpdateUserForm(userForm);
  };

  return (
    <>
      <form onSubmit={handleOnSubmit} className="editProfileForm">
        <Flex direction={['column', 'row', 'row']}>
          <FormInput
            updateField={updateField}
            value={userForm.firstName.value}
            error={userForm.firstName.error}
            name={userForm.firstName.name}
            type={userForm.firstName.type}
            errorField="First name"
            width="95%"
            htmlFor="firstName"
            label="First Name"
          />
          <FormInput
            updateField={updateField}
            value={userForm.lastName.value}
            error={userForm.lastName.error}
            name={userForm.lastName.name}
            type={userForm.lastName.type}
            errorField="Last name"
            width="95%"
            htmlFor="lastName"
            label="Last Name"
          />
        </Flex>
        <Flex justify="flex-end" m="1rem" flexDir="column" alignItems="flex-end">
          <Button type="submit" colorScheme="blue">
            Save
          </Button>
        </Flex>
      </form>
    </>
  );
};

export default UserForm;
