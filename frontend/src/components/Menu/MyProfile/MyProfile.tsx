import { Box, Image, Flex, Text, Link } from '@chakra-ui/react';
import { useContext, useEffect, useState } from 'react';
import { Client } from '../../../util/client';
import Header from '../Header';
import { UserContext } from '../../../context/user';
import { IProfilePage, ISpeciality, IUserContext } from '../../../interfaces';
import { profilePageState } from '../../../state/initialState';
import FormLayout from '../EditProfile/FormLayout';
import EditProfileLink from './EditProfileLink';
import FormHeader from '../EditProfile/FormHeader';
import InitialIcon from '../../Shared/InitialIcon';
import Profile from '../../Profile/Profile';

const MyProfile = () => {
  const { user: contextUser } = useContext(UserContext) as IUserContext;
  const [profile, setProfile] = useState<IProfilePage>(profilePageState);

  useEffect(() => {
    if (contextUser.profileId !== 0) {
      fetchProfile();
    }
  }, [contextUser.profileId]);

  const fetchProfile = () => {
    Client.getProfile(contextUser.profileId)
      .then((res) => {
        const { data } = res.data;
        const { user, profile } = data;

        setProfile({
          ...user,
          ...profile,
          specialities: JSON.parse(profile.specialities),
        });
      })
      .catch((err) => {
        throw new Error(err.response.data.message);
      });
  };

  return (
    <Box>
      <Profile profile={profile} />
    </Box>
  );
};

export default MyProfile;
