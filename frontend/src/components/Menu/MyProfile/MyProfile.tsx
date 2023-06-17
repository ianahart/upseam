import { Box } from '@chakra-ui/react';
import { useContext, useEffect, useState } from 'react';
import { Client } from '../../../util/client';
import { UserContext } from '../../../context/user';
import { IProfilePage, IUserContext } from '../../../interfaces';
import { profilePageState } from '../../../state/initialState';
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
