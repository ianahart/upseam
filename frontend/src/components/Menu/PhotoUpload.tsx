import { Box, Flex, Image, Input, Spinner, Text } from '@chakra-ui/react';
import { BsPencil } from 'react-icons/bs';
import { BiUser } from 'react-icons/bi';
import { AiOutlineUpload, AiOutlineClose } from 'react-icons/ai';
import { useState } from 'react';

interface IPhotoUploadProps {
  isPhotoUploading: boolean;
  avatarUrl: string;
  setFile: (file: File | null) => void;
  file: File | null;
  handlePhotoUpload: (file: File) => void;
}

const PhotoUpload = ({
  isPhotoUploading,
  avatarUrl,
  setFile,
  file,
  handlePhotoUpload,
}: IPhotoUploadProps) => {
  const [isDragging, setIsDragging] = useState(false);
  const [fileError, setFileError] = useState('');

  console.log(fileError);
  const handleOnDragEnter = () => {
    if (!isDragging) {
      setIsDragging(true);
    }
  };
  const handleOnDragLeave = () => {
    if (isDragging) {
      setIsDragging(false);
    }
  };

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFileError('');
    if (e.target.files === null) return;
    const newFile: File = e.target.files[0];
    if (newFile.size > 1000000) {
      setFileError('Image must be under 1 megabyte.');
      return;
    }
    handlePhotoUpload(newFile);
    setIsDragging(false);
  };

  return (
    <>
      {isPhotoUploading && (
        <Flex direction="column" justify="center">
          <Spinner
            thickness="4px"
            speed="0.65s"
            emptyColor="gray.200"
            color="blue.500"
            size="xl"
          />
          <Text color="black.primary" mt="1rem">
            Uploading photo...
          </Text>
        </Flex>
      )}
      {!isPhotoUploading && (
        <Box
          onDragEnter={handleOnDragEnter}
          onDragLeave={handleOnDragLeave}
          pos="relative"
          width="200px"
          height="200px"
          borderRadius="50%"
          cursor="pointer"
          border="1px solid"
          borderColor="light.primary"
        >
          <Flex
            justify="center"
            align="center"
            direction="column"
            borderRadius="50%"
            width="100%"
            height="100%"
          >
            {isDragging && <AiOutlineUpload fontSize="3rem" />}
            {!isDragging && !avatarUrl && <BiUser fontSize="6rem" />}
            {!isDragging && avatarUrl && (
              <Image
                borderRadius="50%"
                width="100%"
                height="100%"
                src={avatarUrl}
                alt="profile picture"
              />
            )}
          </Flex>
          <Input
            onChange={handleFileChange}
            pos="absolute"
            top="0"
            accept="image/*"
            opacity={0}
            left="0"
            width="100%"
            height="100%"
            type="file"
            zIndex="10"
          />
          <Flex
            zIndex={11}
            direction="column"
            align="center"
            justify="center"
            borderRadius="50%"
            bg="blue.500"
            height="40px"
            width="40px"
            pos="absolute"
            bottom="0px"
            color="#fff"
            right="30px"
          >
            {file !== null ? (
              <Box onClick={() => setFile(null)}>
                <AiOutlineClose />
              </Box>
            ) : (
              <Box>
                <BsPencil />
              </Box>
            )}
          </Flex>
        </Box>
      )}
    </>
  );
};

export default PhotoUpload;
