import { FormControl, FormLabel, FormErrorMessage, Input, Box } from '@chakra-ui/react';
import { AiOutlineEye, AiOutlineEyeInvisible } from 'react-icons/ai';

export interface IFormInputProps {
  updateField: (name: string, value: string, attribute: string) => void;
  value: string;
  error: string;
  name: string;
  type: string;
  width: string;
  htmlFor: string;
  label: string;
  errorField: string;
  noValidation?: boolean;
}

const FormInput = ({
  updateField,
  value,
  error,
  name,
  type,
  width,
  htmlFor,
  label,
  errorField,
  noValidation,
}: IFormInputProps) => {
  const handleOnChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    updateField(name, value, 'value');
  };

  const validateField = (value: string) => {
    if (value.trim().length === 0 || value.length > 100) {
      return false;
    }

    return true;
  };

  const handleOnBlur = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (noValidation) return;
    const { name, value } = e.target;
    if (!validateField(value)) {
      const error = `${errorField} must be between 1 and 100 characters.`;
      updateField(name, error, 'error');
    }
  };

  const handleOnFocus = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name } = e.target;
    updateField(name, '', 'error');
  };

  const togglePasswordVisibility = (e: React.MouseEvent<HTMLDivElement>) => {
    const value = type === 'password' ? 'text' : 'password';
    updateField('password', value, 'type');
    updateField('confirmPassword', value, 'type');
  };

  return (
    <FormControl
      my="1.5rem"
      display="flex"
      flexDir="column"
      isInvalid={error.length > 0}
      textAlign="center"
    >
      <FormLabel color="text.primary" htmlFor={htmlFor}>
        {label}
      </FormLabel>
      <Input
        onBlur={handleOnBlur}
        onChange={handleOnChange}
        onFocus={handleOnFocus}
        width={['100%', width, width]}
        value={value}
        name={name}
        type={type}
      />
      {name === 'password' && (
        <Box
          onClick={togglePasswordVisibility}
          fontSize="1.2rem"
          color="text.primary"
          position="absolute"
          bottom="10px"
          right="5px"
        >
          {type === 'password' ? <AiOutlineEye /> : <AiOutlineEyeInvisible />}
        </Box>
      )}

      {error.length > 0 && <FormErrorMessage>{error}</FormErrorMessage>}
    </FormControl>
  );
};

export default FormInput;
