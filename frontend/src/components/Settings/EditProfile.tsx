import { Box } from '@chakra-ui/react';
import Header from './Header';
import { useContext, useEffect, useState } from 'react';
import { editProfileState, userFormState } from '../../state/initialState';
import { UserContext } from '../../context/user';
import { IUserContext } from '../../interfaces';
import { Client } from '../../util/client';
import { useEffectOnce } from '../../hooks/useEffectOnce';
import PhotoUpload from './PhotoUpload';

const EditProfile = () => {
  const { user, updateUser } = useContext(UserContext) as IUserContext;
  const [file, setFile] = useState<File | null>(null);
  const [isPhotoUploading, setIsPhotoUploading] = useState(false);
  const [specialities, setSpecialities] = useState<string[]>([]);
  const [profileForm, setProfileForm] = useState(editProfileState);
  const [userForm, setUserForm] = useState(userFormState);

  useEffect(() => {
    if (user.profileId !== 0) {
      syncProfile();
    }
  }, [user.profileId]);

  const syncProfile = () => {
    Client.getProfile(user.profileId)
      .then((res) => {
        console.log(res);
      })
      .catch((err) => {
        console.log(err);
      });
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
      </Box>
    </Box>
  );
};

export default EditProfile;
