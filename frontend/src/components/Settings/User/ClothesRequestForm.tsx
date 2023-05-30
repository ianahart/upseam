import {
  Box,
  Flex,
  Select,
  Button,
  Text,
  Input,
  Image,
  FormLabel,
  Spinner,
  Textarea,
} from '@chakra-ui/react';
import FormHeader from '../EditProfile/FormHeader';
import { useContext, useState } from 'react';
import { GiClothes } from 'react-icons/gi';
import { AiOutlineUpload } from 'react-icons/ai';
import FormLayout from '../EditProfile/FormLayout';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import * as dayjs from 'dayjs';
import customParseFormat from 'dayjs/plugin/customParseFormat';
dayjs.extend(customParseFormat);
import { Client } from '../../../util/client';
import { UserContext } from '../../../context/user';
import { IUserContext } from '../../../interfaces/index';
import { clothesSizeState } from '../../../state/initialState';
import { useNavigate, useParams } from 'react-router-dom';
import { slugify } from '../../../util';
import { useEffectOnce } from '../../../hooks/useEffectOnce';

interface IClothesRequestsFormProps {
  type: string;
  buttonText: string;
}

const ClothesRequestsForm = ({ type, buttonText }: IClothesRequestsFormProps) => {
  const { user } = useContext(UserContext) as IUserContext;
  const navigate = useNavigate();
  const params = useParams();
  const [file, setFile] = useState<File | null>(null);
  const [isLoading, setIsLoading] = useState(false);
  const [description, setDescription] = useState('');
  const [dataURL, setDataURL] = useState<string>('');
  const [fileError, setFileError] = useState('');
  const [charCounter, setCharCounter] = useState(0);
  const [isDragging, setIsDragging] = useState(false);
  const [size, setSize] = useState('');
  const [clothId, setClothId] = useState(0);
  const [startDate, setStartDate] = useState(new Date());
  const [error, setError] = useState('');
  const MAX_LENGTH = 250;

  const syncForm = (clothId: string) => {
    Client.syncCloth(clothId)
      .then((res) => {
        const { cloth } = res.data;
        setClothId(cloth.id);
        setDataURL(cloth.clothUrl);
        setDescription(cloth.description);
        setCharCounter(cloth.description.length);
        setStartDate(dayjs(cloth.dueDate).toDate());
        setSize(cloth.size);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  useEffectOnce(() => {
    if (params.clothId !== undefined) {
      syncForm(params.clothId);
    }
  });

  const createCloth = () => {
    if (file === null) return;
    Client.createCloth(startDate, description, file, user.id, size)
      .then((res) => {
        navigate(`/settings/${slugify(user.firstName, user.lastName)}/profile`);

        setIsLoading(false);
      })
      .catch((err) => {
        setIsLoading(false);
        if (err.response.status === 400) {
          setError(err.response.data.message);
        }
        throw new Error(err.response.data.message);
      });
  };

  const updateCloth = () => {
    setIsLoading(true);
    Client.updateCloth(startDate, description, file, user.id, size, clothId)
      .then((response) => {
        navigate(`/settings/${slugify(user.firstName, user.lastName)}/clothes`);
        setIsLoading(false);
      })
      .catch((err) => {
        setIsLoading(false);
        console.log(err);
      });
  };

  const handleOnClick = (e: React.MouseEvent<HTMLButtonElement>) => {
    if (file === null && type === 'create') {
      setError('Please upload an image of a piece of clothes you want made');
      return;
    }
    if (description.trim().length === 0) {
      setError('Please provide a description of what you want made');
      return;
    }
    setIsLoading(true);
    if (type === 'create' && file !== null) {
      createCloth();
    }
    if (type === 'update') {
      updateCloth();
    }
  };

  const handleOnTextAreaChange = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    const { value } = e.target;
    if (value.length > MAX_LENGTH) return;
    setDescription(value);
    if (value.length > 1) {
      setCharCounter(value.length);
    }
  };

  const handleOnKeyDown = (e: React.KeyboardEvent<HTMLTextAreaElement>) => {
    if (e.key === 'Backspace' || e.key === 'Delete') {
      if (description.length === 0) {
        setCharCounter(0);
        return;
      }
      setCharCounter((prevState) => prevState - 1);
      return;
    }
    if (description.length < MAX_LENGTH) {
      setCharCounter((prevState) => prevState + 1);
    }
  };

  const readFile = (newFile: File) => {
    const fileReader = new FileReader();
    fileReader.onload = (e) => {
      if (e.target?.result !== undefined && e.target?.result !== null) {
        if (typeof e.target?.result === 'string') {
          setDataURL(e.target?.result);
        }
      }
    };

    fileReader.readAsDataURL(newFile);
  };

  const handleOnPhotoChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFileError('');
    if (e.target.files === null) return;
    const newFile: File = e.target.files[0];
    if (newFile.size > 1000000) {
      setFileError('Image must be under 1 megabyte.');
      return;
    }
    readFile(newFile);
    setFile(newFile);
    setIsDragging(false);
  };

  return (
    <FormLayout>
      <>
        <FormHeader heading="Request clothing to be made" />
        {error && (
          <Text color="red.500" fontSize="0.85rem">
            {error}
          </Text>
        )}
        <Box p="0.5rem" my="2rem">
          <Text mb="0.25rem" color="text.primary">
            Upload a photo to help with the description
          </Text>
          <Box
            onDragEnter={() => setIsDragging(true)}
            onDragLeave={() => setIsDragging(false)}
            backgroundSize="cover"
            backgroundPosition="center"
            backgroundImage={`${dataURL} ? url(${dataURL}) : 'none'`}
            border="1px solid"
            borderColor="light.primary"
            borderRadius="8px"
            width="300px"
            height="200px"
          >
            <Flex
              pos="relative"
              top="4px"
              left="3px"
              direction="column"
              align="center"
              justify="center"
              border="1px dashed"
              borderColor="light.primary"
              borderRadius="8px"
              width="290px"
              height="190px"
            >
              {dataURL.length > 0 && (
                <Image borderRadius="8px" width="300px" height="200px" src={dataURL} />
              )}

              {isDragging && (
                <Box mt="2rem" zIndex={1} fontSize="3rem" color="black.primary">
                  <AiOutlineUpload />
                </Box>
              )}
              <Input
                onChange={handleOnPhotoChange}
                pos="absolute"
                accept="image/*"
                top="0"
                opacity="0"
                left="0"
                width="100%"
                height="100%"
                zIndex="10"
                type="file"
              />
              <Flex
                pos="absolute"
                direction="column"
                color="text.primary"
                justify="center"
                align="center"
                margin=" 3rem auto"
              >
                {!dataURL && <GiClothes fontSize="8rem" />}
              </Flex>
            </Flex>
          </Box>
        </Box>
        <FormLabel mt="1rem" color="text.primary">
          Description
        </FormLabel>
        <Text color="text.primary">{charCounter}/250</Text>
        <Textarea
          mb="1rem"
          value={description}
          onChange={handleOnTextAreaChange}
          onKeyUp={handleOnKeyDown}
          placeholder="Description of the item"
        ></Textarea>
        <Box my="2rem">
          <Text color="text.primary">Due Date</Text>
          <Box
            p="0.5rem"
            borderRadius="20px"
            cursor="pointer"
            border="1px solid"
            borderColor="light.primary"
            color="black.primary"
          >
            <DatePicker
              selected={startDate}
              onChange={(date) => setStartDate(date as any)}
            />
          </Box>
        </Box>
        <Box my="1rem">
          <Text color="text.primary">Size</Text>
          <Select
            value={size}
            onChange={(e) => setSize(e.target.value)}
            placeholder="Select Size"
          >
            {clothesSizeState.map(({ id, size }) => {
              return (
                <option key={id} value={size}>
                  {size}
                </option>
              );
            })}
          </Select>
        </Box>
        <Flex justifyContent="center" my="1.5rem">
          {isLoading && (
            <Spinner
              thickness="4px"
              speed="0.65s"
              emptyColor="gray.200"
              color="blue.500"
              size="xl"
            />
          )}
          {!isLoading && (
            <Button width="50%" colorScheme="blue" onClick={handleOnClick}>
              {buttonText}
            </Button>
          )}
        </Flex>
      </>
    </FormLayout>
  );
};

export default ClothesRequestsForm;
