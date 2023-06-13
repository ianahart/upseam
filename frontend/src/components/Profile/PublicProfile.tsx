import { Box } from '@chakra-ui/react';
import { Client } from '../../util/client';
import { useState, useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import { IProfilePage } from '../../interfaces';
import { profilePageState } from '../../state/initialState';
import Profile from './Profile';

const PublicProfile = () => {
  const location = useLocation();
  const [profile, setProfile] = useState<IProfilePage>(profilePageState);

  useEffect(() => {
    if (location.state.profileId !== 0) {
      fetchProfile();
    }
  }, [location.state.profileId]);

  const fetchProfile = () => {
    Client.getProfile(location.state.profileId)
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

export default PublicProfile;
