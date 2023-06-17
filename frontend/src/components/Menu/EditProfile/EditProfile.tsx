import { Box, useToast } from '@chakra-ui/react';
import Header from '../Header';
import { useContext, useEffect, useState } from 'react';
import { editProfileState, userFormState } from '../../../state/initialState';
import { UserContext } from '../../../context/user';
import {
  IEditProfileForm,
  IEditUserForm,
  ISpeciality,
  IUserContext,
} from '../../../interfaces';
import { Client } from '../../../util/client';
import PhotoUpload from '../PhotoUpload';
import FormLayout from './FormLayout';
import UserForm from './UserForm';
import { abbreviate, capitalize } from '../../../util';
import FormHeader from './FormHeader';
import ProfileForm from './ProfileForm';
import { nanoid } from 'nanoid';

const EditProfile = () => {
  const toast = useToast();
  const { user, updateUser } = useContext(UserContext) as IUserContext;
  const [file, setFile] = useState<File | null>(null);
  const [isPhotoUploading, setIsPhotoUploading] = useState(false);
  const [profileForm, setProfileForm] = useState(editProfileState);
  const [userForm, setUserForm] = useState(userFormState);
  const [specialities, setSpecialities] = useState<ISpeciality[]>([]);

  const handleAddSpeciality = (speciality: string) => {
    setSpecialities((prevState) => [...prevState, { id: nanoid(), text: speciality }]);
  };

  const handleDeleteSpeciality = (id: string) => {
    setSpecialities(specialities.filter((speciality) => speciality.id !== id));
  };

  const handleUpdateProfileForm = () => {
    Client.updateProfile(profileForm, specialities, user.profileId)
      .then(() => {
        toast({
          title: 'Success',
          description: 'Successfully updated profile information',
          status: 'success',
          duration: 5000,
          isClosable: true,
        });
      })
      .catch((err) => {
        throw new Error(err.response.data.message);
      });
  };

  const handleUpdateUserForm = (form: IEditUserForm) => {
    Client.updateUser(form, user.id)
      .then(() => {
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
        throw new Error(err.response.data.message);
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
    setProfileForm((prevState) => ({
      ...prevState,
      [name]: { ...prevState[name as keyof IEditProfileForm], [attribute]: value },
    }));
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
      if (prop === 'avatarUrl') {
        continue;
      }
      if (prop === 'specialities') {
        if (data[prop] === null) {
          //@ts-ignoree
          data[prop] = [];
        }
        //@ts-ignore
        setSpecialities(JSON.parse(data[prop]));
      }
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
      .catch(() => {
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
          <FormLayout>
            <>
              <FormHeader heading="Address" />
              <ProfileForm
                handleUpdateField={handleUpdateField}
                profileForm={profileForm}
                specialities={specialities}
                handleAddSpeciality={handleAddSpeciality}
                handleDeleteSpeciality={handleDeleteSpeciality}
                handleUpdateProfileForm={handleUpdateProfileForm}
              />
            </>
          </FormLayout>
        </Box>
      </Box>
    </Box>
  );
};

export default EditProfile;
