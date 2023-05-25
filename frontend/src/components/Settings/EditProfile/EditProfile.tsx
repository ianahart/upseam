import { Box, Container, useToast } from '@chakra-ui/react';
import Header from '../Header';
import { useContext, useEffect, useState } from 'react';
import { editProfileState, userFormState } from '../../../state/initialState';
import { UserContext } from '../../../context/user';
import { IEditUserForm, IUserContext } from '../../../interfaces';
import { Client } from '../../../util/client';
import PhotoUpload from '../PhotoUpload';
import FormLayout from './FormLayout';
import UserForm from './UserForm';
import { abbreviate, capitalize } from '../../../util';
import FormHeader from './FormHeader';

const EditProfile = () => {
  const toast = useToast();
  const { user, updateUser } = useContext(UserContext) as IUserContext;
  const [file, setFile] = useState<File | null>(null);
  const [isPhotoUploading, setIsPhotoUploading] = useState(false);
  const [profileForm, setProfileForm] = useState(editProfileState);
  const [userForm, setUserForm] = useState(userFormState);

  const handleUpdateUserForm = (form: IEditUserForm) => {
    Client.updateUser(form, user.id)
      .then((res) => {
        updateUser({
          ...user,
          firstName: capitalize(form.firstName.value),
          lastName: capitalize(form.lastName.value),
          abbreviation: abbreviate(form.firstName.value, form.lastName.value),
        });
        toast({
          title: 'Success',
          description: 'Successfully updated personal information',
          status: 'success',
          duration: 5000,
          isClosable: true,
        });
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const handleUpdateField = (
    name: string,
    value: string,
    attribute: string,
    formType: string
  ) => {
    if (formType === 'user') {
      setUserForm((prevState) => ({
        ...prevState,
        [name]: { ...prevState[name as keyof IEditUserForm], [attribute]: value },
      }));
      return;
    }
  };

  useEffect(() => {
    if (user.profileId !== 0) {
      fetchProfile();
    }
  }, [user.profileId]);

  const fetchProfile = () => {
    Client.getProfile(user.profileId)
      .then((res) => {
        const { data } = res.data;
        syncProfile(data.user, setUserForm);
        syncProfile(data.profile, setProfileForm);
      })
      .catch((err) => {
        throw new Error(err.response.data.message);
      });
  };

  const syncProfile = <T,>(data: T, setState: (arg: any) => void) => {
    for (let prop in data) {
      setState((prevState: any) => ({
        ...prevState,
        [prop]: { ...prevState[prop], value: data[prop] === null ? '' : data[prop] },
      }));
    }
  };

  const handlePhotoUpload = (file: File) => {
    setIsPhotoUploading(true);
    Client.uploadPhoto(file, user.profileId)
      .then((res) => {
        updateUser(Object.assign({}, { ...user, avatarUrl: res.data.url }));
        setIsPhotoUploading(false);
      })
      .catch((err) => {
        setIsPhotoUploading(false);
      });
  };

  return (
    <Box>
      <Header heading="Edit Profile" />
      <Box mt="3rem">
        <PhotoUpload
          isPhotoUploading={isPhotoUploading}
          avatarUrl={user.avatarUrl}
          handlePhotoUpload={handlePhotoUpload}
          file={file}
          setFile={setFile}
        />
        <Box mt="3rem">
          <FormLayout>
            <>
            <FormHeader heading="Personal Information" />
            <UserForm
              userForm={userForm}
              handleUpdateUserForm={handleUpdateUserForm}
              handleUpdateField={handleUpdateField}
            />
                        </>
          </FormLayout>
        </Box>
      </Box>
    </Box>
  );
};

export default EditProfile;
